package hello3.hello.spring.service;

import hello3.hello.spring.domain.Member;
import hello3.hello.spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServicesTest {

    MemberServices memberServices;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository=new MemoryMemberRepository();
        memberServices=new MemberServices(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    //테스트 이름은 과감하게 한국어로 해도 된다.
    @Test
    void 회원가입() {
        // given-when-then 문법으로 하면 테스트코드를 작성하기 편하다.
        // given :이런 상황이 주어졌는데
        Member member=new Member();
        member.setName("hello");

        // when : 뭔가가 실행 됐을때
        Long saveId=memberServices.join(member);

        // then : 이런게 되야돼

        Member findMember = memberServices.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test void 중복_회원_예외(){
        //given
        Member member1=new Member();
        member1.setName("spring");

        Member member2=new Member();
        member2.setName("spring");

        //when
        memberServices.join(member1);
        assertThrows(IllegalStateException.class, () -> memberServices.join(member2));
        //then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}