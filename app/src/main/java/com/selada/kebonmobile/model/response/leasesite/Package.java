
package com.selada.kebonmobile.model.response.leasesite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Package {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("default_package")
    @Expose
    private Boolean defaultPackage;
    @SerializedName("site_id")
    @Expose
    private Integer siteId;
    @SerializedName("ref_site_object_type_id")
    @Expose
    private Integer refSiteObjectTypeId;
    @SerializedName("duration_id")
    @Expose
    private Integer durationId;
    @SerializedName("min_duration")
    @Expose
    private Integer minDuration;
    @SerializedName("max_duration")
    @Expose
    private Integer maxDuration;
    @SerializedName("payment_term_id")
    @Expose
    private Integer paymentTermId;
    @SerializedName("price_per_duration")
    @Expose
    private Integer pricePerDuration;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getRefSiteObjectTypeId() {
        return refSiteObjectTypeId;
    }

    public void setRefSiteObjectTypeId(Integer refSiteObjectTypeId) {
        this.refSiteObjectTypeId = refSiteObjectTypeId;
    }

    public Integer getDurationId() {
        return durationId;
    }

    public void setDurationId(Integer durationId) {
        this.durationId = durationId;
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

    public Integer getPaymentTermId() {
        return paymentTermId;
    }

    public void setPaymentTermId(Integer paymentTermId) {
        this.paymentTermId = paymentTermId;
    }

    public Integer getPricePerDuration() {
        return pricePerDuration;
    }

    public void setPricePerDuration(Integer pricePerDuration) {
        this.pricePerDuration = pricePerDuration;
    }

}
