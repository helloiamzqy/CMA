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

    @Column(nullable = false)
    private String picture;

    public Advertisement(String merchantId, String picture) {
        this.merchantId = merchantId;
        this.picture = picture;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
