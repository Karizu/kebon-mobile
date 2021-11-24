
package com.selada.kebonmobile.model.response.farmactivities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selada.kebonmobile.model.request.DeliveryAddress;

public class Activity {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("activity_type_id")
    @Expose
    private Integer activityTypeId;
    @SerializedName("scheme_id")
    @Expose
    private Integer schemeId;
    @SerializedName("commodity_id")
    @Expose
    private Integer commodityId;
    @SerializedName("bulk_code")
    @Expose
    private String bulkCode;
    @SerializedName("ref_site_id")
    @Expose
    private Integer refSiteId;
    @SerializedName("total_cost")
    @Expose
    private Integer totalCost;
    @SerializedName("sub_total_cost")
    @Expose
    private Integer subTotalCost;
    @SerializedName("total_objects")
    @Expose
    private Integer totalObjects;
    @SerializedName("scheduled_date")
    @Expose
    private String scheduledDate;
    @SerializedName("actual_date")
    @Expose
    private String actualDate;
    @SerializedName("pending_payment")
    @Expose
    private Boolean pendingPayment;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("modified_date")
    @Expose
    private String modifiedDate;
    @SerializedName("activity_method_id")
    @Expose
    private Integer activityMethodId;
    @SerializedName("count_logs")
    @Expose
    private String countLogs;
    @SerializedName("count_objects")
    @Expose
    private String countObjects;
    @SerializedName("commodity")
    @Expose
    private Commodity commodity;
    @SerializedName("site")
    @Expose
    private Site site;
    @SerializedName("activity_type")
    @Expose
    private ActivityType activityType;
    @SerializedName("activity_method")
    @Expose
    private ActivityMethod activityMethod;
    @SerializedName("scheme")
    @Expose
    private Scheme scheme;
    @SerializedName("results")
    @Expose
    private List<Result> results;
    @SerializedName("cost_details")
    @Expose
    private List<CostDetail> cost_details;
    @SerializedName("delivery_address")
    @Expose
    private DeliveryAddress deliveryAddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityTypeId() {
        return activityTypeId;
    }

    public void setActivityTypeId(Integer activityTypeId) {
        this.activityTypeId = activityTypeId;
    }

    public Integer getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Integer schemeId) {
        this.schemeId = schemeId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getBulkCode() {
        return bulkCode;
    }

    public void setBulkCode(String bulkCode) {
        this.bulkCode = bulkCode;
    }

    public Integer getRefSiteId() {
        return refSiteId;
    }

    public void setRefSiteId(Integer refSiteId) {
        this.refSiteId = refSiteId;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getSubTotalCost() {
        return subTotalCost;
    }

    public void setSubTotalCost(Integer subTotalCost) {
        this.subTotalCost = subTotalCost;
    }

    public Integer getTotalObjects() {
        return totalObjects;
    }

    public void setTotalObjects(Integer totalObjects) {
        this.totalObjects = totalObjects;
    }

    public String getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(String scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getActualDate() {
        return actualDate;
    }

    public void setActualDate(String actualDate) {
        this.actualDate = actualDate;
    }

    public Boolean getPendingPayment() {
        return pendingPayment;
    }

    public void setPendingPayment(Boolean pendingPayment) {
        this.pendingPayment = pendingPayment;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getActivityMethodId() {
        return activityMethodId;
    }

    public void setActivityMethodId(Integer activityMethodId) {
        this.activityMethodId = activityMethodId;
    }

    public String getCountLogs() {
        return countLogs;
    }

    public void setCountLogs(String countLogs) {
        this.countLogs = countLogs;
    }

    public String getCountObjects() {
        return countObjects;
    }

    public void setCountObjects(String countObjects) {
        this.countObjects = countObjects;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public ActivityMethod getActivityMethod() {
        return activityMethod;
    }

    public void setActivityMethod(ActivityMethod activityMethod) {
        this.activityMethod = activityMethod;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public List<CostDetail> getCost_details() {
        return cost_details;
    }

    public void setCost_details(List<CostDetail> cost_details) {
        this.cost_details = cost_details;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
