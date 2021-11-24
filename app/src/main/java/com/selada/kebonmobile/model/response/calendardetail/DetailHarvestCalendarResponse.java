
package com.selada.kebonmobile.model.response.calendardetail;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailHarvestCalendarResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("harvest_schedules")
    @Expose
    private List<HarvestSchedule> harvestSchedules = null;
    @SerializedName("q")
    @Expose
    private Q q;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<HarvestSchedule> getHarvestSchedules() {
        return harvestSchedules;
    }

    public void setHarvestSchedules(List<HarvestSchedule> harvestSchedules) {
        this.harvestSchedules = harvestSchedules;
    }

    public Q getQ() {
        return q;
    }

    public void setQ(Q q) {
        this.q = q;
    }

}
