
package com.selada.kebonmobile.model.response.invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contract {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("created_by")
    @Expose
    private Integer createdBy;
    @SerializedName("deleted_by")
    @Expose
    private String deletedBy;
    @SerializedName("deleted_date")
    @Expose
    private String deletedDate;
    @SerializedName("modified_by")
    @Expose
    private String modifiedBy;
    @SerializedName("modified_date")
    @Expose
    private String modifiedDate;
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
    @SerializedName("end_date")
    @Expose
    private String endDate;
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
    @SerializedName("package_obj")
    @Expose
    private PackageObj package_obj;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(String deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
