package pojo;


import pojo.penum.JmsTpEnum;

/**
 * @author dunn
 */
public class JmxTp {
    private JmsTpEnum jmxTpEnum;

    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void setJmxTpEnum(JmsTpEnum jmxTpEnum) {
        this.jmxTpEnum = jmxTpEnum;
    }

    public JmsTpEnum getJmxTpEnum() {
        return jmxTpEnum;
    }
}
