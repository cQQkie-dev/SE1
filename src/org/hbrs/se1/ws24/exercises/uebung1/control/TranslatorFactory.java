package org.hbrs.se1.ws24.exercises.uebung1.control;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.LocalDate;

public class TranslatorFactory {
    public static Translator createGermanTranslator(){
        GermanTranslator translator = new GermanTranslator();
        translator.setDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return translator;
    }

}