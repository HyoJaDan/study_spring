package hello3.hello.spring.service;

import hello3.hello.spring.domain.Member;
import hello3.hello.spring.repository.MemberRepository;
import hello3.hello.spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
// 0. 이걸 넣으면 이전(MemberServiseTest) 에는 Java 코드만 있었지만, 앞으로는 테스트코드에서 Spring 이 동작한다.
@SpringBootTest
// 1. Transaction : 거래 : 이전에 BeforeEach, AfterEach 로 테스트 코드 시작과 끝에 함수를 초기화 하고 삭제했지만, 이걸 Spring에서 대신 해준다.
@Transactional
public class MemberServiceIntegrationTest {
    // 2. 보통은 테스트 코드를 할때 새로 사용자를 입력받지 않는다. 그래서 AutoWired로 그전에 만들어놨던 것을 가져온다.
    @Autowired MemberServices memberServices;
    @Autowired
    MemberRepository memberRepository;
    // 3. 이전에 BeforeEach, AfterEach에 있는 코드를 모두 지운 것을 확인할수 있다.
    @Test
    void 회원가입() {
        Member member=new Member();
        member.setName("hello");
        Long saveId=memberServices.join(member);
        Member findMember = memberServices.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test void 중복_회원_예외(){
        Member member1=new Member();
        member1.setName("spring");

        Member member2=new Member();
        member2.setName("spring");
        memberServices.join(member1);
        assertThrows(IllegalStateException.class, () -> memberServices.join(member2));
    }

}