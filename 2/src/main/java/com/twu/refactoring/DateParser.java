package com.twu.refactoring;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class DateParser {
    private final String dateAndTimeString;
    private static final HashMap<String, TimeZone> KNOWN_TIME_ZONES;
    private final String YEAR_LESS4_EXCEPTION;
    private final String YEAR_NOT_INTEGER;
    private final String YEAR_RANGE_ERROR;
    private final String MONTH_LESS2_EXCEPTION;
    private final String MONTH_NOT_INTEGER;
    private final String MONTH_RANGE_ERROR;
    private final String DATE_LESS2_EXCEPTION;
    private final String DATE_NOT_INTEGER;
    private final String DATE_RANGE_ERROR;
    private final String HOUR_LESS2_EXCEPTION;
    private final String HOUR_NOT_INTEGER;
    private final String HOUR_RANGE_ERROR;
    private final String MINUTE_LESS2_EXCEPTION;
    private final String MINUTE_NOT_INTEGER;
    private final String MINUTE_RANGE_ERROR;
    {
        MINUTE_RANGE_ERROR = "Minute cannot be less than 0 or more than 59";
        MINUTE_NOT_INTEGER = "Minute is not an integer";
        MINUTE_LESS2_EXCEPTION = "Minute string is less than 2 characters";
        HOUR_RANGE_ERROR = "Hour cannot be less than 0 or more than 23";
        HOUR_NOT_INTEGER = "Hour is not an integer";
        HOUR_LESS2_EXCEPTION = "Hour string is less than 2 characters";
        DATE_RANGE_ERROR = "Date cannot be less than 1 or more than 31";
        DATE_NOT_INTEGER = "Date is not an integer";
        DATE_LESS2_EXCEPTION = "Date string is less than 2 characters";
        YEAR_RANGE_ERROR = "Year cannot be less than 2000 or more than 2012";
        YEAR_NOT_INTEGER = "Year is not an integer";
        YEAR_LESS4_EXCEPTION = "Year string is less than 4 characters";
        MONTH_LESS2_EXCEPTION = "Month string is less than 2 characters";
        MONTH_NOT_INTEGER = "Month is not an integer";
        MONTH_RANGE_ERROR = "Month cannot be less than 1 or more than 12";

    }

    static {
        KNOWN_TIME_ZONES = new HashMap<String, TimeZone>();
        KNOWN_TIME_ZONES.put("UTC", TimeZone.getTimeZone("UTC"));
    }

    /**
     * Takes a date in ISO 8601 format and returns a date
     *
     * @param dateAndTimeString - should be in format ISO 8601 format
     *                          examples -
     *                          2012-06-17 is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17TZ is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17T15:00Z is 17th June 2012 - 15:00 in UTC TimeZone
     */
    public DateParser(String dateAndTimeString) {
        this.dateAndTimeString = dateAndTimeString;
    }

    public Date parse() {
        int year, month, date, hour, minute;
        year = getYearOrMonthDateOrHourOrMinute(0,4,YEAR_LESS4_EXCEPTION,YEAR_NOT_INTEGER,YEAR_RANGE_ERROR,2012,2000);
        month = getYearOrMonthDateOrHourOrMinute(5,7,MONTH_LESS2_EXCEPTION,MONTH_NOT_INTEGER,MONTH_RANGE_ERROR,12,1);
        date = getYearOrMonthDateOrHourOrMinute(8,10,DATE_LESS2_EXCEPTION,DATE_NOT_INTEGER,DATE_RANGE_ERROR,31,1);
        hour = getYearOrMonthDateOrHourOrMinute(11,13,HOUR_LESS2_EXCEPTION,HOUR_NOT_INTEGER,HOUR_RANGE_ERROR,23,0);
        minute = getYearOrMonthDateOrHourOrMinute(14,16,MINUTE_LESS2_EXCEPTION,MINUTE_NOT_INTEGER,MINUTE_RANGE_ERROR,59,0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(year, month - 1, date, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public int getYearOrMonthDateOrHourOrMinute(int begin,int end,String describe1,String describe2,String describe3,int maximum,int minimum){
        int result;
        if((begin==14&&end==16)||(begin==11&&end==13)){
            if(dateAndTimeString.charAt(11) == 'Z'){
                return 0;
            }
        }
        try {
            String yearString = dateAndTimeString.substring(begin, end);
            result = Integer.parseInt(yearString);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(describe1);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(describe2);
        }
        if (result > maximum || result < minimum)
            throw new IllegalArgumentException(describe3);

        return result;
    }
}
