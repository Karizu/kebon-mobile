
package com.selada.kebonmobile.model.response.mycommodities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selada.kebonmobile.model.response.commodity.MainImage;

public class Commodity {

    @SerializedName("ref_current_commodity_id")
    @Expose
    private Integer ref_current_commodity_id;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("object_count")
    @Expose
    private String objectCount;
    @SerializedName("site_count")
    @Expose
    private String siteCount;
    @SerializedName("main_image")
    @Expose
    private MainImage mainImage;
    @SerializedName("object_type_label")
    @Expose
    private String object_type_label;

    public String getObject_type_label() {
        return object_type_label;
    }

    public void setObject_type_label(String object_type_label) {
        this.object_type_label = object_type_label;
    }

    public Integer getRef_current_commodity_id() {
        return ref_current_commodity_id;
    }

    public void setRef_current_commodity_id(Integer ref_current_commodity_id) {
        this.ref_current_commodity_id = ref_current_commodity_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectCount() {
        return objectCount;
    }

    public void setObjectCount(String objectCount) {
        this.objectCount = objectCount;
    }

    public String getSiteCount() {
        return siteCount;
    }

    public void setSiteCount(String siteCount) {
        this.siteCount = siteCount;
    }

    public MainImage getMainImage() {
        return mainImage;
    }

    public void setMainImage(MainImage mainImage) {
        this.mainImage = mainImage;
    }
}
