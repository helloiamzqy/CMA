package pojo;

/**
 * @author:HUGO
 * @description:
 * @date:Create in 4:01 PM 8/3/2018
 * @modified By:
 */
public class CartItem {
    String id;
    int num;

    @Override
    public String toString() {
        return "CartItem{" +
                "id='" + id + '\'' +
                ", num=" + num +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
