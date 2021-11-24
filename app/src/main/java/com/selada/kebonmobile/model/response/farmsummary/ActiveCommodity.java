
package com.selada.kebonmobile.model.response.farmsummary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActiveCommodity {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("main_image_id")
    @Expose
    private Integer mainImageId;
    @SerializedName("object_count")
    @Expose
    private String count;
    @SerializedName("highest_phase")
    @Expose
    private Integer highestPhase;
    @SerializedName("oldest_age")
    @Expose
    private String oldestAge;
    @SerializedName("oldest_start")
    @Expose
    private String oldestStart;
    @SerializedName("main_image")
    @Expose
    private MainImage mainImage;
    @SerializedName("nearest_harvest_start_period_date")
    @Expose
    private String nearestHarvestStartPeriodDate;
    @SerializedName("nearest_days_to_harvest_start_period_date")
    @Expose
    private String nearestDaysToHarvestStartPeriodDate;
    @SerializedName("nearest_harvest_end_period_date")
    @Expose
    private String nearestHarvestEndPeriodDate;
    @SerializedName("nearest_remaining_days_to_harvest")
    @Expose
    private String nearest_remaining_days_to_harvest;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMainImageId() {
        return mainImageId;
    }

    public void setMainImageId(Integer mainImageId) {
        this.mainImageId = mainImageId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Integer getHighestPhase() {
        return highestPhase;
    }

    public void setHighestPhase(Integer highestPhase) {
        this.highestPhase = highestPhase;
    }

    public String getOldestAge() {
        return oldestAge;
    }

    public void setOldestAge(String oldestAge) {
        this.oldestAge = oldestAge;
    }

    public String getOldestStart() {
        return oldestStart;
    }

    public void setOldestStart(String oldestStart) {
        this.oldestStart = oldestStart;
    }

    public MainImage getMainImage() {
        return mainImage;
    }

    public void setMainImage(MainImage mainImage) {
        this.mainImage = mainImage;
    }

    public String getNearestHarvestStartPeriodDate() {
        return nearestHarvestStartPeriodDate;
    }

    public void setNearestHarvestStartPeriodDate(String nearestHarvestStartPeriodDate) {
        this.nearestHarvestStartPeriodDate = nearestHarvestStartPeriodDate;
    }

    public String getNearestDaysToHarvestStartPeriodDate() {
        return nearestDaysToHarvestStartPeriodDate;
    }

    public void setNearestDaysToHarvestStartPeriodDate(String nearestDaysToHarvestStartPeriodDate) {
        this.nearestDaysToHarvestStartPeriodDate = nearestDaysToHarvestStartPeriodDate;
    }

    public String getNearestHarvestEndPeriodDate() {
        return nearestHarvestEndPeriodDate;
    }

    public void setNearestHarvestEndPeriodDate(String nearestHarvestEndPeriodDate) {
        this.nearestHarvestEndPeriodDate = nearestHarvestEndPeriodDate;
    }

    public String getNearest_remaining_days_to_harvest() {
        return nearest_remaining_days_to_harvest;
    }

    public void setNearest_remaining_days_to_harvest(String nearest_remaining_days_to_harvest) {
        this.nearest_remaining_days_to_harvest = nearest_remaining_days_to_harvest;
    }
}
