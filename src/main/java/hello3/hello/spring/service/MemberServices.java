package hello3.hello.spring.service;

import hello3.hello.spring.domain.Member;
import hello3.hello.spring.repository.MemberRepository;
import hello3.hello.spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberServices {
    private final MemberRepository memberRepository;


    public MemberServices(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    // 회원 가입
    public Long join(Member member){
        //같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member);//중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m->{
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(long memberId){
        return memberRepository.findById(memberId);
    }
}
