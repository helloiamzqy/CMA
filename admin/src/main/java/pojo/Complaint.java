package pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author JohnGao
 * @date 8/1/2018 8:07 PM
 */
@Entity
@Table(name="complaint")
public class Complaint {
    @Id
    @GenericGenerator(strategy="uuid",name="uuid")
    @GeneratedValue(generator="uuid")
    private String id;
    @Column(nullable = false)
    private String reason;

    @Column(name = "merchant_id",nullable = false)
    private String merchantId;

    @Column(name = "order_id",nullable = false)
    private String orderId;

    @Column(name = "merchant_name",nullable = false)
    private String merchantName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time",nullable = false)
    private Date createTime;

    @Column(updatable = false)
    private String className = "Complaint";

    @Column
    private String isRead = "false";

    public Complaint() {
    }

    public Complaint(String reason, String merchantId, String orderId, String merchantName, Date createTime, String className, String isRead) {
        this.reason = reason;
        this.merchantId = merchantId;
        this.orderId = orderId;
        this.merchantName = merchantName;
        this.createTime = createTime;
        this.className = className;
        this.isRead = isRead;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    @Override
    public String toString() {
        return "Complaint{" +
                "id='" + id + '\'' +
                ", reason='" + reason + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", shopName='" + merchantName + '\'' +
                ", createTime=" + createTime +
                ", className='" + className + '\'' +
                ", isRead='" + isRead + '\'' +
                '}';
    }
}
