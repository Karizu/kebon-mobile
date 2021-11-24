package com.selada.kebonmobile.model.response.calendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Q {
    @SerializedName("site_id")
    @Expose
    private String siteId;
    @SerializedName("commodity_id")
    @Expose
    private String commodityId;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("start_date_d")
    @Expose
    private String startDateD;
    @SerializedName("end_date_d")
    @Expose
    private String endDateD;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDateD() {
        return startDateD;
    }

    public void setStartDateD(String startDateD) {
        this.startDateD = startDateD;
    }

    public String getEndDateD() {
        return endDateD;
    }

    public void setEndDateD(String endDateD) {
        this.endDateD = endDateD;
    }
}
