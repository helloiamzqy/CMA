package listener;

import org.springframework.stereotype.Component;
import pojo.Complaint;
import utils.JsonParse;
import utils.JsonParseByJackson;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component(value = "myListener")
public class MyJmsListener implements MessageListener {

    public void onMessage(Message message){
        TextMessage tmsg = (TextMessage)message;
        try {
//            tmsg.getText()
            System.out.println("中文");
            System.out.println("my listener receive:" +new String(tmsg.getText().getBytes("iso8859-1"),"UTF-8"));
            JsonParse<Complaint> parse = new JsonParseByJackson<>();
            Object object = parse.parseJsonToObject(Complaint.class,tmsg.getText());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
