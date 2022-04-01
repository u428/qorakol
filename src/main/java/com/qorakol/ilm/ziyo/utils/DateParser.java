package com.qorakol.ilm.ziyo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    private static SimpleDateFormat dateAndTimeMysqlFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private static SimpleDateFormat dateUzbekistan = new SimpleDateFormat("dd.MM.yyyy");

    /**
     * Try to parse the date from the string
     * @param dateAsString
     * @param dateFormat
     * @return Returns Date if successfull or null on failure.
     */
    public static Date TryParse(String dateAsString, SimpleDateFormat dateFormat) {
        try{
            return dateFormat.parse(dateAsString);
        }
        catch (Exception ignored) {

        }
        return null;
    }

    public static String FormatToDateTime(Date dateAsString) {
        try{
            return dateAndTimeMysqlFormat.format(dateAsString);
        }
        catch (Exception ignored) {

        }
        return null;
    }

    public static String FormatToDate(Date dateAsString) {
        try{
            return dateUzbekistan.format(dateAsString);
        }
        catch (Exception ignored) {

        }
        return null;
    }

}
