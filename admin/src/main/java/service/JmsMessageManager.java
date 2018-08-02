package service;

import org.springframework.stereotype.Service;
import pojo.Advertisement;
import pojo.Complaint;
import pojo.JmsMessage;
import pojo.MerchantInfo;

/**
 * @author JohnGao
 * @date 8/2/2018 11:36 AM
 */

public interface JmsMessageManager {
    public Object getMessageEntity(JmsMessage jmsMessage);
}
