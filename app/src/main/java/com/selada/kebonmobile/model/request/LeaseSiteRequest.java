package com.selada.kebonmobile.model.request;

public class LeaseSiteRequest {
    private String package_id;
    private String lease_duration;
    private String quantity;
    private String preffered_payment_account_id;

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }

    public String getLease_duration() {
        return lease_duration;
    }

    public void setLease_duration(String lease_duration) {
        this.lease_duration = lease_duration;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPreffered_payment_account_id() {
        return preffered_payment_account_id;
    }

    public void setPreffered_payment_account_id(String preffered_payment_account_id) {
        this.preffered_payment_account_id = preffered_payment_account_id;
    }
}
