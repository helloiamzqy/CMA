package pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "foods")
public class Food {
    @Id
    @GenericGenerator(strategy="uuid",name="uuid")
    @GeneratedValue(generator="uuid")
    private String id;

    @Column(name = "food_name",nullable = false)
    private String foodName;

    @Column(nullable = false,precision = 9,scale = 2)
    private double price;

    @Column(nullable = false)
    private String picture;

    @Column(columnDefinition = "varchar2(1) default 1")
    private String status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "m_id")
    private Merchant merchant;

    private String comments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Food(String id,String foodName, double price, String picture, String comments) {
        this.id = id;
        this.foodName = foodName;
        this.price = price;
        this.picture = picture;
        this.comments = comments;
    }

    public Food() {
    }
}
