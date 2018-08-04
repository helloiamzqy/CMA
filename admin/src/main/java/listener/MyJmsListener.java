package listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pojo.JmsMessage;
import service.JmsMessageManager;
import service.WebSocketManager;
import utils.JsonParse;
import utils.JsonParseByJackson;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component(value = "myListener")
public class MyJmsListener implements MessageListener {

    @Autowired
    private WebSocketManager manager;

    public MyJmsListener(){
        System.out.println("MyJmsListener : init");
    }
    @Autowired
    private JmsMessageManager jmsManager;

    public void onMessage(Message message){
        TextMessage tmsg = (TextMessage)message;
        try {
            System.out.println("监听器收到的Json数据："+ tmsg.getText());
            JsonParse<JmsMessage> parse = new JsonParseByJackson<>();
            JmsMessage jmsMessage = parse.parseJsonToObject(JmsMessage.class,tmsg.getText());
            manager.sendMessage(jmsManager.getKey(jmsMessage),jmsManager.getMessageEntity(jmsMessage));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
