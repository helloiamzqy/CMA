package pojo;


import pojo.enums.JmsEnum;

/**
 * @author dunn
 */
public class JmsMessage {
    private JmsEnum jmsEnum;

    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public JmsEnum getJmsEnum() {
        return jmsEnum;
    }

    public void setJmsEnum(JmsEnum jmsEnum) {
        this.jmsEnum = jmsEnum;
    }

    @Override
    public String toString() {
        return "JmsMessage{" +
                "jmsEnum=" + jmsEnum +
                ", object=" + object +
                '}';
    }
}
