package com.selada.kebonmobile.presentation.notification.model;

public class NotificationModel {
//    private String picture;
    private String notifTitle;
    private String notifText;
    private String date;
    private String fullpath;
    private String typeCode;
    private String dataObject;

    public NotificationModel(String notifTitle, String notifText, String date, String fullpath, String typeCode, String dataObject) {
 //       this.picture = picture;
        this.notifTitle = notifTitle;
        this.notifText = notifText;
        this.date = date;
        this.fullpath = fullpath;
        this.typeCode = typeCode;
        this.dataObject = dataObject;
    }

//    public String getPicture() {
//        return picture;
//    }

    public String getNotifTitle() {
        return notifTitle;
    }

    public String getNotifText() {
        return notifText;
    }

    public String getDate() {
        return date;
    }

    public String getFullpath() {
        return fullpath;
    }

    public void setFullpath(String fullpath) {
        this.fullpath = fullpath;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getDataObject() {
        return dataObject;
    }

    public void setDataObject(String dataObject) {
        this.dataObject = dataObject;
    }
}
