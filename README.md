[German Version](LIESMICH.md)

Sulfur
======

Sulfur is a tool to easily write human readable browser based GUI tests. It is a 
[FitNesse](http://fitnesse.org/) adapter using [Selenium](http://docs.seleniumhq.org/)
under the hood. FitNesse is a wiki to write tests in a human readable way, and Selenium controls the
browser.

You can write automated GUI tests with Sulfur like this:

    !| script | Browser |
    | get      | http://example.com/holidays |
    | title is | My holiday planner |
    | click on | id:datepicker|
    | click on | xpath://a[@title='Next>'] |
    | click on | link:8 |
    | click on | id:submitButton|
    | element  | id:missingNameMessage | is visible |
    | element  | id:missingNameMessage | has text | Please enter your name |
    | type     | John Miller | in | name:lastname |
    | element  | id:missingNameMessage | is invisible |

Installation
------------

You need Java 7 to run Sulfur and FitNesse. So please [download and install 
Java 7](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (JDK or JRE) if not 
already available on your machine.

1. Create a directory for your first Sulfur project.
2. [Download FitNesse](http://fitnesse.org/FitNesseDownload) and put the fitnesse-standalone.jar file in the just created project directory.
3. [Download Sulfur](http://www.advitec.de/download/sulfur/sulfur-1.0-complete.jar) and put the sulfur-1.x-complete.jar in the project directory.
4. Open a terminal, change into your project directory, and enter:

        java -jar fitnesse-standalone.jar
This will create the basic wiki structure (when executed the first time) and start the wiki server.

5. Open http://localhost/FrontPage?edit in your browser and replace the content visible on the page with

        !define TEST_SYSTEM {slim} 
        !path *.jar
        !contents -R -g -p -f -h

And click 'save'.
The above configuration tells FitNesse to use the SLIM engine and where to look for libraries (like Sulfur).

That's it! You can now start to write automated GUI tests.

Create SetUp and TearDown
-------------------------

To avoid repetition on every page, you can create pages named 'SetUp' and 'TearDown', to execute something before and after the tests on a page. These pages will be included automatically in your test pages.

Open http://localhost/FrontPage in your browser and click on 'Add > Test page'. Type 'SetUp' in the 'page name' field and put the following text in the content area:

    |Import|
    |de.advitec.sulfur|

Click save, go back to http://localhost/FrontPage and add another  page named 'TearDown' with the following content:

    !|script|Browser|
    |close|

If you want to write your tests in German, use `de.advitec.sulfur.lang.de` in the import statement, and replace `close` by `schließen`.

Writing Tests
-------------

Open http://localhost/FrontPage in your browser, and click on 'Add > Test page'. Give your page a meaningful name and write your test code into the content area. Please note that FitNesse requires at least one upper case letter in the middle of the page name (example: MyTest).

There are three kinds of adapters right now: Browser, List and Table. Example:

    !| script | Browser |
    | get      | http://example.com/holidays |
    | title is | My holiday planner |
    | click on | id:datepicker|

    !| query:List | id:someElement |
    | items      |
    | 2013-08-08 |
    | 2013-08-09 |

    !| Subset query:Table | id:employees |
    | Date       | Remaining | Name   | 
    | 2013-08-13 | 9         | Meyer  |
    | 2013-08-13 | 9         | Meyer2 |

Click save. Now you can run your test page by clicking on the 'Test' button.

You are selecting certain elements of the html page with selectors. In the example above the `id` selector is used to find elements by id. 

You can find all adapter methods with examples in the [API docs](http://www.advitec.de/download/sulfur/apidocs/).

### Selectors

To act on a certain html element, you need to express which element should be used. You do this with one of the following selectors. 
<table>
 <thead>
   <tr>
     <th>selector</th>
     <th>description</th>
     <th>example selector</th>
     <th>matches example html</th>
   </tr>
 </thead>
 <tbody>
   <tr>
     <td>id</td>
     <td>id attribute</td>
     <td>id:loginButton</td>
     <td>&lt;input type=&quot;submit&quot; id=&quot;loginButton&quot; /&gt;</td>
   </tr>
   <tr>
     <td>name</td>
     <td>name attribute</td>
     <td>name:email</td><td>&lt;input type=&quot;text&quot; name=&quot;email&quot; /&gt;</td>
   </tr>
   <tr>
     <td>tag</td>
     <td>name of tag</td>
     <td>tag:h1</th>
     <td>&lt;h1&gt;Example&lt;/h1&gt;</td>
   </tr>
   <tr>
     <td>link</td>
     <td>link text</td>
     <td>link:log in</td>
     <td>&lt;a href=&quot;...&quot;&gt;log in&lt;/a&gt;</td>
   </tr>
   <tr>
     <td>linkPart</td>
     <td>part of the link text</td>
     <td>linkPart:log</td>
     <td>&lt;a href=&quot;...&quot;&gt;log in&lt;/a&gt;</td>
   </tr>
   <tr>
     <td>class</td>
     <td>css class attribute</td>
     <td>class:auth</td>
     <td>&lt;a href=&quot;...&quot; class=&quot;auth&quot; &gt;log in&lt;/a&gt;</td>
   </tr>
   <tr>
     <td>xpath</td>
     <td>expression to locate an xml element</td>
     <td>xpath://input[@alt='Search']</td>
     <td>&lt;input type=&quot;image&quot; 
       src=&quot;some.gif&quot; alt=&quot;Search&quot; title=&quot;Search&quot;&gt;</td>
   </tr>
   <tr>
     <td>css</td>
     <td>CSS 3 selector</td>
     <td>css:.category button</td>
     <td>&lt;div class=&quot;category&quot;&gt;&lt;button 
       class=&quot;confirmation&quot;&gt;OK&lt;/button&gt;&lt;/div&gt;</td>
   </tr>
 </tbody>
</table>

<p><a href="http://www.w3.org/TR/xpath/">XPath</a> and 
<a href="http://www.w3.org/TR/css3-selectors/">CSS 3 Selectors</a> are explained in detail on the 
W3C website.</p>

### Browser Adapter Methods

You can control the browser with the following methods:

    | browser | chrome |
    | get             | http://example.com |
    | click on        | id:someElement |
    | type            | some value | in | id:someElement |
    | select text     | some value | in | id:someElement |
    | select value    | someValue | in | id:someElement |
    | select position | 0 | in | id:someElement |
    | pause           | 10 | seconds |
    | title is        | some page title |
    | element         | id:someElement | has text | some text |
    | element         | id:someElement | is active |
    | element         | id:someElement | is inactive |
    | element         | id:someElement | is visible |
    | element         | id:someElement | is invisible |
    | url is          | http://example.com |
    | element         | id:someElement | has attribute | someAttribute | with value | someValue |
    | element         | id:someElement | has attribute | someAttribute | 
    | element         | id:someElement | exists |
    | element         | id:someElement | does not exist |
    | close           |

### Installing Additional Browser Drivers

To be able to use other browsers than Firefox, you need to download the respective driver. The driver binaries needs to be placed somewhere on your system’s path. And of course you need the required browsers themselves.

* Chrome: [docs](http://docs.seleniumhq.org/docs/03_webdriver.jsp#chrome-driver),  [driver download](https://code.google.com/p/chromedriver/downloads/list)
* Internet Explorer: [docs](https://code.google.com/p/selenium/wiki/InternetExplorerDriver)
* Opera: [docs](https://code.google.com/p/selenium/wiki/OperaDriver)
* iPhone: [docs](https://code.google.com/p/selenium/wiki/IPhoneDriver)
* Android: [docs](https://code.google.com/p/selenium/wiki/AndroidDriver)

More information about [web drivers](http://docs.seleniumhq.org/docs/03_webdriver.jsp#selenium-webdriver-s-drivers).

Sulfur is brought to you by [ADVITEC Informatik GmbH](http://www.advitec.de/softwareentwicklung/start/)

[contact form](http://www.advitec.de/cake/app/webroot/index.php) or [mail@advitec.de](mail@advitec.de)