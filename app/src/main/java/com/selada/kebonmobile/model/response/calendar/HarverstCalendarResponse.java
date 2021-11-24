package com.selada.kebonmobile.model.response.calendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HarverstCalendarResponse {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("calendars")
    @Expose
    private List<Calendars> calendars;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Calendars> getCalendars() {
        return calendars;
    }

    public void setCalendars(List<Calendars> calendars) {
        this.calendars = calendars;
    }
}
