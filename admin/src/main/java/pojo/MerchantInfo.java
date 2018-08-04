package pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author JohnGao
 * @date 8/1/2018 8:08 PM
 */
@Entity
@Table(name="merchant_info")
public class MerchantInfo {
    @Id
    @GenericGenerator(strategy="uuid",name="uuid")
    @GeneratedValue(generator="uuid")
    private String id;
    @Column(name = "merchant_id",nullable = false)
    private String merchantId;

    @Column(name = "credit_code",nullable = false)
    private String creditCode;

    @Column(name = "id_card",nullable = false)
    private String idCard;

    @Column(name = "corporate_name",nullable = false)
    private String corporateName;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false)
    private String phone;

    @Column(name = "shop_name",nullable = false)
    private String shopName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false,columnDefinition = "varchar2(10) default 0")
    private String status;

    @Column(nullable = false)
    private String comments;

    @Column(updatable = false)
    private String className = "MerchantInfo";

    @Column
    private String isRead = "false";

    public MerchantInfo(String merchantId, String creditCode, String idCard, String corporateName, String picture, String phone, String shopName, String address, String status, String comments) {
        this.merchantId = merchantId;
        this.creditCode = creditCode;
        this.idCard = idCard;
        this.corporateName = corporateName;
        this.picture = picture;
        this.phone = phone;
        this.shopName = shopName;
        this.address = address;
        this.status = status;
        this.comments = comments;
    }

    public MerchantInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }
}
