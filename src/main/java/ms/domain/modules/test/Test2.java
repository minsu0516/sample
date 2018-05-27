package ms.domain.modules.test;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Entity
public class Test2 {


    @EmbeddedId
    private TestPK testPK;

    private Integer age;


}
