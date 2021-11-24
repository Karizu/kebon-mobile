
package com.selada.kebonmobile.model.response.leasesite;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FirstInvoice {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("billing_id")
    @Expose
    private Integer billingId;
    @SerializedName("due_date")
    @Expose
    private String dueDate;
    @SerializedName("issued_date")
    @Expose
    private String issuedDate;
    @SerializedName("cycle_seq")
    @Expose
    private Integer cycleSeq;
    @SerializedName("billing")
    @Expose
    private Billing billing;
    @SerializedName("paymentMethods")
    @Expose
    private List<PaymentMethod> paymentMethods = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getBillingId() {
        return billingId;
    }

    public void setBillingId(Integer billingId) {
        this.billingId = billingId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Integer getCycleSeq() {
        return cycleSeq;
    }

    public void setCycleSeq(Integer cycleSeq) {
        this.cycleSeq = cycleSeq;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

}
