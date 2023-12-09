package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class AddToCartTest extends UiBaseTest {

  @Test
  void testAddItemToCart() {
    getIndex();

    loginSuccessfully();

    // Get item data from inventory
    WebElement item = DRIVER.findElement(
        By.cssSelector("div.inventory_item:nth-of-type(1)"));
    String itemName =
        item.findElement(By.cssSelector("div.inventory_item_name"))
            .getText();
    String itemDesc =
        item.findElement(By.cssSelector("div.inventory_item_desc"))
            .getText();
    String itemPrice =
        item.findElement(By.cssSelector("div.inventory_item_price"))
            .getText();

    WebElement addToCart = findByDataTest("add-to-cart-sauce-labs-backpack");
    addToCart.click();

    // Check that there is no longer "add to cart" option for this item
    Assertions.assertThrows(NoSuchElementException.class,
        () -> findByDataTest("add-to-cart-sauce-labs-backpack"));

    // Check that there is "remove" option for this item
    WebElement removeBackpack = findByDataTest("remove-sauce-labs-backpack");
    Assertions.assertEquals("Remove", removeBackpack.getText());

    String quantity = "1";
    Assertions.assertEquals(quantity,
        DRIVER.findElement(By.className("shopping_cart_badge")).getText());

    WebElement shoppingCartLink =
        DRIVER.findElement(By.className("shopping_cart_link"));
    shoppingCartLink.click();

    // Get item data from cart
    WebElement cartItem = DRIVER.findElement(By.cssSelector("div.cart_item"));
    String cartItemQuantity =
        cartItem.findElement(By.cssSelector("div.cart_quantity"))
            .getText();
    String cartItemName =
        cartItem.findElement(By.cssSelector("div.inventory_item_name"))
            .getText();
    String cartItemDesc =
        cartItem.findElement(By.cssSelector("div.inventory_item_desc"))
            .getText();
    String cartItemPrice =
        cartItem.findElement(By.cssSelector("div.inventory_item_price"))
            .getText();

    // Assert cart against inventory data
    Assertions.assertEquals(quantity, cartItemQuantity);
    Assertions.assertEquals(itemName, cartItemName);
    Assertions.assertEquals(itemDesc, cartItemDesc);
    Assertions.assertEquals(itemPrice, cartItemPrice);
  }

}
