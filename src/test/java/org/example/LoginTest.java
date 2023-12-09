package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

public class LoginTest extends ParentTest {

  private static Stream<Arguments> testLoginWithUsernameAndPasswordInputs() {
    return Stream.of(
        Arguments.of("correct username - correct password", "standard_user",
            "secret_sauce", true),
        Arguments.of("correct username - incorrect password", "standard_user",
            "password", false),
        Arguments.of("incorrect username - correct password", "username",
            "secret_sauce", false),
        Arguments.of("incorrect username - incorrect password", "username",
            "password", false)
    );
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testLoginWithUsernameAndPasswordInputs")
  void testLoginWithUsernameAndPassword(String displayName, String username,
                                        String password, boolean result) {
    getIndex();

    inputUsername(username);
    inputPassword(password);
    login();

    // TODO: Use smarter assertion
    Assertions.assertEquals(result,
        "https://www.saucedemo.com/inventory.html".equals(
            DRIVER.getCurrentUrl()));
  }


  private static Stream<Arguments> testLoginWithMissingUsernameOrPasswordInputs() {
    return Stream.of(
        Arguments.of("missing username", "", "secret_sauce"),
        Arguments.of("missing password", "standard_user", "")
    );
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testLoginWithMissingUsernameOrPasswordInputs")
  void testLoginWithMissingUsernameOrPassword(String displayName,
                                              String username,
                                              String password) {
    getIndex();

    inputUsername(username);
    inputPassword(password);

    login();

    Assertions.assertTrue(
        DRIVER.findElement(By.cssSelector("[data-test='error']"))
            .isDisplayed());
  }

  private static void inputPassword(String password) {
    WebElement passwordElement =
        DRIVER.findElement(By.cssSelector("[data-test='password']"));
    passwordElement.sendKeys(password);
  }

  private static void login() {
    WebElement login =
        DRIVER.findElement(By.cssSelector("[data-test='login-button']"));
    login.click();
  }

  private static void inputUsername(String username) {
    WebElement usernameElement =
        DRIVER.findElement(By.cssSelector("[data-test='username']"));
    usernameElement.sendKeys(username);
  }

}

