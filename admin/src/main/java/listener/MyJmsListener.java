package listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.JmsMessage;
import service.JmsMessageManager;
import service.WebSocketManager;
import utils.JsonParse;
import utils.JsonParseByJackson;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author JohnGao
 */
@Component(value = "myListener")
public class MyJmsListener implements MessageListener {

    @Autowired
    private WebSocketManager manager;

    @Autowired
    private JmsMessageManager jmsManager;

    public MyJmsListener() {
        System.out.println("MyJmsListener : init");
    }

    public void onMessage(Message message) {
        TextMessage tmsg = (TextMessage) message;
        try {
            JsonParse<JmsMessage> parse = new JsonParseByJackson<>();
            JmsMessage jmsMessage = parse.parseJsonToObject(JmsMessage.class, tmsg.getText());
            manager.sendMessage(jmsManager.getKey(jmsMessage), jmsManager.getMessageEntity(jmsMessage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
