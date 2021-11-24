package com.selada.kebonmobile.model.response.commoditymonitoring;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhaseTimeline {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("phase_name")
    @Expose
    private String phaseName;
    @SerializedName("phase_code")
    @Expose
    private String phaseCode;
    @SerializedName("phase_seq")
    @Expose
    private Integer phaseSeq;
    @SerializedName("is_current")
    @Expose
    private Boolean isCurrent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhaseName() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public String getPhaseCode() {
        return phaseCode;
    }

    public void setPhaseCode(String phaseCode) {
        this.phaseCode = phaseCode;
    }

    public Integer getPhaseSeq() {
        return phaseSeq;
    }

    public void setPhaseSeq(Integer phaseSeq) {
        this.phaseSeq = phaseSeq;
    }

    public Boolean getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Boolean isCurrent) {
        this.isCurrent = isCurrent;
    }
}
