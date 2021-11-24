
package com.selada.kebonmobile.model.response.filtercalendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Commodity {

    @SerializedName("commodity_id")
    @Expose
    private Integer commodityId;
    @SerializedName("commodity_name")
    @Expose
    private String commodityName;

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

}
