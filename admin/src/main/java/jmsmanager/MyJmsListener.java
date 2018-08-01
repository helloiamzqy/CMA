package jmsmanager;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component(value = "myListener")
public class MyJmsListener implements MessageListener {

    public void onMessage(Message message) {
        TextMessage tmsg = (TextMessage)message;
        try {
            System.out.println("my listener receive:" + tmsg.getText());
        }catch (JMSException e){
            e.printStackTrace();
        }
    }
}
