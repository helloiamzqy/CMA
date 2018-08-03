package service.impl;

import com.google.gson.Gson;
import manager.JmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Complaint;
import pojo.JmsMessage;
import pojo.enums.JmsEnum;
import service.ComplainService;

@Service
public class ComplainServiceImpl implements ComplainService {
    @Autowired
    private JmsSender jmsSender;

    @Override
    public void sendComplainToAdmin(Complaint complaint) {
            JmsMessage jmsMessage = new JmsMessage();
            jmsMessage.setJmsEnum(JmsEnum.COMPLAIN);
            jmsMessage.setObject(complaint);
            String result=new Gson().toJson(jmsMessage);
            jmsSender.send(result);
        }
}
