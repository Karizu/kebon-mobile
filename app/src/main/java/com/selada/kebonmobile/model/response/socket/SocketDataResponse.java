
package com.selada.kebonmobile.model.response.socket;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocketDataResponse {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("room")
    @Expose
    private String room;
    @SerializedName("msg_code")
    @Expose
    private String msgCode;
    @SerializedName("msg_scode")
    @Expose
    private List<MsgScode> msgScode;
    @SerializedName("msg_data")
    @Expose
    private List<MsgDatum> msgData;
    @SerializedName("msg_ucode")
    @Expose
    private String msgUcode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public List<MsgScode> getMsgScode() {
        return msgScode;
    }

    public void setMsgScode(List<MsgScode> msgScode) {
        this.msgScode = msgScode;
    }

    public List<MsgDatum> getMsgData() {
        return msgData;
    }

    public void setMsgData(List<MsgDatum> msgData) {
        this.msgData = msgData;
    }

    public String getMsgUcode() {
        return msgUcode;
    }

    public void setMsgUcode(String msgUcode) {
        this.msgUcode = msgUcode;
    }

}
