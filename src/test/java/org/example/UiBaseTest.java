package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Objects;

public class UiBaseTest {

  protected static ChromeDriver DRIVER;

  @BeforeAll
  static void setup() {
    DRIVER = setupDriver();
  }

  @AfterAll
  static void cleanup() {
    DRIVER.quit();
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

}
