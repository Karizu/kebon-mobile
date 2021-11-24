
package com.selada.kebonmobile.model.response.detailcommodities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActiveSite {

    @SerializedName("site_id")
    @Expose
    private Integer siteId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("operating_company_name")
    @Expose
    private String operatingCompanyName;
    @SerializedName("address_street")
    @Expose
    private String addressStreet;
    @SerializedName("address_subdistrict")
    @Expose
    private String addressSubdistrict;
    @SerializedName("address_district")
    @Expose
    private String addressDistrict;
    @SerializedName("address_city")
    @Expose
    private String addressCity;
    @SerializedName("address_province")
    @Expose
    private String addressProvince;
    @SerializedName("address_country")
    @Expose
    private String addressCountry;
    @SerializedName("address_postal")
    @Expose
    private String addressPostal;
    @SerializedName("commodity_id")
    @Expose
    private Integer commodityId;
    @SerializedName("commodity_name")
    @Expose
    private String commodityName;
    @SerializedName("object_count")
    @Expose
    private Integer objectCount;
    @SerializedName("development_summaries")
    @Expose
    private List<DevelopmentSummary> developmentSummaries = null;
    @SerializedName("object_type_label")
    @Expose
    private String object_type_label;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperatingCompanyName() {
        return operatingCompanyName;
    }

    public void setOperatingCompanyName(String operatingCompanyName) {
        this.operatingCompanyName = operatingCompanyName;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressSubdistrict() {
        return addressSubdistrict;
    }

    public void setAddressSubdistrict(String addressSubdistrict) {
        this.addressSubdistrict = addressSubdistrict;
    }

    public String getAddressDistrict() {
        return addressDistrict;
    }

    public void setAddressDistrict(String addressDistrict) {
        this.addressDistrict = addressDistrict;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getAddressPostal() {
        return addressPostal;
    }

    public void setAddressPostal(String addressPostal) {
        this.addressPostal = addressPostal;
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

    public Integer getObjectCount() {
        return objectCount;
    }

    public void setObjectCount(Integer objectCount) {
        this.objectCount = objectCount;
    }

    public List<DevelopmentSummary> getDevelopmentSummaries() {
        return developmentSummaries;
    }

    public void setDevelopmentSummaries(List<DevelopmentSummary> developmentSummaries) {
        this.developmentSummaries = developmentSummaries;
    }

}
