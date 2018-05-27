package ms.domain.modules.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;
    @Autowired
    private TestRepository testRepository;


    @RequestMapping(value = "/test")
    public Test create(Test test){
        return testService.save1(test);
    }


    @RequestMapping(value = "/test2")
    public Test create2(Test test){
        return testService.save2(test);
    }

    @RequestMapping(value = "/test/m/{idx}")
    public Test masterFindByIdx(@PathVariable("idx")  Long idx){
        return testService.masterFindByIdx(idx);
    }

    @RequestMapping(value = "/test/s/{idx}")
    public Test slaveFindByIdx(@PathVariable("idx")  Long idx){

        return testService.slaveFindByIdx(idx);
    }


    @RequestMapping(value = "/test/my")
    public Test mybatisSaveTest(Test test){
        return testService.mybatisSave(test);
    }
}
