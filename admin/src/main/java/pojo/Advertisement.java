package pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author JohnGao
 * @date 8/1/2018 8:08 PM
 */
@Entity
@Table(name="advertisement")
public class Advertisement {
    @Id
    @GenericGenerator(strategy="uuid",name="uuid")
    @GeneratedValue(generator="uuid")
    private String id;

    @Column(name = "merchant_id",nullable = false)
    private String merchantId;

    @Column(name = "merchant_name",nullable = false)
    private String merchantName;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false,columnDefinition = "varchar2(10) default 1")
    private String status;

    @Column(nullable = false)
    private double price;

    public Advertisement(String merchantId, String merchantName, String picture, String status, double price) {
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.picture = picture;
        this.status = status;
        this.price = price;
    }

    public Advertisement() {
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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
