
package com.selada.kebonmobile.model.response.farmactivities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FarmActivitiesResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("activities")
    @Expose
    private List<Activity> activities;
    @SerializedName("q")
    @Expose
    private Q q;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public Q getQ() {
        return q;
    }

    public void setQ(Q q) {
        this.q = q;
    }

}
