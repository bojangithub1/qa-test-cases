package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

public class LinkTest extends UiBaseTest {

  private static Stream<Arguments> linksInputs() {
    return Stream.of(
        Arguments.of("Twitter", "https://twitter.com/saucelabs"),
        Arguments.of("Facebook", "https://www.facebook.com/saucelabs"),
        Arguments.of("LinkedIn", "https://www.linkedin.com/company/sauce-labs/")
    );
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("linksInputs")
  void testLinks(String linkText, String url) {
    getIndex();

    loginSuccessfully();

    WebElement link = DRIVER.findElement(By.linkText(linkText));
    link.click();

    // There are two window handles, switch to second.
    String secondWindowHandle =
        DRIVER.getWindowHandles().stream().toList().get(1);
    DRIVER.switchTo().window(secondWindowHandle);

    Assertions.assertEquals(url, DRIVER.getCurrentUrl());
  }
}
