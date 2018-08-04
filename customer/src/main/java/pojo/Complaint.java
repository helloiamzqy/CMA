package pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author harper
 * @date 8/42018 8:07 PM
 */
public class Complaint {
    private String id;
    private String reason;
    private String merchantId;
    private String merchantName;
    private String orderId;
    private Date createTime;

    public Complaint() {
    }

    public Complaint(String id, String reason, String merchantId, String merchantName, String orderId, Date createTime) {
        this.id = id;
        this.reason = reason;
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.orderId = orderId;
        this.createTime = createTime;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "id='" + id + '\'' +
                ", reason='" + reason + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
