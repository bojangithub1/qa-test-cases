package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Objects;

public class UiBaseTest {

  protected static ChromeDriver DRIVER;

  @BeforeEach
  void setup() {
    DRIVER = setupDriver();
  }

  @AfterEach
  void cleanup() {
    DRIVER.quit();
    DRIVER = null;
  }

  protected static ChromeDriver setupDriver() {
    if (Objects.nonNull(DRIVER)) {
      return DRIVER;
    }

    // Set chromedriver binary
    // TODO: set this for yourself
    System.setProperty("webdriver.chrome.driver",
        "chromedriver/mac_arm-120.0.6099.71/chromedriver-mac-arm64/chromedriver");

    // Set chrome binary
    ChromeOptions options = new ChromeOptions();
    // TODO: set this for yourself
    options.setBinary(
        "chrome/mac_arm-120.0.6099.71/chrome-mac-arm64/Google Chrome for Testing.app/Contents/MacOS/Google Chrome for Testing");

    return new ChromeDriver(options);
  }

  protected static void getIndex() {
    DRIVER.get("https://www.saucedemo.com/");
  }

  protected static void inputPassword(String password) {
    WebElement passwordElement =
        DRIVER.findElement(By.cssSelector("[data-test='password']"));
    passwordElement.sendKeys(password);
  }

  protected static void login() {
    WebElement login =
        DRIVER.findElement(By.cssSelector("[data-test='login-button']"));
    login.click();
  }

  protected static void inputUsername(String username) {
    WebElement usernameElement =
        DRIVER.findElement(By.cssSelector("[data-test='username']"));
    usernameElement.sendKeys(username);
  }

  protected static void loginSuccessfully() {
    inputUsername("standard_user");
    inputPassword("secret_sauce");
    login();
  }

}
