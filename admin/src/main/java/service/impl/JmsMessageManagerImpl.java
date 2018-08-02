package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Advertisement;
import pojo.Complaint;
import pojo.JmsMessage;
import pojo.MerchantInfo;
import pojo.enums.JmsEnum;
import service.AdvertisementManager;
import service.JmsMessageManager;

/**
 * @author JohnGao
 * @date 8/2/2018 11:46 AM
 */
@Service
public class JmsMessageManagerImpl implements JmsMessageManager {

    @Autowired
    private AdvertisementManager adManager;

    @Override
    public Object getMessageEntity(JmsMessage jmsMessage) {
        if (jmsMessage.getJmxTpEnum().equals(JmsEnum.ADVERSITMENT)){
            return adManager.addAd((Advertisement) jmsMessage.getObject());
        }else if (jmsMessage.getJmxTpEnum().equals(JmsEnum.COMPLAIN)){

        }else if (jmsMessage.getJmxTpEnum().equals(JmsEnum.APPLY)){

        }
        return null;
    }

}
