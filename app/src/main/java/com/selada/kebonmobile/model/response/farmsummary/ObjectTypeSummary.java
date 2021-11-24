package com.selada.kebonmobile.model.response.farmsummary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObjectTypeSummary {
    @SerializedName("site_object_type_id")
    @Expose
    private Integer siteObjectTypeId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("harvestable_object")
    @Expose
    private Boolean farmableStatus;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("total_pending_payment")
    @Expose
    private Integer totalPending;
    @SerializedName("total_available")
    @Expose
    private Integer totalIdle;
    @SerializedName("total_active")
    @Expose
    private Integer total_active;
    @SerializedName("total_clean_up")
    @Expose
    private Integer total_clean_up;
    @SerializedName("total_harvesting")
    @Expose
    private Integer total_harvesting;

    public Integer getSiteObjectTypeId() {
        return siteObjectTypeId;
    }

    public void setSiteObjectTypeId(Integer siteObjectTypeId) {
        this.siteObjectTypeId = siteObjectTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getFarmableStatus() {
        return farmableStatus;
    }

    public void setFarmableStatus(Boolean farmableStatus) {
        this.farmableStatus = farmableStatus;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPending() {
        return totalPending;
    }

    public void setTotalPending(Integer totalPending) {
        this.totalPending = totalPending;
    }

    public Integer getTotalIdle() {
        return totalIdle;
    }

    public void setTotalIdle(Integer totalIdle) {
        this.totalIdle = totalIdle;
    }

    public Integer getTotal_active() {
        return total_active;
    }

    public void setTotal_active(Integer total_active) {
        this.total_active = total_active;
    }

    public Integer getTotal_clean_up() {
        return total_clean_up;
    }

    public void setTotal_clean_up(Integer total_clean_up) {
        this.total_clean_up = total_clean_up;
    }

    public Integer getTotal_harvesting() {
        return total_harvesting;
    }

    public void setTotal_harvesting(Integer total_harvesting) {
        this.total_harvesting = total_harvesting;
    }
}
