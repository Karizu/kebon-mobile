
package com.selada.kebonmobile.model.response.farmsummary;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selada.kebonmobile.model.response.commoditymonitoring.Summary;

public class FarmSummaryResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("farms")
    @Expose
    private List<Farm> farms;
    @SerializedName("farm")
    @Expose
    private Farm farm;
    @SerializedName("summary")
    @Expose
    private Summary summary;
    @SerializedName("days_to_nearest_harvest")
    @Expose
    private String days_to_nearest_harvest;
    @SerializedName("active_commodity_count")
    @Expose
    private String active_commodity_count;
    @SerializedName("active_commodity_str")
    @Expose
    private String active_commodity_str;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Farm> getFarms() {
        return farms;
    }

    public void setFarms(List<Farm> farms) {
        this.farms = farms;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public String getDays_to_nearest_harvest() {
        return days_to_nearest_harvest;
    }

    public void setDays_to_nearest_harvest(String days_to_nearest_harvest) {
        this.days_to_nearest_harvest = days_to_nearest_harvest;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public String getActive_commodity_count() {
        return active_commodity_count;
    }

    public void setActive_commodity_count(String active_commodity_count) {
        this.active_commodity_count = active_commodity_count;
    }

    public String getActive_commodity_str() {
        return active_commodity_str;
    }

    public void setActive_commodity_str(String active_commodity_str) {
        this.active_commodity_str = active_commodity_str;
    }
}
