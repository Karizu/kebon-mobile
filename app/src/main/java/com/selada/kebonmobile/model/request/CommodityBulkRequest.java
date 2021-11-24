package com.selada.kebonmobile.model.request;

import java.util.List;

public class CommodityBulkRequest {
    private Data data;
    private List<Activities> activities;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<Activities> getActivities() {
        return activities;
    }

    public void setActivities(List<Activities> activities) {
        this.activities = activities;
    }
}
