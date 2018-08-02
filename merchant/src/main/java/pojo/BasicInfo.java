package pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "basic_info")
public class BasicInfo {

    @Id
    @GenericGenerator(strategy="uuid",name="uuid")
    @GeneratedValue(generator="uuid")
    private String id;

    @OneToOne
    @JoinColumn(name="m_id")
    private Merchant merchant;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date openTime;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date closeTime;

    @Column(columnDefinition = "varchar2(255) default 3")
    private String delivery;

    @Column(nullable = false)
    private double deliFee;

    @Column(nullable = false)
    private String picture;

    private String slogan;

    @Column(columnDefinition = "varchar2(255) default 0")
    private String status;

    private String comments;


}
