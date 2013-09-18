package de.advitec.sulfur;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Controls an actual browser. Elements are selected by locator expressions as described at
 * {@link  #find(java.lang.String)} method.
 * This class is not thread-safe.
 * @author Chelmbold
 */
public class Browser {

  static WebDriver browser;
  private static final String INVALID_SELECTOR = "Invalid selector.";

  /**
   * Creates a new Browser instance using Firefox as default.
   */
  public Browser() {
    this("firefox");
  }

  /**
   * Creates a new Browser instance using FireFox or Chrome.
   * To be able to use chrome, you have to download the ChromeDriver for your operating system, and 
   * set the environment variable 'webdriver.chrome.driver' pointing to your ChromeDriver 
   * executable.
   * @param browserName 'firefox' or 'chrome'. Upper or lower case doesn't matter.
   */
  // TODO support other browsers
  public Browser(String browserName) {
    if (browser == null) {
      if (browserName.equalsIgnoreCase("firefox")) {
        browser = new FirefoxDriver();
      } else if (browserName.equalsIgnoreCase("chrome")) {
        browser = new ChromeDriver();
      }
      browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
  }

  /**
   * Loads the given URL.
   * Example: {@code | get | http://example.com | }
   * @param url URL to be opened in the browser.
   */
  public static void get(String url) {
    browser.get(url.trim());
  }

  /**
   * Clicks on the selected element.
   * Example: {@code | click on | id:someElement |}
   * @param element Locator of the element to be used.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public void clickOn(String element) {
    find(element).click();
  }

  /**
   * Types characters into the selected element.
   * Example: {@code | type | some value | in | id:someElement | }
   * @param value Value to be typed in the selected element.
   * @param element Locator of the element to be used.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static void typeIn(String value, String element) {
    find(element).sendKeys(value);
  }
  
  /**
   * Selects a select box option by the visible text.
   * Example: {@code | select text | some value | in | id:someElement | }
   * @param value Value to be selected.
   * @param element Locator of the element to be used.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static void selectTextIn(String value, String element) {
    Select selectBox = new Select(find(element));
    selectBox.selectByVisibleText(value);
  }

  /**
   * Selects a select box option by key.
   * Example: {@code | select value | someValue | in | id:someElement | }
   * @param value Value of the value attribute of the option to be selected.
   * @param element Locator of the element to be used.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static void selectValueIn(String value, String element) {
    Select selectBox = new Select(find(element));
    selectBox.selectByValue(value);
  }
 
  /**
   * Selects a select box option by position.
   * Example: {@code | select position | 0 | in | id:someElement | }
   * @param index Index (starting at 0) of the of the option to be selected.
   * @param element Locator of the element to be used.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static void selectPositionIn(int index, String element) {
    Select selectBox = new Select(find(element));
    selectBox.selectByIndex(index);
  }

  /**
   * Pauses this fitnesse execution thread for x seconds.
   * Example: {@code | pause | 10 | seconds |}
   * @param seconds Duration.
   */
  public static void pauseSeconds(int seconds) {
    try {
      Thread.sleep(1000L * seconds);
    } catch (InterruptedException ex) {
      Logger.getLogger(Browser.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  // TODO method to open modal dialoag in Browser to be able to attach debugger

  /**
   * Closes the browser.
   * Example: {@code | close |}
   */
  public static void close() {
    browser.quit();
    browser = null;
  }

  /**
   * Checks the page title.
   * Example: {@code | title is | some page title | }
   * @param value Expected page title
   * @return true, if the page title equals the expected title.
   */
  public static boolean titleIs(String value) {
    return browser.getTitle().equals(value);
  }

  /**
   * Checks whether the selected element has the expected text.
   * Example: {@code | element | id:someElement | has text | some text |}
   * @param element Locator of the element to be used.
   * @param text Expected text.
   * @return true, if the element has the expected text.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static boolean elementHasText(String element, String text) {
    return find(element).getText().equals(text);
  }

  /**
   * Checks whether the selected element is active.
   * Example: {@code | element | id:someElement | is active | }
   * @param element Locator of the element to be used.
   * @return true, if the element is active.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static boolean elementIsActive(String element) {
    return find(element).isEnabled();
  }

  /**
   * Checks whether the selected element is inactive.
   * Example: {@code | element | id:someElement | is inactive | }
   * @param element Locator of the element to be used.
   * @return true, if the element is inactive
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static boolean elementIsInactive(String element) {
    return !elementIsActive(element);
  }

  /**
   * Checks whether the selected element is visible.
   * Example: {@code | element | id:someElement | is visible | }
   * @param element Locator of the element to be used.
   * @return true, if the element is visible.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static boolean elementIsVisible(String element) {
    return find(element).isDisplayed();
  }
  
  /**
   * Checks whether the selected element is invisible.
   * Example: {@code | element | id:someElement | is invisible | }
   * @param element Locator of the element to be used.
   * @return true, if the element is invisible.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static boolean elementIsInvisible(String element) {
    return !elementIsVisible(element);
  }

  /**
   * Checks whether the selected element has an attribute with the expected value.
   * Example: 
   * {@code | element | id:someElement | has attribute | someAttribute | with value | someValue | }
   * @param element Locator of the element to be used.
   * @param attribute Attribute to be checked.
   * @param value Expected attribute value.
   * @return true, if the element has an attribute with the expected value.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static boolean elementHasAttributeWithValue(String element, String attribute, String value) {
    return find(element).getAttribute(attribute).equals(value);
  }

  /**
   * Checks whether the selected element has an attribute.
   * Example: {@code | element | id:someElement | has attribute | someAttribute | }
   * @param element Locator of the element to be used.
   * @param attribute Attribute to be checked.
   * @return true, if the element has an attribute with the given name.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static boolean elementHasAttribute(String element, String attribute) {
    return find(element).getAttribute(attribute) != null;
  }

  /**
   * Checks whether the element exists.
   * Example: {@code | element | id:someElement | exists | }
   * @param element Locator of the element to be looked up.
   * @return true, if the element exists.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static boolean elementExists(String element) {
    try {
      find(element);
      return true;
    } catch (NoSuchElementException _) {
      return false;
    }
  }

  /**
   * Checks whether the element doesn't exist.
   * Example: {@code | element | id:someElement | does not exist | }
   * @param element Locator of the element to be looked up.
   * @return true, if the element doesn't exist.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static boolean elementDoesNotExist(String element) {
    return !elementExists(element);
  }
  
  /**
   * Checks whether the current url is the given url.
   * Example: {@code | url is | http://example.com | }
   * @param url Expected URL.
   * @return true, if the expected URL equals the actual URL.
   */
  public static boolean urlIs(String url) {
    return browser.getCurrentUrl().equals(url);
  }

  

  /**
   * Finds the WebElement by the given selector. This method is used internally wherever an 
   * 'element' parameter takes a selector expression. The selector type (eg. 'xpath') is separated
   * from its value by a colon.
   * @param selector one of the following selector types
   * <table>
   *  <thead>
   *    <tr>
   *      <th>selector</th>
   *      <th>description</th>
   *      <th>example selector</th>
   *      <th>matches example html</th>
   *    </tr>
   *  </thead>
   *  <tbody>
   *    <tr>
   *      <td>id</td>
   *      <td>id attribute</td>
   *      <td>id:loginButton</td>
   *      <td>&lt;input type=&quot;submit&quot; id=&quot;loginButton&quot; /&gt;</td>
   *    </tr>
   *    <tr>
   *      <td>name</td>
   *      <td>name attribute</td>
   *      <td>name:email</td><td>&lt;input type=&quot;text&quot; name=&quot;email&quot; /&gt;</td>
   *    </tr>
   *    <tr>
   *      <td>tag</td>
   *      <td>name of tag</td>
   *      <td>tag:h1</th>
   *      <td>&lt;h1&gt;Example&lt;/h1&gt;</td>
   *    </tr>
   *    <tr>
   *      <td>link</td>
   *      <td>link text</td>
   *      <td>link:log in</td>
   *      <td>&lt;a href=&quot;...&quot;&gt;log in&lt;/a&gt;</td>
   *    </tr>
   *    <tr>
   *      <td>linkPart</td>
   *      <td>part of the link text</td>
   *      <td>linkPart:log</td>
   *      <td>&lt;a href=&quot;...&quot;&gt;log in&lt;/a&gt;</td>
   *    </tr>
   *    <tr>
   *      <td>class</td>
   *      <td>css class attribute</td>
   *      <td>class:auth</td>
   *      <td>&lt;a href=&quot;...&quot; class=&quot;auth&quot; &gt;log in&lt;/a&gt;</td>
   *    </tr>
   *    <tr>
   *      <td>xpath</td>
   *      <td>expression to locate an xml element</td>
   *      <td>xpath://input[@alt='Search']</td>
   *      <td>&lt;input type=&quot;image&quot; 
   *        src=&quot;some.gif&quot; alt=&quot;Search&quot; title=&quot;Search&quot;&gt;</td>
   *    </tr>
   *    <tr>
   *      <td>css</td>
   *      <td>CSS 3 selektor</td>
   *      <td>css:.category button</td>
   *      <td>&lt;div class=&quot;category&quot;&gt;&lt;button 
   *        class=&quot;confirmation&quot;&gt;OK&lt;/button&gt;&lt;/div&gt;</td>
   *    </tr>
   *  </tbody>
   * </table>
   *
   * @return found element
   * @throws IllegalArgumentException if the specified element is not found.
   * @see <a href="http://www.w3.org/TR/xpath/">XPath</a>
   * @see <a href="http://www.w3.org/TR/css3-selectors/">CSS 3 Selectors</a>
   */
  public static WebElement find(String selector) {
    final String selectorValue = getSelectorValue(selector);
    By locator = null;
    if (selector.startsWith("id:")) {
      locator = By.id(selectorValue);
    } else if (selector.startsWith("name:")) {
      locator = By.name(selectorValue);
    } else if (selector.startsWith("tag:")) {
      locator = By.tagName(selectorValue);
    } else if (selector.startsWith("link:")) {
      locator = By.linkText(selectorValue);
    } else if (selector.startsWith("linkPart:")) {
      locator = By.partialLinkText(selectorValue);
    } else if (selector.startsWith("class:")) {
      locator = By.className(selectorValue);
    } else if (selector.startsWith("xpath:")) {
      locator = By.xpath(selectorValue);
    } else if (selector.startsWith("css:")) {
      locator = By.cssSelector(selectorValue);
    } else {
      throw new IllegalArgumentException(INVALID_SELECTOR);
    }
    return browser.findElement(locator);
  }

  private static String getSelectorValue(String selector) {
    String[] parts = selector.split(":", 2);
    if (parts.length != 2) {
      throw new IllegalArgumentException(INVALID_SELECTOR);
    }
    return parts[1];
  }
}