package pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author JohnGao
 * @date 8/1/2018 8:08 PM
 */
@Entity
@Table(name="manager")
public class Manager {
    @Id
    @GenericGenerator(strategy="uuid",name="uuid")
    @GeneratedValue(generator="uuid")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    public Manager(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Manager() {
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
