
package com.selada.kebonmobile.model.response.detailcommodities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailCommoditiesResponse {

    @SerializedName("commodity")
    @Expose
    private Commodity commodity;
    @SerializedName("active_sites")
    @Expose
    private List<ActiveSite> activeSites = null;

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public List<ActiveSite> getActiveSites() {
        return activeSites;
    }

    public void setActiveSites(List<ActiveSite> activeSites) {
        this.activeSites = activeSites;
    }

}
