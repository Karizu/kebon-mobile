
package com.selada.kebonmobile.model.response.commodity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseCommodity {

    @SerializedName("site")
    @Expose
    private Site site;

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

}
