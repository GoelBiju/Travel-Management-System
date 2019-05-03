package com.example.customermobileapplication.Utilities.API;

import android.content.SharedPreferences;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// Goel Biju

public class Helpers {

    // Format the Date ("2019-04-27T09:00:00") to String (27/04/2019 - hh:mm)
    // using API_DATETIME_PATTERN and GENERIC_DATETIME_PATTERN.
    public static String API_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static String GENERIC_DATETIME_PATTERN = "dd/MM/yyyy - hh:mm";
    public static String DATE_ONLY_PATTERN = "dd/MM/yyyy";

    public static String formatDateTime(Date inputDate) {
        DateFormat dateFormat = new SimpleDateFormat(GENERIC_DATETIME_PATTERN, Locale.UK);
        return dateFormat.format(inputDate);
    }

    public static String formatAPIDateTime(String dateTime) {
        return formatDateTime(dateTime, API_DATETIME_PATTERN, GENERIC_DATETIME_PATTERN);
    }

    public static Date toAPIDateTime(String dateTime) {
        Date formattedDate = new Date();

        try {
            formattedDate = (new SimpleDateFormat(API_DATETIME_PATTERN, Locale.UK)).parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }

    public static String formatDateOnly (Date inputDate) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_ONLY_PATTERN, Locale.UK);
        return dateFormat.format(inputDate);
    }

    private static String formatDateTime(String dateTime, String inputPattern, String outputPattern) {
        String formattedDateTime = "";

        try {
            Date initDate = new SimpleDateFormat(inputPattern, Locale.UK).parse(dateTime);
            SimpleDateFormat formatter = new SimpleDateFormat(outputPattern, Locale.UK);
            formattedDateTime = formatter.format(initDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDateTime;
    }
}
