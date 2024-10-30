package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//jpa의 모든 데이터 변경이나 로직들은 트랜젝션 안에서 실행되야 하기 때문에 추가한다. 이래야지 Lazy 같은게 된다.
//class 단위로 설정했으니, 내부의 모든 public 함수들은 적용된다.
@Transactional(readOnly = true) //읽기 전용이니까 DB가 리소스를 많이 쓰지 않고 읽기만 가능.
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 가입
    //여기는 읽기 전용이 아니니까 이렇게 선언하면 우선권을 가져 readOnly를 안가진다.
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //중복 회원 로직
    private void validateDuplicateMember(Member member){
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public  Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    /**
     *
     * @param id
     * @param name
     */
    @Transactional
    public void update(Long id, String name) {
        //JPA가 영속성 컨텍스트나 DB에서 가져오고, 영속성 컨택스트에 올려준다.
        Member member = memberRepository.findOne(id);
        // 새로운 값을 반환하면 AOP가 반환하면서 Transactional annotation에 의해서 transaction commit이 된다.
        // 이때 jpa가 flush하고 update 쿼리를 commit 한다.
        member.setName(name);
    }
}
