package pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GenericGenerator(strategy="uuid",name="uuid")
    @GeneratedValue(generator="uuid")
    private String id;

    @Column(name = "create_time",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "finish_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishTime;

    @ManyToOne
    @JoinColumn(name="m_id")
    private Merchant merchant;

    @Column(columnDefinition = "varchar2(2) default 1")
    private String status;
    @Column(precision = 9,scale = 2,name="total_price",nullable = false)
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "c_id")
    private Customer customer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", finishTime=" + finishTime +
                ", merchant=" + merchant +
                ", status='" + status + '\'' +
                ", totalPrice=" + totalPrice +
                ", customer=" + customer +
                '}';
    }
}
