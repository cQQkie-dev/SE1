# Software Engineering 1 - Exercise 1

## Aufgabe 1 (Wiederholung Java, Erstes Muster, Blackbox-Testing, 25 Punkte):

### 1.1

* Wie kann diese Kommunikationsverbindung nun dennoch mit Hilfe einer zusätzlichen „Fabrik“-Klasse, welche die dazu notwendige Objekt-Erzeugung übernimmt, aufgebaut werden?
    > Factory-Klasse erzeugt und liefert eine Referenz eines Translators mit einer Methode, hier z.B createGermanTranslator (könnte auch anderer Translator für andere Sprachen sein)
* In welchem Package sollte diese zusätzliche Klasse liegen?
    > Sie liegt im Package *.control mit Translator und GermanTranslator, um unnötige Imports zu verhindern
* Welches Entwurfsmuster (engl.: design pattern) könnte für die Problematik der Objekt-Erzeugung verwendet werden? Was ist der software-technische Nutzen bei der Verwendung des Entwurfsmusters?
    > Es wird das Factory Method Pattern verwendet. Das macht den Code flexibler und leichter erweiterbar (z.B ließe sich ein FrenchTranslator oder EnglishTranslator hinzufügen) und außerdem werden diese Komponenten dann alle zentral in der Factory erzeugt
* Wie muss man den Source Code des Interface ggf. anpassen, um mögliche auftretende Kompilierfehler zu beseitigen?
    > Das Interface Translator muss auf public gesetzt werden und in Client muss das Packet *.control importiert werden 

### 1.3
* Was ist der Vorteil einer separaten Test-Klasse?
    > Macht ursprünglichen Sourcecode und Testcode unabhängiger voneinander
* Was ist bei einem Blackbox-Test der Sinn von Äquivalenzklassen?
    > Zu einer Äquivalenzklasse gehören alle Werte, bei denen man annimmt, dass sich die Werte beim Test gleich verhalten und man testet daher nur einen einzigen Repräsentanten dieser Testklasse. Das reduziert die Anzahl der Testsfälle die durchgeführt werden müssen
* Warum ist ein Blackbox-Test mit JUnit auf der Klasse Client nicht unmittelbar durchführbar?
    > Die Methode display von Client hat keinen Rückgabewert auf den unmittelbar geprüft werden könnte