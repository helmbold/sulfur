package de.advitec.sulfur.lang.de;

import de.advitec.sulfur.*;

/**
 * Prüft Listen, die mit {@literal <li>} definiert sind. Beispiel:
 * <pre>
 *  {@literal 
 * !| query:Liste | id:someElement |
 *  | Einträge   |
 *  | 2013-08-08 |
 *  | 2013-08-09 |
 * }
 * </pre>
 * Denken Sie an die Spaltenüberschrift 'Einträge', um FitNesse zufriedenzustellen. 
 * Siehe <a href="http://fitnesse.org/FitNesse.UserGuide.SliM.QueryTable">QueryTable</a>
 * @author Chelmbold
 */
public class Liste {

  private List internalList;

  /**
   * Erzeugt eine Liste mit dem Spaltennamen 'Einträge'.
   * @param element Selektor auf die zu prüfende Liste.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public Liste(String element) {
    internalList = new List(element, "Einträge");
  }

  public java.util.List<java.util.List<java.util.List<String>>> query() {
    return internalList.query();
  }
}
