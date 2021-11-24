
package com.selada.kebonmobile.model.response.leasesite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selada.kebonmobile.model.response.invoice.PrefferedPaymentAccount;

public class Billing {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type_id")
    @Expose
    private Integer typeId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("reff_id")
    @Expose
    private Integer reffId;
    @SerializedName("total_price")
    @Expose
    private Integer totalPrice;
    @SerializedName("period_cycle")
    @Expose
    private Integer periodCycle;
    @SerializedName("issued_date")
    @Expose
    private String issuedDate;
    @SerializedName("due_date")
    @Expose
    private String dueDate;
    @SerializedName("period")
    @Expose
    private String period;
    @SerializedName("paid_status")
    @Expose
    private Boolean paidStatus;
    @SerializedName("description")
    @Expose
    private String description;
//    @SerializedName("contract")
//    @Expose
//    private String contract;
    @SerializedName("preffered_payment_account")
    @Expose
    private PrefferedPaymentAccount preffered_payment_account;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getReffId() {
        return reffId;
    }

    public void setReffId(Integer reffId) {
        this.reffId = reffId;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getPeriodCycle() {
        return periodCycle;
    }

    public void setPeriodCycle(Integer periodCycle) {
        this.periodCycle = periodCycle;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Boolean getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(Boolean paidStatus) {
        this.paidStatus = paidStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public String getContract() {
//        return contract;
//    }
//
//    public void setContract(String contract) {
//        this.contract = contract;
//    }


    public PrefferedPaymentAccount getPreffered_payment_account() {
        return preffered_payment_account;
    }

    public void setPreffered_payment_account(PrefferedPaymentAccount preffered_payment_account) {
        this.preffered_payment_account = preffered_payment_account;
    }
}
