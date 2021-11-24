
package com.selada.kebonmobile.model.response.invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account {

    @SerializedName("payment_method_id")
    @Expose
    private Integer paymentMethodId;
    @SerializedName("account_name")
    @Expose
    private String accountName;
    @SerializedName("account_issuer_institution_name")
    @Expose
    private String accountIssuerInstitutionName;
    @SerializedName("account_number")
    @Expose
    private String accountNumber;
    @SerializedName("is_active")
    @Expose
    private boolean is_active;
    @SerializedName("payment_method")
    @Expose
    private PaymentMethod payment_method;

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountIssuerInstitutionName() {
        return accountIssuerInstitutionName;
    }

    public void setAccountIssuerInstitutionName(String accountIssuerInstitutionName) {
        this.accountIssuerInstitutionName = accountIssuerInstitutionName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public PaymentMethod getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(PaymentMethod payment_method) {
        this.payment_method = payment_method;
    }
}
