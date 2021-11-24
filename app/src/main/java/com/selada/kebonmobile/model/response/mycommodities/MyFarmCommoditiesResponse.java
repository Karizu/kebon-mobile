
package com.selada.kebonmobile.model.response.mycommodities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyFarmCommoditiesResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("commodities")
    @Expose
    private List<Commodity> commodities;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<Commodity> commodities) {
        this.commodities = commodities;
    }

}
