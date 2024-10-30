package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)// Junit 실행할때 spring이랑 같이 실행한다는 뜻
@SpringBootTest // 스프링 부트를 띄우고 싶으면 넣어야 한다.
@Transactional // 끝나면 모두 rollback을 해준다. 테스트에선 넣어야한다.
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;
    @Test
    //@Rollback(false) 이걸 하면 데이터베이스에 데이터가 업데이트 된다.
    public void 회원가입() throws Exception {
        //given <- 이렇게 주어졌을때
        Member member = new Member();
        member.setName("Jun");

        //when <- 이걸 실행하면
        Long savedId = memberService.join(member);

        //then <- 이렇게 되야돼
        //em.flush(); // 있어도 되고 없어도 된다. 있으면 데이터베이스에 데이터를 날리게 된다. 테스트는 반복해야하기때문에 데이터가 남으면 안돼서 있으면 안된다.
        assertEquals(member,memberRepository.findOne(savedId));
    }
    //2. 여기서 이렇게 설정할수 있다.
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외()throws Exception {
        //given
        Member member1=new Member();
        member1.setName("kim");

        Member member2=new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);

        // 1. 이 부분을
        /*try{
            memberService.join(member2);
        } catch (IllegalStateException e){
            return;
        }*/
        memberService.join(member2);
        //then
        fail("예외가 발생해야 한다.");
    }
}