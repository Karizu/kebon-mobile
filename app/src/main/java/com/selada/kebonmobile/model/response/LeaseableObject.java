
package com.selada.kebonmobile.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeaseableObject {

    @SerializedName("site_id")
    @Expose
    private Integer siteId;
    @SerializedName("object_name")
    @Expose
    private String objectName;
    @SerializedName("object_type_id")
    @Expose
    private Integer objectTypeId;
    @SerializedName("object_type_name")
    @Expose
    private String objectTypeName;
    @SerializedName("object_type_code")
    @Expose
    private String objectTypeCode;
    @SerializedName("sale_lease_object")
    @Expose
    private Boolean saleLeaseObject;
    @SerializedName("production_capacity")
    @Expose
    private Integer productionCapacity;
    @SerializedName("sowing_object")
    @Expose
    private Boolean sowingObject;
    @SerializedName("occupied_status")
    @Expose
    private Boolean occupiedStatus;
    @SerializedName("available")
    @Expose
    private String available;
    @SerializedName("package_id")
    @Expose
    private String package_id;
    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("default_package")
    @Expose
    private Boolean defaultPackage;
    @SerializedName("payment_term_id")
    @Expose
    private Integer paymentTermId;
    @SerializedName("duration_id")
    @Expose
    private Integer durationId;
    @SerializedName("payment_term_name")
    @Expose
    private String paymentTermName;
    @SerializedName("payment_term_code")
    @Expose
    private String paymentTermCode;
    @SerializedName("lease_duration_name")
    @Expose
    private String leaseDurationName;
    @SerializedName("lease_duration_code")
    @Expose
    private String leaseDurationCode;
    @SerializedName("min_duration")
    @Expose
    private Integer minDuration;
    @SerializedName("max_duration")
    @Expose
    private Integer maxDuration;
    @SerializedName("price_per_duration")
    @Expose
    private Integer pricePerDuration;

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Integer getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(Integer objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public String getObjectTypeName() {
        return objectTypeName;
    }

    public void setObjectTypeName(String objectTypeName) {
        this.objectTypeName = objectTypeName;
    }

    public String getObjectTypeCode() {
        return objectTypeCode;
    }

    public void setObjectTypeCode(String objectTypeCode) {
        this.objectTypeCode = objectTypeCode;
    }

    public Boolean getSaleLeaseObject() {
        return saleLeaseObject;
    }

    public void setSaleLeaseObject(Boolean saleLeaseObject) {
        this.saleLeaseObject = saleLeaseObject;
    }

    public Integer getProductionCapacity() {
        return productionCapacity;
    }

    public void setProductionCapacity(Integer productionCapacity) {
        this.productionCapacity = productionCapacity;
    }

    public Boolean getSowingObject() {
        return sowingObject;
    }

    public void setSowingObject(Boolean sowingObject) {
        this.sowingObject = sowingObject;
    }

    public Boolean getOccupiedStatus() {
        return occupiedStatus;
    }

    public void setOccupiedStatus(Boolean occupiedStatus) {
        this.occupiedStatus = occupiedStatus;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Boolean getDefaultPackage() {
        return defaultPackage;
    }

    public void setDefaultPackage(Boolean defaultPackage) {
        this.defaultPackage = defaultPackage;
    }

    public Integer getPaymentTermId() {
        return paymentTermId;
    }

    public void setPaymentTermId(Integer paymentTermId) {
        this.paymentTermId = paymentTermId;
    }

    public Integer getDurationId() {
        return durationId;
    }

    public void setDurationId(Integer durationId) {
        this.durationId = durationId;
    }

    public String getPaymentTermName() {
        return paymentTermName;
    }

    public void setPaymentTermName(String paymentTermName) {
        this.paymentTermName = paymentTermName;
    }

    public String getPaymentTermCode() {
        return paymentTermCode;
    }

    public void setPaymentTermCode(String paymentTermCode) {
        this.paymentTermCode = paymentTermCode;
    }

    public String getLeaseDurationName() {
        return leaseDurationName;
    }

    public void setLeaseDurationName(String leaseDurationName) {
        this.leaseDurationName = leaseDurationName;
    }

    public String getLeaseDurationCode() {
        return leaseDurationCode;
    }

    public void setLeaseDurationCode(String leaseDurationCode) {
        this.leaseDurationCode = leaseDurationCode;
    }

    public Integer getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(Integer minDuration) {
        this.minDuration = minDuration;
    }

    public Integer getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(Integer maxDuration) {
        this.maxDuration = maxDuration;
    }

    public Integer getPricePerDuration() {
        return pricePerDuration;
    }

    public void setPricePerDuration(Integer pricePerDuration) {
        this.pricePerDuration = pricePerDuration;
    }

}
