package hello3.hello.spring.repository;

import hello3.hello.spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SpringDataJpaMemberRespository extends JpaRepository<Member,Long>,MemberRepository {
}
