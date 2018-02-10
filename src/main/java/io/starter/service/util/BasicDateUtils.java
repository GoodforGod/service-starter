package io.starter.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.TimeZone;

import static io.starter.service.util.BasicStringUtils.isNotBlank;
import static java.util.Calendar.getInstance;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 10.02.2018
 */
public abstract class BasicDateUtils {

    private static final Logger logger = LoggerFactory.getLogger(BasicDateUtils.class);

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm d.M.yyyy");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d.M.yyyy");
    public static final DateTimeFormatter DATE_SHORT_FORMATTER = DateTimeFormatter.ofPattern("d.M");

    public static Calendar genCalendarZoned() {
        return getInstance(TimeZone.getTimeZone("Europe/Moscow"));
    }

    //<editor-fold desc="Comparators">

    public static boolean isDatesOccurToday(LocalDateTime first, LocalDateTime second) {
        return (first != null && second != null
                && first.getDayOfMonth() == second.getDayOfMonth()
                && first.getMonthValue() == second.getMonthValue());
    }

    public static boolean isDateOccurTomorrow(LocalDateTime first, LocalDateTime second) {
        return (first != null && second != null
                && first.getMonthValue() == second.getMonthValue()
                && (first.getDayOfMonth() + 1) == second.getDayOfMonth());
    }

    public static int compareDateTimes(LocalDateTime date1, LocalDateTime date2) {
        return (date1 != null && date2 != null)
                ? date1.compareTo(date2)
                : 0;
    }

    public static int compareDates(LocalDate date1, LocalDate date2) {
        return (date1 != null && date2 != null)
                ? date1.compareTo(date2)
                : 0;
    }

    public static int compareTimes(LocalTime date1, LocalTime date2) {
        return (date1 != null && date2 != null)
                ? date1.compareTo(date2)
                : 0;
    }
    //</editor-fold>

    //<editor-fold desc="ParsersAndFormaters">

    public static LocalDateTime parseDateTime(String date, String time) {
        final boolean timeStatus = isNotBlank(time);
        final boolean dateStatus = isNotBlank(date);

        if (timeStatus & dateStatus)
            return parseDateTime(time + " " + date, DATE_TIME_FORMATTER);

        if (timeStatus) {
            final LocalTime localTime = parseTime(time);
            return (localTime == null)
                    ? null
                    : LocalDateTime.of(0, 0, 0, localTime.getHour(), localTime.getMinute());
        }

        if (dateStatus) {
            final LocalDate localDate = parseDate(time);
            return (localDate == null)
                    ? null
                    : LocalDateTime.of(localDate.getYear(), localDate.getDayOfMonth(), localDate.getDayOfMonth(), 0, 0);
        }

        return null;
    }

    public static LocalDate parseDate(String date) {
        return (isNotBlank(date))
                ? parseDate(date, DATE_FORMATTER)
                : null;
    }

    public static LocalTime parseTime(String time) {
        return (isNotBlank(time))
                ? parseTime(time, TIME_FORMATTER)
                : null;
    }

    public static String formatToDate(LocalDateTime dateTime) {
        return (dateTime != null)
                ? format(dateTime, DATE_SHORT_FORMATTER)
                : null;
    }

    public static String formatToTime(LocalDateTime dateTime) {
        return (dateTime != null)
                ? format(dateTime, TIME_FORMATTER)
                : null;
    }

    public static String formatToDate(LocalDate date) {
        return (date != null)
                ? format(date, DATE_SHORT_FORMATTER)
                : null;
    }

    public static String formatToTime(LocalTime time) {
        return (time != null)
                ? format(time, TIME_FORMATTER)
                : null;
    }

    private static LocalDateTime parseDateTime(String dateTime, DateTimeFormatter format) {
        try {
            return LocalDateTime.parse(dateTime, format);
        } catch (DateTimeParseException e) {
            logger.warn("[BASIC DATE UTILS] Can not parse date time: " + e.getMessage());
            return null;
        }
    }

    private static LocalTime parseTime(String time, DateTimeFormatter format) {
        try {
            return LocalTime.parse(time, format);
        } catch (DateTimeParseException e) {
            logger.warn("[BASIC DATE UTILS] Can not parse time: " + e.getMessage());
            return null;
        }
    }

    private static LocalDate parseDate(String date, DateTimeFormatter format) {
        try {
            return LocalDate.parse(date, format);
        } catch (DateTimeParseException e) {
            logger.warn("[BASIC DATE UTILS] Can not parse date: " + e.getMessage());
            return null;
        }
    }

    private static String format(LocalTime time, DateTimeFormatter format) {
        try {
            return time.format(format);
        } catch (DateTimeParseException e) {
            logger.warn("[BASIC DATE UTILS] Can not format date time: " + e.getMessage());
            return null;
        }
    }

    private static String format(LocalDate date, DateTimeFormatter format) {
        try {
            return date.format(format);
        } catch (DateTimeParseException e) {
            logger.warn("[BASIC DATE UTILS] Can not format date time: " + e.getMessage());
            return null;
        }
    }

    private static String format(LocalDateTime dateTime, DateTimeFormatter format) {
        try {
            return dateTime.format(format);
        } catch (DateTimeParseException e) {
            logger.warn("[BASIC DATE UTILS] Can not format date time: " + e.getMessage());
            return null;
        }
    }
    //</editor-fold>
}
