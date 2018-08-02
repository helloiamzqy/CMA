package pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "basic_info")
public class BasicInfo {

    @Id
    @GenericGenerator(strategy="uuid",name="uuid")
    @GeneratedValue(generator="uuid")
    private String id;

    @OneToOne
    @JoinColumn(name="m_id")
    private Merchant merchant;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date openTime;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date closeTime;

    @Column(columnDefinition = "varchar2(255) default 3")
    private String delivery;

    @Column(nullable = false)
    private double deliFee;

    @Column(nullable = false)
    private String picture;

    private String slogan;

    private String status;

    private String comments;

    @Column(nullable = false,name = "shop_name")
    private String shopName;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }
    @Column(columnDefinition = "varchar2(255) default 3")
    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public double getDeliFee() {
        return deliFee;
    }

    public void setDeliFee(double deliFee) {
        this.deliFee = deliFee;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
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
}
