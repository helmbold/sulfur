[English Version](README.md)

Sulfur
======

Sulfur ist ein Werkzeug um einfach menschenlesbare Tests von Webanwendungen zu schreiben. Es handelt sich um einen It is a 
[FitNesse](http://fitnesse.org/)-Adapter, der unter der Haube [Selenium](http://docs.seleniumhq.org/) verwendet. FitNesse ist ein Wiki, um Tests in einer für Menschen leicht lesbaren Art und Weise zu schreiben. Selenium dienst dazu, den Browser zu steuern.

Mit Sulfur können Sie automatisierte GUI-Tests in dieser Art schreiben:

    !| script    | Browser |
    | lade       | http://example.com/holidays |
    | Titel ist  | Mein Urlaubsplaner |
    | klicke auf | id:datepicker|
    | klicke auf | xpath://a[@title='Weiter>'] |
    | klicke auf | link:8 |
    | klicke auf | id:submitButton|
    | Element    | id:missingNameMessage | ist sichtbar |
    | Element    | id:missingNameMessage | hat Text | Bitte geben Sie Ihren Namen ein |
    | schreibe   | Hans Meier | in | name:lastname |
    | Element    | id:missingNameMessage | ist unsichtbar |

Installation
------------

Sie benötigen Java 7, um Sulfur und FitNesse auszuführen. Installieren Sie zunächst [Java 7](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (JDK or JRE), falls es nicht bereits installiert ist.

1. Erstellen Sie ein Verzeichnis für ihr erstes Sulfur-Projekt.
2. [Laden Sie FitNesse herunter](http://fitnesse.org/FitNesseDownload) und legen Sie die Datei fitnesse-standalone.jar in das gerade angelegte Projektverzeichnis.
3. [Laden Sie Sulfur herunter](http://www.advitec.de/download/sulfur/sulfur-1.0-complete.jar) und legen Sie die Datei sulfur-1.x-complete.jar ebenfalls in das Projektverzeichnis.
4. Öffnen Sie ein Terminal, wechseln Sie in das Projektverzeichnis und geben dann ein:

        java -jar fitnesse-standalone.jar

Dadurch wird (beim ersten Aufruf) die grundlegende Wiki-Struktur erzeugt und der Wiki-Server gestartet.

5. Öffnen Sie http://localhost/FrontPage?edit in Ihrem Browser und erstzen Sie den Inhalt der angezeigten Seite durch:


        !define TEST_SYSTEM {slim} 
        !path *.jar
        !contents -R -g -p -f -h

Klicken Sie anschließend auf 'save'.

Diese Konfiguration weist FitNesses an, die SLIM-Engine zu nutzen und sagt, wo sich Bibliotheken (wie Sulfur) befinden.

Das ist alles! Sie können jetzt anfangen automatisierte GUI-Tests zu schreiben.

SetUp und TearDown einrichten
-----------------------------

Um Wiederholungen auf allen Seiten zu vermeiden, können Sie Seiten namens 'SetUp' und 'TearDown' anlegen, um etwas vor oder nach den Tests auf einer Seite auszuführen. Diese beiden Seiten werden automatisch in die Testseiten eingebunden.

Öffnen Sie http://localhost/FrontPage in Ihrem Browser und klicken Sie auf 'Add > Test page'. Geben Sie 'SetUp' im Feld 'page name' und schreiben Sie folgenden Text in den Inhaltsbereich:

    |Import|
    |de.advitec.sulfur.lang.de|

Klicken Sie auf "save", gehen Sie zurück zu http://localhost/FrontPage und fügen Sie eine weitere Seite namens 'TearDown' mit folgendem Inhalt an:

    !|script|Browser|
    |schließen|

Wenn Sie die Tests auf Englisch schreiben wollen, verwenden Sie `de.advitec.sulfur` in der Importanweisung und ersetzen Sie `schließen` durch `close`.

Tests schreiben
---------------

Öffnen Sie http://localhost/FrontPage in Ihrem Browser und klicken Sie auf 'Add > Test page'. Geben Sie der Seite einen aussagekräftigen Namen und schreiben Sie den Testcode in den Inhaltsbereich. Beachten Sie bei der Namenswahl, dass FitNesse-Seiten mitten im Namen mindestens einen Großbuchstaben erfordern (Beispiel: MeinTest).

Es gibt derzeit drei Arten von Adaptern: Browser, Listen und Tabellen. Beispiel:

    !| script | Browser |
    | get      | http://example.com/holidays |
    | title is | Mein Urlaubsplaner |
    | click on | id:datepicker|

    !| query:List | id:someElement |
    | items      |
    | 2013-08-08 |
    | 2013-08-09 |

    !| Subset query:Table | id:employees |
    | Datum      | Verbleibend | Name   | 
    | 2013-08-13 | 9           | Meyer  |
    | 2013-08-13 | 9           | Meyer2 |

Klicken Sie auf 'save'. Jetzt können Sie die Testseite ausführen, indem sie auf die Schaltfläche 'Test' klicken.

Bestimmte Elemente einer HTML-Seite werden mit Selektoren ausgewählt. In dem obigen Beispiel wird der `id`-Selektor verwendet, um Elemente anhand ihrer ID zu finden.

Sie finden alle Adaptermethoden mit Beispielen in der [API-Dokumentation](http://www.advitec.de/download/sulfur/apidocs/).

### Selektoren

Um Aktionen auf einem bestimmten HTML-Element auszuführen, müssen Sie ausdrücken können, um welches Element es sich handelt. Sie tun das durch einen der folgenden Selektoren.
<table>
 <thead>
   <tr>
     <th>Selektor</th>
     <th>Beschreibung</th>
     <th>Beispiel-Selektor</th>
     <th>passt auf Beispiel-HTML</th>
   </tr>
 </thead>
 <tbody>
   <tr>
     <td>id</td>
     <td>Attribut 'id'</td>
     <td>id:loginButton</td>
     <td>&lt;input type=&quot;submit&quot; id=&quot;loginButton&quot; /&gt;</td>
   </tr>
   <tr>
     <td>Name</td>
     <td>Attribut 'name'</td>
     <td>name:email</td><td>&lt;input type=&quot;text&quot; name=&quot;email&quot; /&gt;</td>
   </tr>
   <tr>
     <td>Tag</td>
     <td>Name des Tags</td>
     <td>tag:h1</th>
     <td>&lt;h1&gt;Beispiel&lt;/h1&gt;</td>
   </tr>
   <tr>
     <td>Link</td>
     <td>Linktext</td>
     <td>link:Anmelden</td>
     <td>&lt;a href=&quot;...&quot;&gt;Anmelden&lt;/a&gt;</td>
   </tr>
   <tr>
     <td>linkPart</td>
     <td>Teil des Linktexts</td>
     <td>linkPart:Anm</td>
     <td>&lt;a href=&quot;...&quot;&gt;Anmelden&lt;/a&gt;</td>
   </tr>
   <tr>
     <td>class</td>
     <td>Attribut CSS-Klasse</td>
     <td>class:auth</td>
     <td>&lt;a href=&quot;...&quot; class=&quot;auth&quot; &gt;Anmelden&lt;/a&gt;</td>
   </tr>
   <tr>
     <td>xpath</td>
     <td>Ausdruck, um ein XML-Element zu lokalisieren</td>
     <td>xpath://input[@alt='Search']</td>
     <td>&lt;input type=&quot;image&quot; 
       src=&quot;some.gif&quot; alt=&quot;Search&quot; title=&quot;Search&quot;&gt;</td>
   </tr>
   <tr>
     <td>css</td>
     <td>CSS-3-Selektor</td>
     <td>css:.category button</td>
     <td>&lt;div class=&quot;category&quot;&gt;&lt;button 
       class=&quot;confirmation&quot;&gt;OK&lt;/button&gt;&lt;/div&gt;</td>
   </tr>
 </tbody>
</table>

<p><a href="http://www.w3.org/TR/xpath/">XPath</a> und 
<a href="http://www.w3.org/TR/css3-selectors/">CSS 3 Selectors</a> werden detailliet auf der
W3C-Website erläutert.</p>

### Methoden des Browser-Adapaters

Sie können den Browser mit folgenden Methoden steuern:

    | browser | chrome |
    | lade            | http://example.com |
    | klicke auf      | id:someElement |
    | schreibe        | ein Wert | in | id:einElement |
    | wähle Text      | ein Wert | in | id:einElement |
    | wähle Wert      | einWert | in | id:einElement |
    | wähle Position  | 0 | in | id:einElement |
    | pausiere        | 10 | Sekunden |
    | Titel ist       | Ein Seitentitel |
    | Element         | id:einElement | hat Text | ein Text |
    | Element         | id:einElement | ist aktiv |
    | Element         | id:einElement | ist inaktiv |
    | Element         | id:einElement | ist sichtbar |
    | Element         | id:einElement | ist unsichtbar |
    | url is          | http://example.com |
    | Element         | id:einElement | hat Attribut | einAttribut | mit Wert | einWert |
    | Element         | id:einElement | hat Attribut | einAttribut | 
    | Element         | id:einElement | existiert |
    | Element         | id:einElement | existiert nicht |
    | close           |

### Installation zusätzlicher Browser-Treiber

Um andere Browser als Firefox nutzen zu können, müssen sie die entsprechenden Treiber herunterladen. Die Treiberdateien müssen in ein im Systempfad (Umgebungsvariable PATH) enthaltenes Verzeichnis gelegt werden. Und natürlich müssen die gewünschten Browser selbst installiert sein.

* Chrome: [Dokumentation](http://docs.seleniumhq.org/docs/03_webdriver.jsp#chrome-driver), [Treiber](https://code.google.com/p/chromedriver/downloads/list)
* Internet Explorer: [Dokumentation](https://code.google.com/p/selenium/wiki/InternetExplorerDriver)
* Opera: [Dokumentation](https://code.google.com/p/selenium/wiki/OperaDriver)
* iPhone: [Dokumentation](https://code.google.com/p/selenium/wiki/IPhoneDriver)
* Android: [Dokumentation](https://code.google.com/p/selenium/wiki/AndroidDriver)

Mehr Informationen über [Browser-Treiber](http://docs.seleniumhq.org/docs/03_webdriver.jsp#selenium-webdriver-s-drivers).

Sulfur ist ein Geschenk von [ADVITEC Informatik GmbH](http://www.advitec.de/softwareentwicklung/start/)

[Kontaktformular](http://www.advitec.de/cake/app/webroot/index.php) oder [mail@advitec.de](mail@advitec.de)