package com.selada.kebonmobile.presentation.notification.model;

public class NotificationModel {
//    private String picture;
    private String notifTitle;
    private String notifText;
    private String date;

    public NotificationModel(String notifTitle, String notifText, String date) {
 //       this.picture = picture;
        this.notifTitle = notifTitle;
        this.notifText = notifText;
        this.date = date;
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

}
