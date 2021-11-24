package com.selada.kebonmobile.model.response.farmsummary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActivityRegularDefinedCosts {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("cost")
    @Expose
    private Integer cost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

}
