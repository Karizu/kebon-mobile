
package com.selada.kebonmobile.model.response.farmactivities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Q {

    @SerializedName("site_id")
    @Expose
    private Object siteId;
    @SerializedName("commodity_id")
    @Expose
    private Object commodityId;
    @SerializedName("activity_status_code")
    @Expose
    private Object activityStatusCode;
    @SerializedName("activity_type_code")
    @Expose
    private Object activityTypeCode;
    @SerializedName("activity_method_code")
    @Expose
    private Object activityMethodCode;

    public Object getSiteId() {
        return siteId;
    }

    public void setSiteId(Object siteId) {
        this.siteId = siteId;
    }

    public Object getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Object commodityId) {
        this.commodityId = commodityId;
    }

    public Object getActivityStatusCode() {
        return activityStatusCode;
    }

    public void setActivityStatusCode(Object activityStatusCode) {
        this.activityStatusCode = activityStatusCode;
    }

    public Object getActivityTypeCode() {
        return activityTypeCode;
    }

    public void setActivityTypeCode(Object activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }

    public Object getActivityMethodCode() {
        return activityMethodCode;
    }

    public void setActivityMethodCode(Object activityMethodCode) {
        this.activityMethodCode = activityMethodCode;
    }

}
