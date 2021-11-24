
package com.selada.kebonmobile.model.response.harvest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Harvest {

    @SerializedName("object_count")
    @Expose
    private Integer objectCount;
    @SerializedName("object_type_label")
    @Expose
    private String objectTypeLabel;
    @SerializedName("object_type_code")
    @Expose
    private String objectTypeCode;
    @SerializedName("site_id")
    @Expose
    private Integer siteId;
    @SerializedName("site_name")
    @Expose
    private String siteName;
    @SerializedName("commodity_id")
    @Expose
    private Integer commodityId;
    @SerializedName("commodity_name")
    @Expose
    private String commodityName;
    @SerializedName("commodity_main_image_id")
    @Expose
    private Integer commodityMainImageId;
    @SerializedName("ref_estimated_harvest_start_date")
    @Expose
    private String refEstimatedHarvestStartDate;
    @SerializedName("ref_estimated_harvest_end_date")
    @Expose
    private String refEstimatedHarvestEndDate;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("remaining_days_to_harvest")
    @Expose
    private String remainingDaysToHarvest;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("main_image")
    @Expose
    private MainImage mainImage;

    public Integer getObjectCount() {
        return objectCount;
    }

    public void setObjectCount(Integer objectCount) {
        this.objectCount = objectCount;
    }

    public String getObjectTypeLabel() {
        return objectTypeLabel;
    }

    public void setObjectTypeLabel(String objectTypeLabel) {
        this.objectTypeLabel = objectTypeLabel;
    }

    public String getObjectTypeCode() {
        return objectTypeCode;
    }

    public void setObjectTypeCode(String objectTypeCode) {
        this.objectTypeCode = objectTypeCode;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Integer getCommodityMainImageId() {
        return commodityMainImageId;
    }

    public void setCommodityMainImageId(Integer commodityMainImageId) {
        this.commodityMainImageId = commodityMainImageId;
    }

    public String getRefEstimatedHarvestStartDate() {
        return refEstimatedHarvestStartDate;
    }

    public void setRefEstimatedHarvestStartDate(String refEstimatedHarvestStartDate) {
        this.refEstimatedHarvestStartDate = refEstimatedHarvestStartDate;
    }

    public String getRefEstimatedHarvestEndDate() {
        return refEstimatedHarvestEndDate;
    }

    public void setRefEstimatedHarvestEndDate(String refEstimatedHarvestEndDate) {
        this.refEstimatedHarvestEndDate = refEstimatedHarvestEndDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
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

    public MainImage getMainImage() {
        return mainImage;
    }

    public void setMainImage(MainImage mainImage) {
        this.mainImage = mainImage;
    }

}
