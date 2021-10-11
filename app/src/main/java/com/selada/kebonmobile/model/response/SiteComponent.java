
package com.selada.kebonmobile.model.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SiteComponent {

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
    private List<String> commands;
    @SerializedName("socket_address")
    @Expose
    private String socketAddress;
    @SerializedName("app_url_address")
    @Expose
    private String appUrlAddress;
    @SerializedName("childs")
    @Expose
    private List<String> childs;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("independent_command")
    @Expose
    private Boolean independentCommand;
    @SerializedName("socket_type")
    @Expose
    private String socketType;
    @SerializedName("open_socket_when_parent_up")
    @Expose
    private Boolean openSocketWhenParentUp;

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

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
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

    public List<String> getChilds() {
        return childs;
    }

    public void setChilds(List<String> childs) {
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

    public Boolean getIndependentCommand() {
        return independentCommand;
    }

    public void setIndependentCommand(Boolean independentCommand) {
        this.independentCommand = independentCommand;
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

}
