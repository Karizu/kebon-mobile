
package com.selada.kebonmobile.model.response.farmactivities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("activity_id")
    @Expose
    private Integer activityId;
    @SerializedName("activity_status_id")
    @Expose
    private Integer activityStatusId;
    @SerializedName("activity_status_name")
    @Expose
    private String activityStatusName;
    @SerializedName("activity_status_code")
    @Expose
    private String activityStatusCode;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("total_success_objects")
    @Expose
    private Integer totalSuccessObjects;
    @SerializedName("total_failed_objects")
    @Expose
    private Integer totalFailedObjects;
    @SerializedName("finished_date")
    @Expose
    private String finishedDate;
    @SerializedName("bulk_activity_code")
    @Expose
    private String bulkActivityCode;
    @SerializedName("created_date")
    @Expose
    private String created_date;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getActivityStatusId() {
        return activityStatusId;
    }

    public void setActivityStatusId(Integer activityStatusId) {
        this.activityStatusId = activityStatusId;
    }

    public String getActivityStatusName() {
        return activityStatusName;
    }

    public void setActivityStatusName(String activityStatusName) {
        this.activityStatusName = activityStatusName;
    }

    public String getActivityStatusCode() {
        return activityStatusCode;
    }

    public void setActivityStatusCode(String activityStatusCode) {
        this.activityStatusCode = activityStatusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotalSuccessObjects() {
        return totalSuccessObjects;
    }

    public void setTotalSuccessObjects(Integer totalSuccessObjects) {
        this.totalSuccessObjects = totalSuccessObjects;
    }

    public Integer getTotalFailedObjects() {
        return totalFailedObjects;
    }

    public void setTotalFailedObjects(Integer totalFailedObjects) {
        this.totalFailedObjects = totalFailedObjects;
    }

    public String getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(String finishedDate) {
        this.finishedDate = finishedDate;
    }

    public String getBulkActivityCode() {
        return bulkActivityCode;
    }

    public void setBulkActivityCode(String bulkActivityCode) {
        this.bulkActivityCode = bulkActivityCode;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
}
