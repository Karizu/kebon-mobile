package com.selada.kebonmobile.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SiteLeasableResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address_street")
    @Expose
    private String addressStreet;
    @SerializedName("address_postal")
    @Expose
    private String addressPostal;
    @SerializedName("address_district")
    @Expose
    private String addressDistrict;
    @SerializedName("address_subdistrict")
    @Expose
    private String addressSubdistrict;
    @SerializedName("address_city")
    @Expose
    private String addressCity;
    @SerializedName("address_province")
    @Expose
    private String addressProvince;
    @SerializedName("address_country")
    @Expose
    private String addressCountry;
    @SerializedName("operating_company_name")
    @Expose
    private String operatingCompanyName;
    @SerializedName("site_components")
    @Expose
    private List<SiteComponent> siteComponents;
    @SerializedName("leaseable_objects")
    @Expose
    private List<LeaseableObject> leaseableObjects;

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

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressPostal() {
        return addressPostal;
    }

    public void setAddressPostal(String addressPostal) {
        this.addressPostal = addressPostal;
    }

    public String getAddressDistrict() {
        return addressDistrict;
    }

    public void setAddressDistrict(String addressDistrict) {
        this.addressDistrict = addressDistrict;
    }

    public String getAddressSubdistrict() {
        return addressSubdistrict;
    }

    public void setAddressSubdistrict(String addressSubdistrict) {
        this.addressSubdistrict = addressSubdistrict;
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

    public String getOperatingCompanyName() {
        return operatingCompanyName;
    }

    public void setOperatingCompanyName(String operatingCompanyName) {
        this.operatingCompanyName = operatingCompanyName;
    }

    public List<SiteComponent> getSiteComponents() {
        return siteComponents;
    }

    public void setSiteComponents(List<SiteComponent> siteComponents) {
        this.siteComponents = siteComponents;
    }

    public List<LeaseableObject> getLeaseableObjects() {
        return leaseableObjects;
    }

    public void setLeaseableObjects(List<LeaseableObject> leaseableObjects) {
        this.leaseableObjects = leaseableObjects;
    }
}
