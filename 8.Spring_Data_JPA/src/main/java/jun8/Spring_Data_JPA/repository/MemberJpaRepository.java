package jun8.Spring_Data_JPA.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jun8.Spring_Data_JPA.entity.Member;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberJpaRepository {
    @PersistenceContext
    private EntityManager em;

    public Member save(Member member){
        em.persist(member);
        return member;
    }

    public void delete(Member member){
        em.remove(member);
    }

    public List<Member> findAll(){
        // List를 반환받을때는 JPQL을 사용해야 한다.
        // JPQL은 테이블을 대상으로 하는게 아니라 덴티티를 대상으로 한다.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public Optional<Member> findById(Long id){
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public long count(){
        return em.createQuery("select count(m) from Member m", Long.class)
                .getSingleResult();
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
    public List<Member> findByUsernameAndAgeGreaterThan(String username, int age){
        return em.createQuery("""
                        SELECT  m
                        FROM    Member m
                        WHERE   m.username = :username
                        AND     m.age > :age
                        """, Member.class)
                .setParameter("username", username)
                .setParameter("age", age)
                .getResultList();
    }

    /**
     * 페이징 쿼리를 해주는 메서드
     */
    // offset : 어디서부터 가져온다.
    // limit : 몇 개를 가져온다.
    public List<Member> findByPage(int age, int offset, int limit){
        return em.createQuery("""
                SELECT  m
                FROM    Member m
                WHERE   m.age = :age
                ORDER BY m.username desc
                """, Member.class)
                .setParameter("age", age)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
    /**
     * 전체 페이지 중 내 페이지가 몇 번째 페이지인지 알려준다.
     */
    public long totalCount(int age){
        return em.createQuery("""
                SELECT  count(m)
                FROM    Member m
                WHERE   m.age = :age
                """,Long.class)
                .setParameter("age", age)
                .getSingleResult();
    }


}

