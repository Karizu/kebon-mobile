package com.selada.kebonmobile.model.response.farmsummary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FarmSummaryInSite {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("farm")
    @Expose
    private Farm farm;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }
}
