
package com.selada.kebonmobile.model.response.commoditymonitoring;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConnectedComponent {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("ts")
    @Expose
    private String ts;
    @SerializedName("commands")
    @Expose
    private List<Object> commands = null;
    @SerializedName("independent_command")
    @Expose
    private Boolean independentCommand;
    @SerializedName("socket_address")
    @Expose
    private String socketAddress;
    @SerializedName("app_url_address")
    @Expose
    private String appUrlAddress;
    @SerializedName("socket_type")
    @Expose
    private String socketType;
    @SerializedName("open_socket_when_parent_up")
    @Expose
    private Boolean openSocketWhenParentUp;
    @SerializedName("childs")
    @Expose
    private List<Object> childs = null;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("latest_data")
    @Expose
    private LatestData latestData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public List<Object> getCommands() {
        return commands;
    }

    public void setCommands(List<Object> commands) {
        this.commands = commands;
    }

    public Boolean getIndependentCommand() {
        return independentCommand;
    }

    public void setIndependentCommand(Boolean independentCommand) {
        this.independentCommand = independentCommand;
    }

    public String getSocketAddress() {
        return socketAddress;
    }

    public void setSocketAddress(String socketAddress) {
        this.socketAddress = socketAddress;
    }

    public String getAppUrlAddress() {
        return appUrlAddress;
    }

    public void setAppUrlAddress(String appUrlAddress) {
        this.appUrlAddress = appUrlAddress;
    }

    public String getSocketType() {
        return socketType;
    }

    public void setSocketType(String socketType) {
        this.socketType = socketType;
    }

    public Boolean getOpenSocketWhenParentUp() {
        return openSocketWhenParentUp;
    }

    public void setOpenSocketWhenParentUp(Boolean openSocketWhenParentUp) {
        this.openSocketWhenParentUp = openSocketWhenParentUp;
    }

    public List<Object> getChilds() {
        return childs;
    }

    public void setChilds(List<Object> childs) {
        this.childs = childs;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public LatestData getLatestData() {
        return latestData;
    }

    public void setLatestData(LatestData latestData) {
        this.latestData = latestData;
    }

}
