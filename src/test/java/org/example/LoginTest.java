package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

public class LoginTest extends UiBaseTest {

  private static Stream<Arguments> loginWithUsernameAndPasswordInputs() {
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
  @MethodSource("loginWithUsernameAndPasswordInputs")
  void testLoginWithUsernameAndPassword(String displayName, String username,
                                        String password,
                                        boolean successfulLogin) {
    getIndex();

    inputUsername(username);
    inputPassword(password);
    login();

    if (!successfulLogin) {
      WebElement error = findByDataTest("error");
      Assertions.assertEquals(
          "Epic sadface: Username and password do not match any user in this service",
          error.getText());
    }

    Assertions.assertEquals(successfulLogin,
        "https://www.saucedemo.com/inventory.html".equals(
            DRIVER.getCurrentUrl()));
  }


  private static Stream<Arguments> loginWithMissingUsernameOrPasswordInputs() {
    return Stream.of(
        Arguments.of("missing username", "", "secret_sauce"),
        Arguments.of("missing password", "standard_user", "")
    );
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("loginWithMissingUsernameOrPasswordInputs")
  void testLoginWithMissingUsernameOrPassword(String displayName,
                                              String username,
                                              String password) {
    getIndex();

    inputUsername(username);
    inputPassword(password);

    login();

    Assertions.assertTrue(findByDataTest("error").isDisplayed());
  }

}

