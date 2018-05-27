package me.socuri.modules.test;

import me.socuri.modules.config.type.DataSource;
import me.socuri.modules.config.type.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {


    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestMapper testMapper;


    @Transactional
    @DataSource(DataSourceType.MASTER)
    public Test save1(Test test){

       return testRepository.save(test);
    }

    @Transactional
    @DataSource(DataSourceType.SLAVE)
    public Test save2(Test test){

        return testRepository.save(test);
    }

    @DataSource(DataSourceType.MYPRIMARY)
    public Test masterFindByIdx(Long idx){
        return testMapper.findByIdx(idx);
    }
    @DataSource(DataSourceType.MYSECONDARY)
    public Test slaveFindByIdx(Long idx){
        return testMapper.findByIdx(idx);
    }

    @Transactional
    @DataSource(DataSourceType.MASTER)
    public Test mybatisSave(Test test){
         Integer rtn=testMapper.save(test);
         return test;
    }

}
