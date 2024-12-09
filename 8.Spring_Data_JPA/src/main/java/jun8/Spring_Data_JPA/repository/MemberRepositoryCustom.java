package jun8.Spring_Data_JPA.repository;

import jun8.Spring_Data_JPA.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();
}
