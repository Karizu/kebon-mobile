
package com.selada.kebonmobile.model.response.commoditymonitoring;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommodityMonitoringResponse {

    @SerializedName("commodity")
    @Expose
    private Commodity commodity;
    @SerializedName("summary")
    @Expose
    private Summary summary;
    @SerializedName("current_sites")
    @Expose
    private List<CurrentSite> currentSites = null;
    @SerializedName("connected_components")
    @Expose
    private List<ConnectedComponent> connectedComponents = null;
    @SerializedName("current_development_details")
    @Expose
    private List<CurrentDevelopmentDetail> currentDevelopmentDetails = null;

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public List<CurrentSite> getCurrentSites() {
        return currentSites;
    }

    public void setCurrentSites(List<CurrentSite> currentSites) {
        this.currentSites = currentSites;
    }

    public List<ConnectedComponent> getConnectedComponents() {
        return connectedComponents;
    }

    public void setConnectedComponents(List<ConnectedComponent> connectedComponents) {
        this.connectedComponents = connectedComponents;
    }

    public List<CurrentDevelopmentDetail> getCurrentDevelopmentDetails() {
        return currentDevelopmentDetails;
    }

    public void setCurrentDevelopmentDetails(List<CurrentDevelopmentDetail> currentDevelopmentDetails) {
        this.currentDevelopmentDetails = currentDevelopmentDetails;
    }

}
