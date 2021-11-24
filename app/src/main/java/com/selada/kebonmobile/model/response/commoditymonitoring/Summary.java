
package com.selada.kebonmobile.model.response.commoditymonitoring;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selada.kebonmobile.model.response.farmsummary.ObjectTypeSummary;

import java.util.List;

public class Summary {

    @SerializedName("object_count")
    @Expose
    private Integer objectCount;
    @SerializedName("site_count")
    @Expose
    private Integer siteCount;
    @SerializedName("commodity_id")
    @Expose
    private Integer commodityId;
    @SerializedName("recent_start_date")
    @Expose
    private String recentStartDate;
    @SerializedName("nearest_harvest_start_date")
    @Expose
    private String nearestHarvestStartDate;
    @SerializedName("nearest_harvest_end_date")
    @Expose
    private String nearestHarvestEndDate;
    @SerializedName("nearest_remaining_days_to_harvest")
    @Expose
    private String nearestRemainingDaysToHarvest;
    @SerializedName("oldest_age")
    @Expose
    private String oldestAge;
    @SerializedName("newest_age")
    @Expose
    private String newestAge;
    @SerializedName("object_type_summary")
    @Expose
    private List<ObjectTypeSummary> object_type_summary;
    @SerializedName("contract_expiry_dates")
    @Expose
    private String contract_expiry_dates;
    @SerializedName("object_type_label")
    @Expose
    private String object_type_label;

    public String getObject_type_label() {
        return object_type_label;
    }

    public void setObject_type_label(String object_type_label) {
        this.object_type_label = object_type_label;
    }

    public String getContract_expiry_dates() {
        return contract_expiry_dates;
    }

    public void setContract_expiry_dates(String contract_expiry_dates) {
        this.contract_expiry_dates = contract_expiry_dates;
    }

    public List<ObjectTypeSummary> getObject_type_summary() {
        return object_type_summary;
    }

    public void setObject_type_summary(List<ObjectTypeSummary> object_type_summary) {
        this.object_type_summary = object_type_summary;
    }

    public Integer getObjectCount() {
        return objectCount;
    }

    public void setObjectCount(Integer objectCount) {
        this.objectCount = objectCount;
    }

    public Integer getSiteCount() {
        return siteCount;
    }

    public void setSiteCount(Integer siteCount) {
        this.siteCount = siteCount;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getRecentStartDate() {
        return recentStartDate;
    }

    public void setRecentStartDate(String recentStartDate) {
        this.recentStartDate = recentStartDate;
    }

    public String getNearestHarvestStartDate() {
        return nearestHarvestStartDate;
    }

    public void setNearestHarvestStartDate(String nearestHarvestStartDate) {
        this.nearestHarvestStartDate = nearestHarvestStartDate;
    }

    public String getNearestHarvestEndDate() {
        return nearestHarvestEndDate;
    }

    public void setNearestHarvestEndDate(String nearestHarvestEndDate) {
        this.nearestHarvestEndDate = nearestHarvestEndDate;
    }

    public String getNearestRemainingDaysToHarvest() {
        return nearestRemainingDaysToHarvest;
    }

    public void setNearestRemainingDaysToHarvest(String nearestRemainingDaysToHarvest) {
        this.nearestRemainingDaysToHarvest = nearestRemainingDaysToHarvest;
    }

    public String getOldestAge() {
        return oldestAge;
    }

    public void setOldestAge(String oldestAge) {
        this.oldestAge = oldestAge;
    }

    public String getNewestAge() {
        return newestAge;
    }

    public void setNewestAge(String newestAge) {
        this.newestAge = newestAge;
    }

}
