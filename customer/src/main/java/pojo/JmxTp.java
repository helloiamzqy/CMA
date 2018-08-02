package pojo;


import pojo.enums.JmsTpEnum;

/**
 * @author dunn
 */
public class JmxTp {
    private Enum<JmsTpEnum> jmxTpEnum;

    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Enum<JmsTpEnum> getJmxTpEnum() {
        return jmxTpEnum;
    }

    public void setJmxTpEnum(Enum<JmsTpEnum> jmxTpEnum) {
        this.jmxTpEnum = jmxTpEnum;
    }

}
