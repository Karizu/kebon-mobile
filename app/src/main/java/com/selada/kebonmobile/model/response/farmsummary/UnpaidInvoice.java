
package com.selada.kebonmobile.model.response.farmsummary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnpaidInvoice {

    @SerializedName("invoice_id")
    @Expose
    private Integer invoiceId;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("issued_date")
    @Expose
    private String issuedDate;
    @SerializedName("sent_date")
    @Expose
    private String sentDate;
    @SerializedName("due_date")
    @Expose
    private String dueDate;
    @SerializedName("contract_id")
    @Expose
    private Integer contractId;
    @SerializedName("billing_id")
    @Expose
    private Integer billingId;
    @SerializedName("billing_type_id")
    @Expose
    private Integer billingTypeId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("total_price")
    @Expose
    private Integer totalPrice;
    @SerializedName("billing_period")
    @Expose
    private String billingPeriod;
    @SerializedName("billing_description")
    @Expose
    private String billingDescription;
    @SerializedName("contract")
    @Expose
    private Contract contract;

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Integer getBillingId() {
        return billingId;
    }

    public void setBillingId(Integer billingId) {
        this.billingId = billingId;
    }

    public Integer getBillingTypeId() {
        return billingTypeId;
    }

    public void setBillingTypeId(Integer billingTypeId) {
        this.billingTypeId = billingTypeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBillingPeriod() {
        return billingPeriod;
    }

    public void setBillingPeriod(String billingPeriod) {
        this.billingPeriod = billingPeriod;
    }

    public String getBillingDescription() {
        return billingDescription;
    }

    public void setBillingDescription(String billingDescription) {
        this.billingDescription = billingDescription;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

}
