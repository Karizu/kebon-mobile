
package com.selada.kebonmobile.model.response.detailcommodities;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class DevelopmentSummary {

    @SerializedName("current_phase_id")
    @Expose
    private Integer currentPhaseId;
    @SerializedName("phase_name")
    @Expose
    private String phaseName;
    @SerializedName("actual_initiation_date")
    @Expose
    private String actualInitiationDate;
    @SerializedName("object_count")
    @Expose
    private Integer objectCount;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("harvest_days_start")
    @Expose
    private Integer harvestDaysStart;
    @SerializedName("harvest_days_end")
    @Expose
    private Integer harvestDaysEnd;
    @SerializedName("days_to_harvest")
    @Expose
    private String daysToHarvest;
    @SerializedName("start_harvest_date")
    @Expose
    private String startHarvestDate;
    @SerializedName("end_harvest_date")
    @Expose
    private String endHarvestDate;
    @SerializedName("object_type_label")
    @Expose
    private String object_type_label;

    public String getObject_type_label() {
        return object_type_label;
    }

    public void setObject_type_label(String object_type_label) {
        this.object_type_label = object_type_label;
    }

    public Integer getCurrentPhaseId() {
        return currentPhaseId;
    }

    public void setCurrentPhaseId(Integer currentPhaseId) {
        this.currentPhaseId = currentPhaseId;
    }

    public String getPhaseName() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public String getActualInitiationDate() {
        return actualInitiationDate;
    }

    public void setActualInitiationDate(String actualInitiationDate) {
        this.actualInitiationDate = actualInitiationDate;
    }

    public Integer getObjectCount() {
        return objectCount;
    }

    public void setObjectCount(Integer objectCount) {
        this.objectCount = objectCount;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getHarvestDaysStart() {
        return harvestDaysStart;
    }

    public void setHarvestDaysStart(Integer harvestDaysStart) {
        this.harvestDaysStart = harvestDaysStart;
    }

    public Integer getHarvestDaysEnd() {
        return harvestDaysEnd;
    }

    public void setHarvestDaysEnd(Integer harvestDaysEnd) {
        this.harvestDaysEnd = harvestDaysEnd;
    }

    public String getDaysToHarvest() {
        return daysToHarvest;
    }

    public void setDaysToHarvest(String daysToHarvest) {
        this.daysToHarvest = daysToHarvest;
    }

    public String getStartHarvestDate() {
        return startHarvestDate;
    }

    public void setStartHarvestDate(String startHarvestDate) {
        this.startHarvestDate = startHarvestDate;
    }

    public String getEndHarvestDate() {
        return endHarvestDate;
    }

    public void setEndHarvestDate(String endHarvestDate) {
        this.endHarvestDate = endHarvestDate;
    }

}
