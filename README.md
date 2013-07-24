Sulfur
======

*Selenium adapter for FitNesse - browser based GUI tests*

Sulfur is a [FitNesse](http://fitnesse.org/) adapter using [Selenium](http://docs.seleniumhq.org/)
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

1. Create a directory for your Sulfur project.
2. [Download FitNesse](http://fitnesse.org/FitNesseDownload) and put the fitnesse-standalone.jar file in the just created project directory.
3. [Download Sulfur](https://github.com/helmbold/sulfur/tree/master/target) and put the sulfur-1.x-complete.jar in the project directory.

        java -jar fitnesse-standalone.jar
This will create the basic wiki structure (when executed the first time) and start the wiki server.

4. Open http://localhost/FrontPage?edit in your browser and replace the content with

        !define TEST_SYSTEM {slim} 
        !path *.jar
        !contents -R -g -p -f -h
And click 'save'.
The above configuration tells FitNesse to use the SLIM engine and where to look for libraries.

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

Open http://localhost/FrontPage in your browser, and click on 'Add > Test page'. Give your page a meaningful name and write your test code into the content area.

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

You can find all adapter methods with examples in the API docs.

TODO
----
* describe installation of additional drivers (chrome ...)
* list all adapter methods
* explain selectors
