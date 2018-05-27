package ms.domain.modules.test;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Columns;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@Embeddable
public class TestPK implements Serializable{

    @Column(name="id",nullable=false)
    private Long id;

    @Column(name="name",nullable=false)
    private String name;
    public TestPK(){}
    public TestPK(Long id,String name){
        this.id=id;
        this.name=name;
    }
}
