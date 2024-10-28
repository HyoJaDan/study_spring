package sungho1.springCorePrinciple.member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);
}
