package br.ind.scenario.suporte.atendimentos_api.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class DateTimeUtils {
    public static String getLastWeekDateFromTodayAsString() {
        LocalDate today = LocalDate.now();
        LocalDate previousDate = today.minusDays(7);
        return previousDate.toString();
    }

    public static String getYesterdayDateAsString() {
        LocalDate today = LocalDate.now();
        LocalDate previousDate = today.minusDays(1);
        return previousDate.toString();
    }

    public static LocalDate getLocalDateOfLastWeekFirstDay(){
        return createLocalDateFromString(getLastWeekDateFromTodayAsString());
    }

    // The following method only work for ISO 8601 date formats, so you can split at 'T'
    public static LocalDate createLocalDateFromString(String dateString) {
        // Check if the input string is null or empty
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }
        try {
            // Parse the string to extract only the date part (YYYY-MM-DD)
            String datePart = dateString.split("T")[0];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(datePart, formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // This method extracts only the time part (HH:mm:ss.SSS) and converts it to a LocalTime
    public static LocalTime createLocalTimeFromString(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }
        try {
            // Extrair apenas a parte de tempo (HH:mm:ss.SSS, HH:mm:ss.SS, ou HH:mm:ss.S)
            String timePart = dateString.split("T")[1].split("Z")[0];

            // Construir o formatter para milissegundos opcionais (1 a 3 dígitos)
            DateTimeFormatter formatterWithOptionalMillis = new DateTimeFormatterBuilder()
                    .appendPattern("HH:mm:ss")
                    .optionalStart()
                    .appendFraction(ChronoField.MILLI_OF_SECOND, 1, 3, true)
                    .optionalEnd()
                    .toFormatter();

            // Tentar analisar o tempo com o formatter criado
            return LocalTime.parse(timePart, formatterWithOptionalMillis);

        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
