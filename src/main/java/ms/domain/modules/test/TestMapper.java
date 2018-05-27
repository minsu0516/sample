package ms.domain.modules.test;

import org.apache.ibatis.annotations.*;

@Mapper
public interface TestMapper{

    @Select("select * from test where idx = #{idx}")
    Test findByIdx(@Param("idx") Long idx);


    @Insert("insert into test (name) values (#{name})")
    @Options(useGeneratedKeys = true,keyProperty = "idx")
    Integer save(Test test);

}
