package ms.domain.modules.test.member;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMember {

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    @Test
    public void testSave(){

        Member member=new Member();
        member.setName("test");
        member.setAge(18);

       // member.addPhone(phone);

        Member memberObj=memberRepository.save(member);

        Phone phone=new Phone();
        phone.setNo("010-1111-2222");
        phone.setMember(member);





        List<Member> list=memberRepository.findAll();

        for(Member m : list){
            System.out.print(m.toString());
        }



    }
}
