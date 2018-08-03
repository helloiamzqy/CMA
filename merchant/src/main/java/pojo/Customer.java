package pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GenericGenerator(strategy="uuid",name="uuid")
    @GeneratedValue(generator="uuid")
    private String id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    private String phone;

    private String address;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
