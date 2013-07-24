package de.advitec.sulfur;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Checks lists defined with {@literal <li>}. Example:
 * <pre>
 *  {@literal 
 * !| query:List | id:someElement |
 *  | items      |
 *  | 2013-08-08 |
 *  | 2013-08-09 |
 * }
 * </pre>
 * Don't forget the 'items' header, to make FitNesse happy. 
 * See <a href="http://fitnesse.org/FitNesse.UserGuide.SliM.QueryTable">QueryTable</a>
 * @author Chelmbold
 */
public class List {

  private final String element;
  private final String headerName;

  /**
   * Constructs a list using 'items' as default column name.
   * @param element Locator of the list to be used.
   */
  public List(String element) {
    this(element, "items");
  }
  
  /**
   * This constructor is only needed for translation to other languages than english.
   * @param element Locator of the list to be used.
   * @param headerName Name of the column (required by fitnesse).
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public List(String element, String headerName) {
    this.element = element;
    this.headerName = headerName;
  }

  public java.util.List<java.util.List<java.util.List<String>>> query() {
    WebElement list = Browser.find(element);
    java.util.List<java.util.List<java.util.List<String>>> rows = new ArrayList<>();
    for (final WebElement item : list.findElements(By.xpath("./li"))) {
      rows.add(new ArrayList<java.util.List<String>>() {
        {
          add(new ArrayList<String>() {
            {
              add(headerName);
              add(item.getText());
            }
          });
        }
      });
    }
    return rows;
  }
}
