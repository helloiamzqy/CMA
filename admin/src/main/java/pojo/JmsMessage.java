package pojo;


import pojo.enums.JmsEnum;

/**
 * @author dunn
 */
public class JmsMessage {
    private JmsEnum jmxTpEnum;

    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void setJmxTpEnum(JmsEnum jmxTpEnum) {
        this.jmxTpEnum = jmxTpEnum;
    }

    public JmsEnum getJmxTpEnum() {
        return jmxTpEnum;
    }

    @Override
    public String toString() {
        return "JmsMessage{" +
                "jmxTpEnum=" + jmxTpEnum +
                ", object=" + object +
                '}';
    }
}
