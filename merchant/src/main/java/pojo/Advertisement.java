package pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dto.AdvertisementDto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "advertisements")
public class Advertisement {
    @Id
    @GenericGenerator(strategy="uuid",name="uuid")
    @GeneratedValue(generator="uuid")
    private String id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "m_id")
    private Merchant merchant;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false,precision = 9,scale = 2)
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public AdvertisementDto toDto(){
        AdvertisementDto adDto = new AdvertisementDto();
        adDto.setMerchantId(this.merchant.getId());
        adDto.setMerchantName(this.merchant.getName());
        adDto.setPicture(this.picture);
        adDto.setPrice(this.price);
        return  adDto;
    }
}
