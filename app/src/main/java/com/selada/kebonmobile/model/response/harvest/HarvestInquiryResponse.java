
package com.selada.kebonmobile.model.response.harvest;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HarvestInquiryResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("harvests")
    @Expose
    private List<Harvest> harvests = null;
    @SerializedName("activity_methods")
    @Expose
    private List<ActivityMethod> activityMethods = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Harvest> getHarvests() {
        return harvests;
    }

    public void setHarvests(List<Harvest> harvests) {
        this.harvests = harvests;
    }

    public List<ActivityMethod> getActivityMethods() {
        return activityMethods;
    }

    public void setActivityMethods(List<ActivityMethod> activityMethods) {
        this.activityMethods = activityMethods;
    }

}
