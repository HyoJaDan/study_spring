package jun8.Spring_Data_JPA.repository;

import jakarta.persistence.EntityManager;
import jun8.Spring_Data_JPA.entity.Member;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final EntityManager em;

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("""
                SELECT m FROM Member m
                """, Member.class)
                .getResultList();
    }
}
