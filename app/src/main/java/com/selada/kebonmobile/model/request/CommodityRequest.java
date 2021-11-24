package com.selada.kebonmobile.model.request;

public class CommodityRequest {
    private String activity_type_code;
    private int scheme_id;
    private int commodity_id;
    private int total_objects;
    private int preffered_payment_account_id;

    public int getScheme_id() {
        return scheme_id;
    }

    public void setScheme_id(int scheme_id) {
        this.scheme_id = scheme_id;
    }

    public String getActivity_type_code() {
        return activity_type_code;
    }

    public void setActivity_type_code(String activity_type_code) {
        this.activity_type_code = activity_type_code;
    }

    public int getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(int commodity_id) {
        this.commodity_id = commodity_id;
    }

    public int getTotal_objects() {
        return total_objects;
    }

    public void setTotal_objects(int total_objects) {
        this.total_objects = total_objects;
    }

    public int getPreffered_payment_account_id() {
        return preffered_payment_account_id;
    }

    public void setPreffered_payment_account_id(int preffered_payment_account_id) {
        this.preffered_payment_account_id = preffered_payment_account_id;
    }
}
