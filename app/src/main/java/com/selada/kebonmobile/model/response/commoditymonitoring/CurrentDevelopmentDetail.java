
package com.selada.kebonmobile.model.response.commoditymonitoring;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentDevelopmentDetail {

    @SerializedName("site_id")
    @Expose
    private Integer siteId;
    @SerializedName("commodity_id")
    @Expose
    private Integer commodityId;
    @SerializedName("current_phase_id")
    @Expose
    private Integer currentPhaseId;
    @SerializedName("current_phase_name")
    @Expose
    private String currentPhaseName;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("harvest_start_date")
    @Expose
    private String harvestStartDate;
    @SerializedName("harvest_end_date")
    @Expose
    private String harvestEndDate;
    @SerializedName("remaining_days_to_harvest")
    @Expose
    private String remainingDaysToHarvest;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("object_count")
    @Expose
    private Integer objectCount;
    @SerializedName("object_type_label")
    @Expose
    private String object_type_label;
    @SerializedName("phase_timeline")
    @Expose
    private List<PhaseTimeline> phaseTimeline;
    @SerializedName("current_status_name")
    @Expose
    private String current_status_name;


    public String getObject_type_label() {
        return object_type_label;
    }

    public void setObject_type_label(String object_type_label) {
        this.object_type_label = object_type_label;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getCurrentPhaseId() {
        return currentPhaseId;
    }

    public void setCurrentPhaseId(Integer currentPhaseId) {
        this.currentPhaseId = currentPhaseId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getHarvestStartDate() {
        return harvestStartDate;
    }

    public void setHarvestStartDate(String harvestStartDate) {
        this.harvestStartDate = harvestStartDate;
    }

    public String getHarvestEndDate() {
        return harvestEndDate;
    }

    public void setHarvestEndDate(String harvestEndDate) {
        this.harvestEndDate = harvestEndDate;
    }

    public String getRemainingDaysToHarvest() {
        return remainingDaysToHarvest;
    }

    public void setRemainingDaysToHarvest(String remainingDaysToHarvest) {
        this.remainingDaysToHarvest = remainingDaysToHarvest;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getObjectCount() {
        return objectCount;
    }

    public void setObjectCount(Integer objectCount) {
        this.objectCount = objectCount;
    }

    public String getCurrentPhaseName() {
        return currentPhaseName;
    }

    public void setCurrentPhaseName(String currentPhaseName) {
        this.currentPhaseName = currentPhaseName;
    }

    public List<PhaseTimeline> getPhaseTimeline() {
        return phaseTimeline;
    }

    public void setPhaseTimeline(List<PhaseTimeline> phaseTimeline) {
        this.phaseTimeline = phaseTimeline;
    }

    public String getCurrent_status_name() {
        return current_status_name;
    }

    public void setCurrent_status_name(String current_status_name) {
        this.current_status_name = current_status_name;
    }
}
