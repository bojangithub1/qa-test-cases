package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class RemoveFromCartTest extends UiBaseTest {

  @Test
  void testRemoveItemFromCart() {
    getIndex();

    loginSuccessfully();

    // Add item to cart
    WebElement addToCart = findByDataTest("add-to-cart-sauce-labs-backpack");
    addToCart.click();

    WebElement shoppingCartLink =
        DRIVER.findElement(By.className("shopping_cart_link"));
    shoppingCartLink.click();

    WebElement removeBackpackFromCart =
        findByDataTest("remove-sauce-labs-backpack");
    removeBackpackFromCart.click();

    WebElement continueShopping = findByDataTest("continue-shopping");
    continueShopping.click();

    // Check if item is available to add to cart
    Assertions.assertDoesNotThrow(
        () -> findByDataTest("add-to-cart-sauce-labs-backpack"));

    Assertions.assertThrows(NoSuchElementException.class, () ->
        DRIVER.findElement(By.className("shopping_cart_badge")).getText());
  }

}
