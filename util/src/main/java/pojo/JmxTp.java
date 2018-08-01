package pojo;

import pojo.penum.JmxTpEnum;

/**
 * @author dunn
 */
public class JmxTp {
    private Enum<JmxTpEnum> jmxTpEnum;

    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Enum<JmxTpEnum> getJmxTpEnum() {
        return jmxTpEnum;
    }

    public void setJmxTpEnum(Enum<JmxTpEnum> jmxTpEnum) {
        this.jmxTpEnum = jmxTpEnum;
    }

}
