package jun8.Spring_Data_JPA.repository;

import jun8.Spring_Data_JPA.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

// 첫번째꺼는 타입, 2번쨰 인자는 매핑된 PK
// 이렇게 만들어 놓으면 jpa가 구현체를 넣어버림. 개발자가 할 필요가 없음.
public interface TeamRepository extends JpaRepository<Team, Long> {
}
