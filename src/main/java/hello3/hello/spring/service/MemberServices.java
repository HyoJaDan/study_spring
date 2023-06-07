package hello3.hello.spring.service;

import hello3.hello.spring.repository.MemberRepository;
import hello3.hello.spring.repository.MemoryMemberRepository;

public class MemberServices {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public Long join(Member member){
        memberRepository.save(member);
        return member.getId();
    }
}
