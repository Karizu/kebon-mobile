
package com.selada.kebonmobile.model.response.leasesite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selada.kebonmobile.model.response.commodity.MainImage;

public class Account {

    @SerializedName("id")
    @Expose
    private Integer id;
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
    @SerializedName("image")
    @Expose
    private MainImage image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public MainImage getImage() {
        return image;
    }

    public void setImage(MainImage image) {
        this.image = image;
    }
}
