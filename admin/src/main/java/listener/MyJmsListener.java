package listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.Complaint;
import pojo.JmsMessage;
import pojo.enums.JmsEnum;
import service.JmsMessageManager;
import utils.JsonParse;
import utils.JsonParseByJackson;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component(value = "myListener")
public class MyJmsListener implements MessageListener {
    @Autowired
    private JmsMessageManager jmsManager;
    public void onMessage(Message message){
        TextMessage tmsg = (TextMessage)message;
        try {
            System.out.println("my listener receive:"+ tmsg.getText());
            JsonParse<JmsMessage> parse = new JsonParseByJackson<>();
            JmsMessage jmsMessage = parse.parseJsonToObject(JmsMessage.class,tmsg.getText());
            System.out.println(jmsMessage.toString());
            jmsManager.getMessageEntity(jmsMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
