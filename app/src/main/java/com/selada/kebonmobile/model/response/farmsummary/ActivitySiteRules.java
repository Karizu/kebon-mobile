package com.selada.kebonmobile.model.response.farmsummary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActivitySiteRules {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("site_id")
    @Expose
    private Integer siteId;
    @SerializedName("activity_type_id")
    @Expose
    private Integer activityTypeId;
    @SerializedName("rule_string")
    @Expose
    private String ruleString;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getActivityTypeId() {
        return activityTypeId;
    }

    public void setActivityTypeId(Integer activityTypeId) {
        this.activityTypeId = activityTypeId;
    }

    public String getRuleString() {
        return ruleString;
    }

    public void setRuleString(String ruleString) {
        this.ruleString = ruleString;
    }

}
