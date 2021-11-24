package com.selada.kebonmobile.model.response.calendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Calendars {
    @SerializedName("calendar_date")
    @Expose
    private String calendarDate;
    @SerializedName("commodity_ids")
    @Expose
    private String commodityIds;
    @SerializedName("commodity_names")
    @Expose
    private String commodityNames;
    @SerializedName("site_ids")
    @Expose
    private String siteIds;
    @SerializedName("site_names")
    @Expose
    private String siteNames;
    @SerializedName("total_object_count")
    @Expose
    private String totalObjectCount;
    @SerializedName("estimated_harvest_start_date")
    @Expose
    private String estimatedHarvestStartDate;
    @SerializedName("estimated_harvest_end_date")
    @Expose
    private String estimatedHarvestEndDate;
    @SerializedName("q")
    @Expose
    private Q q;

    public String getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(String calendarDate) {
        this.calendarDate = calendarDate;
    }

    public String getCommodityIds() {
        return commodityIds;
    }

    public void setCommodityIds(String commodityIds) {
        this.commodityIds = commodityIds;
    }

    public String getCommodityNames() {
        return commodityNames;
    }

    public void setCommodityNames(String commodityNames) {
        this.commodityNames = commodityNames;
    }

    public String getSiteIds() {
        return siteIds;
    }

    public void setSiteIds(String siteIds) {
        this.siteIds = siteIds;
    }

    public String getSiteNames() {
        return siteNames;
    }

    public void setSiteNames(String siteNames) {
        this.siteNames = siteNames;
    }

    public String getTotalObjectCount() {
        return totalObjectCount;
    }

    public void setTotalObjectCount(String totalObjectCount) {
        this.totalObjectCount = totalObjectCount;
    }

    public String getEstimatedHarvestStartDate() {
        return estimatedHarvestStartDate;
    }

    public void setEstimatedHarvestStartDate(String estimatedHarvestStartDate) {
        this.estimatedHarvestStartDate = estimatedHarvestStartDate;
    }

    public String getEstimatedHarvestEndDate() {
        return estimatedHarvestEndDate;
    }

    public void setEstimatedHarvestEndDate(String estimatedHarvestEndDate) {
        this.estimatedHarvestEndDate = estimatedHarvestEndDate;
    }

    public Q getQ() {
        return q;
    }

    public void setQ(Q q) {
        this.q = q;
    }
}
