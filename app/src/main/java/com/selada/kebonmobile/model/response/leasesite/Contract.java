
package com.selada.kebonmobile.model.response.leasesite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selada.kebonmobile.model.response.invoice.PackageObj;

public class Contract {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("package_id")
    @Expose
    private Integer packageId;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("issued_date")
    @Expose
    private String issuedDate;
    @SerializedName("effective_date")
    @Expose
    private String effectiveDate;
    @SerializedName("expiry_date")
    @Expose
    private String expiryDate;
    @SerializedName("lease_duration_number")
    @Expose
    private Integer leaseDurationNumber;
    @SerializedName("lease_duration_code")
    @Expose
    private String leaseDurationCode;
    @SerializedName("lease_duration_name")
    @Expose
    private String lease_duration_name;
    @SerializedName("lease_payment_term_code")
    @Expose
    private String leasePaymentTermCode;
    @SerializedName("lease_per_duration_price")
    @Expose
    private Integer leasePerDurationPrice;
    @SerializedName("lease_total_price")
    @Expose
    private Integer leaseTotalPrice;
    @SerializedName("lease_quantity")
    @Expose
    private Integer leaseQuantity;
    @SerializedName("package")
    @Expose
    private Package _package;
    @SerializedName("package_obj")
    @Expose
    private PackageObj package_obj;
    @SerializedName("user")
    @Expose
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getLeaseDurationNumber() {
        return leaseDurationNumber;
    }

    public void setLeaseDurationNumber(Integer leaseDurationNumber) {
        this.leaseDurationNumber = leaseDurationNumber;
    }

    public String getLeaseDurationCode() {
        return leaseDurationCode;
    }

    public void setLeaseDurationCode(String leaseDurationCode) {
        this.leaseDurationCode = leaseDurationCode;
    }

    public String getLeasePaymentTermCode() {
        return leasePaymentTermCode;
    }

    public void setLeasePaymentTermCode(String leasePaymentTermCode) {
        this.leasePaymentTermCode = leasePaymentTermCode;
    }

    public Integer getLeasePerDurationPrice() {
        return leasePerDurationPrice;
    }

    public void setLeasePerDurationPrice(Integer leasePerDurationPrice) {
        this.leasePerDurationPrice = leasePerDurationPrice;
    }

    public Integer getLeaseTotalPrice() {
        return leaseTotalPrice;
    }

    public void setLeaseTotalPrice(Integer leaseTotalPrice) {
        this.leaseTotalPrice = leaseTotalPrice;
    }

    public Integer getLeaseQuantity() {
        return leaseQuantity;
    }

    public void setLeaseQuantity(Integer leaseQuantity) {
        this.leaseQuantity = leaseQuantity;
    }

    public Package getPackage() {
        return _package;
    }

    public void setPackage(Package _package) {
        this._package = _package;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Package get_package() {
        return _package;
    }

    public void set_package(Package _package) {
        this._package = _package;
    }

    public PackageObj getPackage_obj() {
        return package_obj;
    }

    public void setPackage_obj(PackageObj package_obj) {
        this.package_obj = package_obj;
    }

    public String getLease_duration_name() {
        return lease_duration_name;
    }

    public void setLease_duration_name(String lease_duration_name) {
        this.lease_duration_name = lease_duration_name;
    }
}
