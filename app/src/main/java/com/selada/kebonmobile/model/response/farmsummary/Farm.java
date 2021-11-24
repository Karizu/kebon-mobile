
package com.selada.kebonmobile.model.response.farmsummary;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selada.kebonmobile.model.response.Image;
import com.selada.kebonmobile.model.response.Logo;
import com.selada.kebonmobile.model.response.commodity.AvailableCommodity;

public class Farm {

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("site_id")
    @Expose
    private Integer site_id;
    @SerializedName("ref_lease_contract_id")
    @Expose
    private Integer refLeaseContractId;
    @SerializedName("ref_site_id")
    @Expose
    private Integer refSiteId;
    @SerializedName("ref_site_object_type_id")
    @Expose
    private Integer refSiteObjectTypeId;
    @SerializedName("total_farmable")
    @Expose
    private Integer totalFarmable;
    @SerializedName("total_pending")
    @Expose
    private Integer totalPending;
    @SerializedName("total_idle")
    @Expose
    private Integer totalIdle;
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
    private String address_latitude;
    @SerializedName("address_longitude")
    @Expose
    private String address_longitude;
    @SerializedName("unpaid_invoices")
    @Expose
    private List<UnpaidInvoice> unpaidInvoices;
    @SerializedName("site_components")
    @Expose
    private List<SiteComponent> siteComponents;
    @SerializedName("object_type_summary")
    @Expose
    private List<ObjectTypeSummary> objectTypeSummary;
    @SerializedName("activity_regular_defined_costs")
    @Expose
    private List<ActivityRegularDefinedCosts> activityRegularDefinedCosts;
    @SerializedName("activity_site_rules")
    @Expose
    private List<ActivitySiteRules> activitySiteRules;
    @SerializedName("available_commodities")
    @Expose
    private List<AvailableCommodity> availableCommodities;
    @SerializedName("active_commodity_count")
    @Expose
    private String activeCommodityCount;
    @SerializedName("active_commodity_str")
    @Expose
    private String activeCommodityStr;
    @SerializedName("active_commodities")
    @Expose
    private List<ActiveCommodity> activeCommodities;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("logo")
    @Expose
    private Logo logo;

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRefLeaseContractId() {
        return refLeaseContractId;
    }

    public void setRefLeaseContractId(Integer refLeaseContractId) {
        this.refLeaseContractId = refLeaseContractId;
    }

    public Integer getRefSiteId() {
        return refSiteId;
    }

    public void setRefSiteId(Integer refSiteId) {
        this.refSiteId = refSiteId;
    }

    public Integer getRefSiteObjectTypeId() {
        return refSiteObjectTypeId;
    }

    public void setRefSiteObjectTypeId(Integer refSiteObjectTypeId) {
        this.refSiteObjectTypeId = refSiteObjectTypeId;
    }

    public Integer getTotalFarmable() {
        return totalFarmable;
    }

    public void setTotalFarmable(Integer totalFarmable) {
        this.totalFarmable = totalFarmable;
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

    public List<UnpaidInvoice> getUnpaidInvoices() {
        return unpaidInvoices;
    }

    public void setUnpaidInvoices(List<UnpaidInvoice> unpaidInvoices) {
        this.unpaidInvoices = unpaidInvoices;
    }

    public List<SiteComponent> getSiteComponents() {
        return siteComponents;
    }

    public void setSiteComponents(List<SiteComponent> siteComponents) {
        this.siteComponents = siteComponents;
    }

    public List<ObjectTypeSummary> getObjectTypeSummary() {
        return objectTypeSummary;
    }

    public void setObjectTypeSummary(List<ObjectTypeSummary> objectTypeSummary) {
        this.objectTypeSummary = objectTypeSummary;
    }

    public Integer getSite_id() {
        return site_id;
    }

    public void setSite_id(Integer site_id) {
        this.site_id = site_id;
    }

    public List<ActivityRegularDefinedCosts> getActivityRegularDefinedCosts() {
        return activityRegularDefinedCosts;
    }

    public void setActivityRegularDefinedCosts(List<ActivityRegularDefinedCosts> activityRegularDefinedCosts) {
        this.activityRegularDefinedCosts = activityRegularDefinedCosts;
    }

    public List<ActivitySiteRules> getActivitySiteRules() {
        return activitySiteRules;
    }

    public void setActivitySiteRules(List<ActivitySiteRules> activitySiteRules) {
        this.activitySiteRules = activitySiteRules;
    }

    public List<AvailableCommodity> getAvailableCommodities() {
        return availableCommodities;
    }

    public void setAvailableCommodities(List<AvailableCommodity> availableCommodities) {
        this.availableCommodities = availableCommodities;
    }

    public String getActiveCommodityCount() {
        return activeCommodityCount;
    }

    public void setActiveCommodityCount(String activeCommodityCount) {
        this.activeCommodityCount = activeCommodityCount;
    }

    public String getActiveCommodityStr() {
        return activeCommodityStr;
    }

    public void setActiveCommodityStr(String activeCommodityStr) {
        this.activeCommodityStr = activeCommodityStr;
    }

    public List<ActiveCommodity> getActiveCommodities() {
        return activeCommodities;
    }

    public void setActiveCommodities(List<ActiveCommodity> activeCommodities) {
        this.activeCommodities = activeCommodities;
    }

    public String getAddress_latitude() {
        return address_latitude;
    }

    public void setAddress_latitude(String address_latitude) {
        this.address_latitude = address_latitude;
    }

    public String getAddress_longitude() {
        return address_longitude;
    }

    public void setAddress_longitude(String address_longitude) {
        this.address_longitude = address_longitude;
    }
}
