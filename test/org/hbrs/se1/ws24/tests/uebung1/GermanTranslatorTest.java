package org.hbrs.se1.ws24.tests.uebung1;
import static org.junit.jupiter.api.Assertions.*;
import org.hbrs.se1.ws24.exercises.uebung1.control.GermanTranslator;
import org.junit.jupiter.api.Test;

public class GermanTranslatorTest {

    @Test
    public void aTest() {
        GermanTranslator translator = new GermanTranslator();

        assertEquals("zwei" , translator.translateNumber(2));
        // Negativer Test für zu kleine Werte
        String result = "Übersetzung der Zahl -8 nicht möglich! (V " + translator.version + ")";
        assertEquals( result , translator.translateNumber(-8) );
        // Negativer Test für zu große Werte
        String result2 = "Übersetzung der Zahl 1234 nicht möglich! (V " + translator.version + ")";
        assertEquals( result2 , translator.translateNumber(1234) );
        // Negativer Test für Grenzfall 0
        String result3 = "Übersetzung der Zahl 0 nicht möglich! (V " + translator.version + ")";
        assertEquals( result3 , translator.translateNumber(0) );
    }

}