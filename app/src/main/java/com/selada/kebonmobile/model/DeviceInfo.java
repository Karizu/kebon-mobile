package com.selada.kebonmobile.model;

public class DeviceInfo {
    private String device_firebase_token;
    private String device_brand;
    private String device_type;
    private String device_series;
    private String device_os;

    public String getDevice_firebase_token() {
        return device_firebase_token;
    }

    public void setDevice_firebase_token(String device_firebase_token) {
        this.device_firebase_token = device_firebase_token;
    }

    public String getDevice_brand() {
        return device_brand;
    }

    public void setDevice_brand(String device_brand) {
        this.device_brand = device_brand;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_series() {
        return device_series;
    }

    public void setDevice_series(String device_series) {
        this.device_series = device_series;
    }

    public String getDevice_os() {
        return device_os;
    }

    public void setDevice_os(String device_os) {
        this.device_os = device_os;
    }
}
