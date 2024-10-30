package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

//컴포넌트 스캐으로 spring bean에 의해 component를 등록한다.
@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    //영속성 컨텍스트에 member를 넣고, db에 insert query가 들어가는거다.
    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    public List<Member> findAll(){
        List<Member> result=em.createQuery("select m from Member m", Member.class).getResultList();

        return result;
    }

    //이름에 의해서 멤버를 찾는다.
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
