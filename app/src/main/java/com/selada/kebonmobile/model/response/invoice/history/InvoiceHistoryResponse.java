
package com.selada.kebonmobile.model.response.invoice.history;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InvoiceHistoryResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("invoices")
    @Expose
    private List<Invoices> invoices;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Invoices> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoices> invoices) {
        this.invoices = invoices;
    }

}
