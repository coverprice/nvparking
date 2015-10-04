package com.jamesrussell1911.noevalleyparking;

import java.util.Calendar;

/**
 * Created by James on 2015-10-03.
 */
public class CalendarUtils {
    protected Calendar c;

    public CalendarUtils() {
        c = Calendar.getInstance();
    }

    /**
     * Returns an integer representing the current day of the week. Calendar.[DAY_NAME].
     */
    public int getDayOfWeek() {
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Returns whether the week is an even or odd one, according to the San Francisco Public Works
     * definition of it where days 1-7 = week 1, 8-14 = week 2, etc.
     * (This may go against some people's intuition, because most people think of a week as
     * tarting on Sunday or Monday. In SFPW terms, a weekcould potentially start on any day
     * of the week)
     *
     * @return - true if the week is week 1 or 3 or 5, false otherwise.
     */
    public boolean getIsWeekOdd() {
        int day_of_month = c.get(Calendar.DAY_OF_MONTH);
        return (((day_of_month - 1) / 7) % 2 == 0);
    }
}
