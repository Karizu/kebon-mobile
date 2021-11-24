
package com.selada.kebonmobile.model.response.invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InvoiceDetailResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("invoice")
    @Expose
    private Invoice invoice;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

}
