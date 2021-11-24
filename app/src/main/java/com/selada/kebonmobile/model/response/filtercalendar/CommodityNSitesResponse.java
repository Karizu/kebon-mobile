
package com.selada.kebonmobile.model.response.filtercalendar;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommodityNSitesResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("sites")
    @Expose
    private List<Site> sites = null;
    @SerializedName("commodities")
    @Expose
    private List<Commodity> commodities = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    public List<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<Commodity> commodities) {
        this.commodities = commodities;
    }

}
