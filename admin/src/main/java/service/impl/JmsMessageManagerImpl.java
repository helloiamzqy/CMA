package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Advertisement;
import pojo.Complaint;
import pojo.JmsMessage;
import pojo.MerchantInfo;
import pojo.enums.JmsEnum;
import service.AdvertisementManager;
import service.ComplaintManager;
import service.JmsMessageManager;
import service.MerchantInfoManager;
import utils.JsonParseByJackson;

import java.util.Map;

/**
 * @author JohnGao
 * @date 8/2/2018 11:46 AM
 */
@Service
public class JmsMessageManagerImpl implements JmsMessageManager {

    @Autowired
    private AdvertisementManager adManager;
    @Autowired
    private ComplaintManager complaintManager;
    @Autowired
    private MerchantInfoManager merchantInfoManager;

    @Override
    public Object getMessageEntity(JmsMessage jmsMessage) {
        JsonParseByJackson<String> parse = new JsonParseByJackson<>();
        String json = parse.parseMapToJson((Map<String,String>)jmsMessage.getObject());
        if (jmsMessage.getJmsEnum().equals(JmsEnum.ADVERSITMENT)){
            return adManager.addAd((Advertisement) jmsMessage.getObject());
        }else if (jmsMessage.getJmsEnum().equals(JmsEnum.COMPLAIN)){
            return complaintManager.addComplaint((Complaint) jmsMessage.getObject());
        }else if (jmsMessage.getJmsEnum().equals(JmsEnum.APPLY)){
         //   return merchantInfoManager.addMerchantInfo((MerchantInfo)parse.parseJsonToObject(Class<MerchantInfo>,json));
        }
        return null;
    }

}
