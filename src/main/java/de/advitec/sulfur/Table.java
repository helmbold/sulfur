package de.advitec.sulfur;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Checks the contents of a table. The checked <a href="http://www.w3schools.com/tags/tag_thead.asp">
 * html table must define thead and tbody correctly</a>.
 * Example:
 * <pre>
 * {@literal 
 * !| Subset query:Table | id:employees |
 * | Date       | Remaining | Name   | 
 * | 2013-08-13 | 9         | Meyer  |
 * | 2013-08-13 | 9         | Meyer2 |
 * }
 * </pre>
 * @author Chelmbold
 */
public class Table {

  String element;

  /**
   * @param element Locator of the table to be used.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public Table(String element) {
    this.element = element;
  }
  
  /**
   * Funktioniert nur mit Tabellen, die thead und tbody korrekt verwenden.
   */
  public List<List<List<String>>> query() {
    WebElement table = Browser.find(element);
    List<String> headers = new ArrayList<>();
    for (WebElement cell : table.findElements(By.xpath("./thead/tr/th"))) {
      headers.add(cell.getText());
    }
    List<List<List<String>>> rows = new ArrayList<>();
    for (WebElement row : table.findElements(By.xpath("./tbody/tr"))) {
      List<WebElement> cellsOfRow = row.findElements(By.xpath("./td"));
      List<List<String>> columns = new ArrayList<>();
      for (int i = 0; i < cellsOfRow.size(); i++) {
        if (cellsOfRow.get(i) != null) {
          ArrayList<String> column = new ArrayList<>();
          column.add(headers.get(i));
          column.add(cellsOfRow.get(i).getText());
          columns.add(column);
        }
      }
      rows.add(columns);
    }
    return rows;
  }
}
