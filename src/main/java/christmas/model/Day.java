package christmas.model;

import static christmas.constant.Constant.ERROR_DATE;
import static christmas.constant.Constant.THIS_MONTH;
import static christmas.constant.Constant.THIS_YEAR;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Day {
    private final int day;
    private final String dayOfWeek;

    public Day(String date) {
        this.day = Integer.parseInt(date);
        validateDay(date);
        LocalDate localDate = LocalDate.of(THIS_YEAR, THIS_MONTH, day);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        this.dayOfWeek = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN);
    }

    private void validateDay(String date) {
        int day = Integer.parseInt(date);
        if(day <= 0 || day > 31) {
            throw new IllegalArgumentException(ERROR_DATE);
        }
    }

    public int getDay() {
        return day;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }
}
