package ms.domain.modules.test.member;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer age;


    //@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    //@JoinColumn(name="member_id")
    @OneToMany(cascade=CascadeType.ALL, mappedBy="member")
    private Collection<Phone> phone;

    public void addPhone(Phone p){
        if(phone ==null) {
            phone =new ArrayList<Phone>();
        }
        phone.add(p);

    }



}
