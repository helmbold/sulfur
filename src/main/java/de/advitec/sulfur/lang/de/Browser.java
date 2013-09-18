package de.advitec.sulfur.lang.de;

/**
 * Steuert den eigentlichen Browser. Elemente werden durch Selektorausdrücke wie bei der Methode
 * {@link  de.advitec.sulfur.Browser#find(java.lang.String)} beschrieben angesprochen.
 * @author Chelmbold
 */
public class Browser {

  private static de.advitec.sulfur.Browser internalBrowser;


  public Browser() {
    internalBrowser = new de.advitec.sulfur.Browser();
  }

  /**
   * Erzeugt einen neuen Browser und verwendet Firefox oder Chrome. Um Chrome nutzen zu können,
   * müssen sie den ChromeDriver für Ihr Betriebssystem installieren und die Umgebungsvariable
   * 'webdriver.chrome.driver', die auf die ChromeDriver-Datei verweist, setzen.
   *
   * @param browserName 'firefox' oder 'chrome'
   */
  public Browser(String browserName) {
    internalBrowser = new de.advitec.sulfur.Browser(browserName);
  }

  /**
   * Öffnet die gegebene Adresse. Beispiel: {@code | lade | http://example.com | }
   *
   * @param url URL, die im Browser geladen werden soll.
   */
  public static void lade(String url) {
    internalBrowser.get(url);
  }

  /**
   * Klickt auf das gewählte Element. Beispiel: {@code | klicke auf | id:einElement |}
   *
   * @param element Selektor auf das zu verwendende Element. 
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static void klickeAuf(String element) {
    internalBrowser.clickOn(element);
  }

  /**
   * Schreibt Zeichen in ein Eingabeelement. 
   * Beispiel: {@code | schreibe | eine Zeichenkette | in | id:einElement | }
   *
   * @param wert Einzugebender Wert.
   * @param element Selektor auf das zu verwendende Element.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static void schreibeIn(String wert, String element) {
    internalBrowser.typeIn(wert, element);
  }
  
  /**
   * Wählt eine Option eines Auswahlfelds anhand des Texts aus. +
   * Beispiel: {@code | wähle Text | eine Beschriftung | in | id:einElement | }
   *
   * @param text Auszuwählender Wert
   * @param element Selektor auf das zu verwendende Element.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static void wähleTextIn(String text, String element) {
    internalBrowser.selectTextIn(text, element);
  }

  /**
   * Wählt eine Option eines Auswahlfelds anhand des Werts aus. 
   * Beispiel: {@code | wähle Wert | ein Wert | in | id:einElement | }
   *
   * @param wert Wert der auszuwählenden Option.
   * @param element Selektor auf das zu verwendende Element.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static void wähleWertIn(String wert, String element) {
    internalBrowser.selectValueIn(wert, element);
  }

  /**
   * Wählt eine Option eines Auswahlfelds anhand der Position aus.
   * Beispiel: {@code | wähle Position | 0 | in | id:einElement | }
   *
   * @param index Index (beginnend bei 0) der auszuwählenden Option.
   * @param element Selektor auf das zu verwendende Element.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static void wählePositionIn(int index, String element) {
    internalBrowser.selectPositionIn(index, element);
  }

  /**
   * Pausiert die Ausführung für x Sekunden.
   * Beispiel: {@code | pausiere | 10 | Sekunde | }
   * 
   * @param sekunden Duration.
   */
  public static void pausiereSekunden(int sekunden) {
    internalBrowser.pauseSeconds(sekunden);
  }

  /**
   * Schließt den Browser. Beispiel: {@code | schließe | }
   */
  public static void schließen() {
    internalBrowser.close();
  }

  /**
   * Prüft den Seitentitel. Beispiel: {@code | Titel ist | Ein Seitentitel | }
   *
   * @param text Erwarteter Seitentitel.
   * @return true, wenn der tatsächliche dem erwarteten Seitentitel entspricht.
   */
  public static boolean TitelIst(String text) {
    return internalBrowser.titleIs(text);
  }

  /**
   * Prüft, ob das gewählte Element den erwarteten Text enthält. Beispiel:
   * {@code | Element | id:einElement | hat Text | ein Text |}
   *
   * @param element Selektor auf das zu verwendende Element.
   * @param text Erwarteter Text.
   * @return true, wenn der tatsächliche dem erwarteten Text entspricht.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static boolean ElementHatText(String element, String text) {
    return internalBrowser.elementHasText(element, text);
  }

  /**
   * Prüft, ob das gewählte Element aktiv ist. 
   * Beispiel: {@code | Element | id:einElement | ist aktiv | }
   *
   * @param element Selektor auf das zu verwendende Element.
   * @return true, wenn das Element aktiv ist.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static boolean ElementIstAktiv(String element) {
    return internalBrowser.elementIsActive(element);
  }

  /**
   * Prüft, ob das gewählte Element inaktiv ist. 
   * Beispiel: {@code | Element | id:einElement | ist inaktiv | }
   *
   * @param element Selektor auf das zu verwendende Element.
   * @return true, wenn das Element inaktiv ist.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static boolean ElementIstInaktiv(String element) {
    return internalBrowser.elementIsInactive(element);
  }

  /**
   * Prüft, ob das gewählte Element sichtbar ist. 
   * Beispiel: {@code | Element | id:einElement | ist sichtbar | }
   *
   * @param element Selektor auf das zu verwendende Element.
   * @return true, wenn das Element sichtbar ist
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static boolean ElementIstSichtbar(String element) {
    return internalBrowser.elementIsVisible(element);
  }

  /**
   * Prüft, ob das gewählte Element unsichtbar ist.
   * Beispiel: {@code | Element | id:einElement | ist unsichtbar | }
   *
   * @param element Selektor auf das zu verwendende Element.
   * @return true, wenn das Element unsichtbar ist.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static boolean ElementIstUnsichtbar(String element) {
    return internalBrowser.elementIsInvisible(element);
  }

  /**
   * Prüft, ob die tatsächliche der erwarteten Adresse entspricht. 
   * Beispiel: {@code | Adresse ist | http://example.com | }
   *
   * @param url Erwartete Adresse.
   * @return true, wenn die Adresse übereinstimmt.
   */
  public static boolean AdresseIst(String url) {
    return internalBrowser.urlIs(url);
  }

  /**
   * Prüft, ob das gewählte Element ein Attribut mit dem gegebenen Wert hat.
   * Beispiel: {@code | Element | id:einElement | hat Attribut | einAttribut | mit Wert | einWert |}
   *
   * @param element Selektor auf das zu verwendende Element.
   * @param attribut Zu prüfendes Attribut.
   * @param wert Erwarteter Attributwert.
   * @return true, wenn das Element ein Attribut mit dem erwarteten Wert hat.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static boolean ElementHatAttributMitWert(String element, String attribut, String wert) {
    return internalBrowser.elementHasAttributeWithValue(element, attribut, wert);
  }

  /**
   * Prüft, ob das gewählte Element das gegebene Attribut hat. 
   * Beispiel: {@code | Element | id:einElement | hat Attribut | einAttribut | }
   *
   * @param element Selektor auf das zu verwendende Element.
   * @param attribut Zu prüfendes Attribut.
   * @return true, wenn das gewählte Element das gegebene Attribut hat.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static boolean ElementHatAttribut(String element, String attribut) {
    return internalBrowser.elementHasAttribute(element, attribut);
  }

  /**
   * Prüft, ob das gewählte Element exisitert. 
   * Beispiel: {@code | Element | id:einElement | existiert | }
   *
   * @param element Selektor auf das zu suchende Element.
   * @return true, wenn das Element existiert.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static boolean ElementExistiert(String element) {
    return internalBrowser.elementExists(element);
  }

  /**
   * Prüft, ob das gewählte Element exisitert.
   * Beispiel: {@code | Element | id:einElement | existiert nicht | }
   *
   * @param element Selektor auf das zu suchende Element.
   * @return true, wenn das Element nicht existiert.
   * @see de.advitec.sulfur.Browser#find(java.lang.String)
   */
  public static boolean ElementExistiertNicht(String element) {
    return internalBrowser.elementDoesNotExist(element);
  }
}