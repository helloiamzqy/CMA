package jmsmananger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

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
}
