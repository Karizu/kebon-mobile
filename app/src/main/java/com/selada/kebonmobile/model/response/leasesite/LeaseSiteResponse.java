
package com.selada.kebonmobile.model.response.leasesite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LeaseSiteResponse implements Serializable {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("first_invoice")
    @Expose
    private FirstInvoice firstInvoice;
    @SerializedName("contract")
    @Expose
    private Contract contract;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FirstInvoice getFirstInvoice() {
        return firstInvoice;
    }

    public void setFirstInvoice(FirstInvoice firstInvoice) {
        this.firstInvoice = firstInvoice;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

}
