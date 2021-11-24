
package com.selada.kebonmobile.model.response.commoditymonitoring;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Commodity {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("alias_name")
    @Expose
    private String aliasName;
    @SerializedName("latin_name")
    @Expose
    private String latinName;
    @SerializedName("main_image_id")
    @Expose
    private Integer mainImageId;
    @SerializedName("main_image")
    @Expose
    private MainImage mainImage;

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

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public Integer getMainImageId() {
        return mainImageId;
    }

    public void setMainImageId(Integer mainImageId) {
        this.mainImageId = mainImageId;
    }

    public MainImage getMainImage() {
        return mainImage;
    }

    public void setMainImage(MainImage mainImage) {
        this.mainImage = mainImage;
    }

}
