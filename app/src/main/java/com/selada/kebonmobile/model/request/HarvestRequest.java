package com.selada.kebonmobile.model.request;

public class HarvestRequest {
    private String activity_type_code;
    private String commodity_id;
    private String activity_method_code;
    private String total_objects;
    private DeliveryAddress delivery_address;

    public String getActivity_type_code() {
        return activity_type_code;
    }

    public void setActivity_type_code(String activity_type_code) {
        this.activity_type_code = activity_type_code;
    }

    public String getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(String commodity_id) {
        this.commodity_id = commodity_id;
    }

    public String getActivity_method_code() {
        return activity_method_code;
    }

    public void setActivity_method_code(String activity_method_code) {
        this.activity_method_code = activity_method_code;
    }

    public String getTotal_objects() {
        return total_objects;
    }

    public void setTotal_objects(String total_objects) {
        this.total_objects = total_objects;
    }

    public DeliveryAddress getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(DeliveryAddress delivery_address) {
        this.delivery_address = delivery_address;
    }
}
