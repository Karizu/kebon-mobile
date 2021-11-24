package com.selada.kebonmobile.model;

import com.selada.kebonmobile.model.response.calendar.Calendars;

import java.util.Calendar;

public class CalendarModel {
    private int date;
    private int month;
    private int year;
    private Calendar calendarCompare ;
    private String status;
    private Calendars calendars;

    public CalendarModel(int date, int month, int year, Calendar calendarCompare, String status, Calendars calendars) {
        this.date = date;
        this.month = month;
        this.year = year;
        this.calendarCompare = calendarCompare;
        this.status = status;
        this.calendars = calendars;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Calendar getCalendarCompare() {
        return calendarCompare;
    }

    public void setCalendarCompare(Calendar calendarCompare) {
        this.calendarCompare = calendarCompare;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Calendars getCalendars() {
        return calendars;
    }

    public void setCalendars(Calendars calendars) {
        this.calendars = calendars;
    }
}
