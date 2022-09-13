package com.kazmiruk.time;

/**
 * Class that keeps time
 */
public class Time {
    /** Hour */
    private int hour;
    /** Minute */
    private int minute;
    /** Second */
    private int second;

    /**
     * Constructor without parameters
     */
    public Time() {
        hour = 0;
        minute = 0;
        second = 0;
    }

    /**
     * Constructor to which a string is passed
     * with time in the format hh:mm:ss or hh:mm or hh
     *
     * @param time Time in String
     */
    public Time(String time) {
        stringToTime(time);
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void setTime(String time) {
        stringToTime(time);
    }

    /**
     * Converts all time into seconds
     * @return Time in seconds
     */
    public int getTimeInSecond() {
        return hour * 3600 + minute * 60 + second;
    }

    public String getTime() {
        return toString();
    }

    /**
     * Enters data from the character string
     * into the class fields
     * @param time Time in string
     */
    private void stringToTime(String time) {
        String[] tm = time.split(":");
        hour = tm.length >= 1 ? Integer.parseInt(tm[0]) : 0;
        minute = tm.length >= 2 ? Integer.parseInt(tm[1]) : 0;
        second = tm.length == 3 ? Integer.parseInt(tm[2]) : 0;
    }

    /**
     * Converts time to hh:mm:ss format
     *
     * @return Time in hh:mm:ss format
     */
    @Override
    public String toString() {
        String strHour = String.valueOf(hour);
        String strMinute = String.valueOf(minute);
        String strSecond = String.valueOf(second);

        if (hour < 10)   strHour = "0" + strHour;
        if (minute < 10) strMinute = "0" + strMinute;
        if (second < 10) strSecond = "0" + strSecond;

        return strHour + ":" + strMinute + ":" + strSecond;
    }
}
