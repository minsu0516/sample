package me.socuri.modules.test;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test2TestUnit {

    @Autowired private Test2Repository test2Repository;

    @Test
    public void test2Save(){
        TestPK testPK=new TestPK(1L,"name");

        Test2 test2=new Test2();
        test2.setTestPK(testPK);
        test2.setAge(10);
        Test2 testObj=test2Repository.save(test2);

        Assert.assertEquals(test2,testObj);


    }

}
