package de.advitec.sulfur.lang.de;

import de.advitec.sulfur.*;
import java.util.List;

/**
 * Prüft den Inhalt einer Tabelle. Die zu prüfende 
 * <a href="http://www.w3schools.com/tags/tag_thead.asp">HTML-Tabelle muss thead und tbody korrekt 
 * definieren</a>.
 * Example:
 * <pre>
 * {@literal 
 * !| Subset query:Tabelle | id:employees |
 * | Datum      | Rest | Name   | 
 * | 2013-08-13 | 9    | Meyer  |
 * | 2013-08-13 | 9    | Meyer2 |
 * }
 * </pre>
 * @author Chelmbold
 */
public class Tabelle {

  private Table internalTable;

  /**
   * @param element Selektor auf die zu prüfende Tabelle.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public Tabelle(String element) {
    internalTable = new Table(element);
  }

  public List<List<List<String>>> query() {
    return internalTable.query();
  }
}
