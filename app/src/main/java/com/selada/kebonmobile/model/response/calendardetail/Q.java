
package com.selada.kebonmobile.model.response.calendardetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Q {

    @SerializedName("site_id")
    @Expose
    private String siteId;
    @SerializedName("commodity_id")
    @Expose
    private String commodityId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("dated")
    @Expose
    private String dated;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDated() {
        return dated;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

}
