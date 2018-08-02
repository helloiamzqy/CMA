package mananger;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import pojo.JmsMessage;
import pojo.Merchant;
import pojo.RegisterInfo;
import pojo.enums.JmsEnum;

import javax.annotation.PostConstruct;
import javax.jms.*;
@Component
public class JmsSender {
    @Autowired
    private JmsTemplate template;
    @Autowired
    private Queue queue;

    public void send(final String object){
        template.send(queue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage tx = session.createTextMessage();
                tx.setText(object);
                return tx;
            }
        });
    }
//    //TODO
//    @PostConstruct
//    public void send(){
//        template.send(queue, new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                TextMessage tx = session.createTextMessage();
//                JmsMessage jmxTp = new JmsMessage();
//                RegisterInfo registerInfo = new RegisterInfo();
//                registerInfo.setAddress("南方软件园");
//                registerInfo.setComments("评论");
//                registerInfo.setCorporateName("dunn");
//                registerInfo.setCreditCode("441522");
//                registerInfo.setId("asadasdasd");
//                registerInfo.setPhone("123123123");
//                registerInfo.setPicture("asdddddd");
//                registerInfo.setShopName("kfc");
//                registerInfo.setIdCard("asddddddddd");
//                Merchant merchant = new Merchant();
//                merchant.setId("merchantId");
//                registerInfo.setMerchant(merchant);
//                Gson gson = new Gson();
//                jmxTp.setObject(registerInfo);
//                jmxTp.setJmxTpEnum(JmsEnum.APPLY);
//                tx.setText(gson.toJson(registerInfo));
//                return tx;
//            }
//        });
//    }
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        JmsSender jmsSender = context.getBean(JmsSender.class);
        JmsMessage jmxTp = new JmsMessage();
        RegisterInfo registerInfo = new RegisterInfo();
        registerInfo.setAddress("南方软件园");
        registerInfo.setComments("评论");
        registerInfo.setCorporateName("dunn");
        registerInfo.setCreditCode("441522");
        registerInfo.setId("asadasdasd");
        registerInfo.setPhone("123123123");
        registerInfo.setPicture("asdddddd");
        registerInfo.setShopName("kfc");
        registerInfo.setIdCard("asddddddddd");
        Merchant merchant = new Merchant();
        merchant.setId("merchantId");
        registerInfo.setMerchant(merchant);
        Gson gson = new Gson();
        jmxTp.setObject(registerInfo);
        jmxTp.setJmxTpEnum(JmsEnum.APPLY);
        jmsSender.send(gson.toJson(registerInfo));
    }
}
