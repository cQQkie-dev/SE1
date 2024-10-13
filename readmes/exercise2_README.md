# Software Engineering 1 - Exercise 2

## Aufgabe 2 (Intensivierung Java und Objektorientierung)

### FA2:

* Falls zu der übergebenen ID kein Member-Objekt gespeichert ist, sollte über einen von ihnen freiwählbaren Rückgabewert eine entsprechende Fehlermeldung ausgegeben werden. Welche Nachteile ergeben sich aus ihrer Sicht für ein solchen Fehlerhandling gegenüber einer Lösung mit Exceptions? Kurzes Statement!
    > Fehlerbehandlung mit Rückgabewert anstatt mit Exceptions führt zu weniger einheitlich strukturierten Codebasis mit Custom-Lösungen, die schwerer wartbar sind. Rückgabewerte müssen dauern überprüft werden => unübersichtlich.
    > Exceptions führen zur klarer Trennung zw. Fehlerbehandlung und Programmfluss