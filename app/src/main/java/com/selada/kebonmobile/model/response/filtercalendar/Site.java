
package com.selada.kebonmobile.model.response.filtercalendar;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Site {

    @SerializedName("site_id")
    @Expose
    private Integer siteId;
    @SerializedName("site_name")
    @Expose
    private String siteName;

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

}
