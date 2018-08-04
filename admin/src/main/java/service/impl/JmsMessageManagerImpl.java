package service.impl;

import com.google.gson.Gson;
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
    public String getMessageEntity(JmsMessage jmsMessage) {
        Gson gson = new Gson();

        if (jmsMessage.getJmsEnum().equals(JmsEnum.ADVERSITMENT)){
            return gson.toJson(adManager.addAd( gson.fromJson(gson.toJson(jmsMessage.getObject()),Advertisement.class)));
        }else if (jmsMessage.getJmsEnum().equals(JmsEnum.COMPLAIN)){
            return gson.toJson(complaintManager.addComplaint(gson.fromJson(gson.toJson(jmsMessage.getObject()),Complaint.class)));
        }else if (jmsMessage.getJmsEnum().equals(JmsEnum.APPLY)){
            return gson.toJson(merchantInfoManager.addMerchantInfo(gson.fromJson(gson.toJson(jmsMessage.getObject()),MerchantInfo.class)));
        }
        return null;
    }

    @Override
    public String getKey(JmsMessage jmsMessage) {
        Gson gson = new Gson();

        if (jmsMessage.getJmsEnum().equals(JmsEnum.ADVERSITMENT)){
            return adManager.addAd( gson.fromJson(gson.toJson(jmsMessage.getObject()),Advertisement.class)).getClassName();
        }else if (jmsMessage.getJmsEnum().equals(JmsEnum.COMPLAIN)){
            return complaintManager.addComplaint(gson.fromJson(gson.toJson(jmsMessage.getObject()),Complaint.class)).getClassName();
        }else if (jmsMessage.getJmsEnum().equals(JmsEnum.APPLY)){
            return merchantInfoManager.addMerchantInfo(gson.fromJson(gson.toJson(jmsMessage.getObject()),MerchantInfo.class)).getClassName();
        }
        return null;
    }
}
