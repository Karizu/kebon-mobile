
package com.selada.kebonmobile.model.response.commoditymonitoring;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selada.kebonmobile.model.response.Image;
import com.selada.kebonmobile.model.response.Logo;

public class CurrentSite {

    @SerializedName("site_id")
    @Expose
    private Integer siteId;
    @SerializedName("object_count")
    @Expose
    private Integer objectCount;
    @SerializedName("id")
    @Expose
    private Integer id;
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
    @SerializedName("address_latitude")
    @Expose
    private String addressLatitude;
    @SerializedName("address_longitude")
    @Expose
    private String addressLongitude;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("logo")
    @Expose
    private Logo logo;
    @SerializedName("object_type_label")
    @Expose
    private String object_type_label;

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public String getObject_type_label() {
        return object_type_label;
    }

    public void setObject_type_label(String object_type_label) {
        this.object_type_label = object_type_label;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getObjectCount() {
        return objectCount;
    }

    public void setObjectCount(Integer objectCount) {
        this.objectCount = objectCount;
    }

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

    public String getAddressLatitude() {
        return addressLatitude;
    }

    public void setAddressLatitude(String addressLatitude) {
        this.addressLatitude = addressLatitude;
    }

    public String getAddressLongitude() {
        return addressLongitude;
    }

    public void setAddressLongitude(String addressLongitude) {
        this.addressLongitude = addressLongitude;
    }

}
