package sungho1.springCorePrinciple.member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
