package listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import containers.WebSocketSessionContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pojo.Complaint;
import pojo.JmsMessage;
import pojo.enums.JmsEnum;
import service.JmsMessageManager;
import service.WebSocketManager;
import utils.JsonParse;
import utils.JsonParseByJackson;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component(value = "myListener")
public class MyJmsListener implements MessageListener {

    @Autowired
    @Qualifier("WebSocketManager")
    private WebSocketManager manager;

//    @Autowired
//    @Qualifier("syncContainer")
//    private WebSocketSessionContainer container;

    public MyJmsListener(){
        System.out.println("MyJmsListener...init....");
    }
    @Autowired
    private JmsMessageManager jmsManager;

    public void onMessage(Message message){
        TextMessage tmsg = (TextMessage)message;
        try {
     //       System.out.println("在listener端发送同步前："+container.size());
            System.out.println("监听器收到的Json数据："+ tmsg.getText());
            JsonParse<JmsMessage> parse = new JsonParseByJackson<>();
            JmsMessage jmsMessage = parse.parseJsonToObject(JmsMessage.class,tmsg.getText());
            jmsManager.getMessageEntity(jmsMessage);
            manager.sendMessage("test data");
         //   System.out.println("在listener端发送同步后："+container.size());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
