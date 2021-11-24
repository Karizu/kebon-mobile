
package com.selada.kebonmobile.model.response.harvest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActivityMethod {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("illustration_image_id")
    @Expose
    private Integer illustrationImageId;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("harvest_description")
    @Expose
    private String harvest_description;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIllustrationImageId() {
        return illustrationImageId;
    }

    public void setIllustrationImageId(Integer illustrationImageId) {
        this.illustrationImageId = illustrationImageId;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getHarvest_description() {
        return harvest_description;
    }

    public void setHarvest_description(String harvest_description) {
        this.harvest_description = harvest_description;
    }
}
