package com.selada.kebonmobile.model.response.farmactivities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailFarmActivityResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("activity")
    @Expose
    private Activity activity;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
