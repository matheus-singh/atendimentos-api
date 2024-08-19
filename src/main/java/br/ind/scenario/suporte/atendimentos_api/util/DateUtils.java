package br.ind.scenario.suporte.atendimentos_api.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
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

    public static LocalDate getLocalDateOfTheWeek(){
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
}
