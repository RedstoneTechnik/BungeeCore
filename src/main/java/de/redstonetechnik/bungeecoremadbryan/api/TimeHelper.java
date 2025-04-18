package de.redstonetechnik.bungeecoremadbryan.api;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeHelper {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final String timeAddFormat = "^(\\d{0,10})([y,m,w,d,h,m,s])$";

    private static final int YEAR_IN_SEC = 31536000;
    private static final int DAY_IN_SEC = 86400;
    private static final int HOUR_IN_SEC = 3600;
    private static final int MIN_IN_SEC = 60;


    /**
     * This code takes a {@code string} time and adds it to given time {@code LocalDateTime}
     * @param time the time
     * @param timeToAdd time to be added
     * @return the new time
     */
    public static LocalDateTime addTime(LocalDateTime time, String timeToAdd) {
        Pattern pattern = Pattern.compile(timeAddFormat);
        Matcher matcher = pattern.matcher(timeToAdd); //regex check time format

        if (!matcher.find()) return null;

        int multiplier = Integer.parseInt(matcher.group(1));
        String timeUnit = matcher.group(2);

        long secondsToAdd = secondsToAdd(multiplier, timeUnit);

        return time.plusSeconds(secondsToAdd);
    }


    private static long secondsToAdd(long multiplier, String timeUnit) {
        switch (timeUnit) {
            case "y": return YEAR_IN_SEC * multiplier;
            case "d": return DAY_IN_SEC * multiplier;
            case "h": return HOUR_IN_SEC * multiplier;
            case "m": return MIN_IN_SEC * multiplier;
            default: return multiplier;
        }
    }

    /**
     * Calculate difference between two dates and return as string format
     * @param start the start date
     * @param end the end date
     * @return the formatted difference as string
     */
    public static String getDifference(LocalDateTime start, LocalDateTime end) {

        Duration duration = Duration.between(start, end);
        long years = duration.toDays() / 365;
        if (years > 100) return BungeeCore_MadBryan.instance.configMessage.cfg.getString("timeformat.permanent");
        long days = duration.toDays() % 365;
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        return (years == 1 ? years + BungeeCore_MadBryan.instance.configMessage.cfg.getString("timeformat.year") : (years != 0) ? years + BungeeCore_MadBryan.instance.configMessage.cfg.getString("timeformat.years") : "") +
                (days == 1 ? days + BungeeCore_MadBryan.instance.configMessage.cfg.getString("timeformat.day") : (days != 0) ? days + BungeeCore_MadBryan.instance.configMessage.cfg.getString("timeformat.days") : "") +
                (hours == 1 ? hours + BungeeCore_MadBryan.instance.configMessage.cfg.getString("timeformat.hour"): (hours != 0) ? hours + BungeeCore_MadBryan.instance.configMessage.cfg.getString("timeformat.hours") : "") +
                (minutes == 1 ? minutes + BungeeCore_MadBryan.instance.configMessage.cfg.getString("timeformat.minute") : (minutes != 0) ? minutes + BungeeCore_MadBryan.instance.configMessage.cfg.getString("timeformat.minutes") : "") +
                (seconds == 1 ? seconds + BungeeCore_MadBryan.instance.configMessage.cfg.getString("timeformat.second") : (seconds != 0) ? seconds + BungeeCore_MadBryan.instance.configMessage.cfg.getString("timeformat.seconds") : "");
    }

    public static String formatTime(String time) {

        Pattern pattern = Pattern.compile(timeAddFormat);
        Matcher matcher = pattern.matcher(time);

        if (!matcher.find()) return "Invalid";

        int timeValue = Integer.parseInt(matcher.group(1));
        String timeUnit = matcher.group(2);

        if (timeUnit.equals("s")) {
            return timeValue + BungeeCore_MadBryan.instance.configMessage.cfg.getString("timeformat.second" + (timeValue != 1 ? "s" : ""));
        }
        if (timeUnit.equals("m")) {
            return timeValue + BungeeCore_MadBryan.instance.configMessage.cfg.getString("timeformat.minute" + (timeValue != 1 ? "s" : ""));
        }
        if (timeUnit.equals("h")) {
            return timeValue + BungeeCore_MadBryan.instance.configMessage.cfg.getString("timeformat.hour" + (timeValue != 1 ? "s" : ""));
        }
        if (timeUnit.equals("d")) {
            return timeValue + BungeeCore_MadBryan.instance.configMessage.cfg.getString("timeformat.day" + (timeValue != 1 ? "s" : ""));
        }
        if (timeUnit.equals("y")) {
            if (timeValue > 100) {
                return BungeeCore_MadBryan.instance.configMessage.cfg.getString("timeformat.permanent");
            }
            return timeValue + BungeeCore_MadBryan.instance.configMessage.cfg.getString("timeformat.year" + (timeValue != 1 ? "s" : ""));
        }
        return "Invalid";
    }

}
