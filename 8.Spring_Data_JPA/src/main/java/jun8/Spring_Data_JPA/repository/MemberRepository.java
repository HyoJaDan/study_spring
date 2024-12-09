package jun8.Spring_Data_JPA.repository;

import jun8.Spring_Data_JPA.dto.MemberDto;
import jun8.Spring_Data_JPA.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    @Query("""
            SELECT  m
            FROM    Member m
            WHERE   m.username = :username
            AND     m.age > :age
            """)
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("SELECT m.username from Member m")
    List<String> findUsernameList();

    @Query("""
            SELECT
            new jun8.Spring_Data_JPA.dto.MemberDto(m.id, m.username, t.name)
            FROM    Member m join team t
            """)
    List<MemberDto> findMemberDto();

    @Query("""
            SELECT  m
            FROM    Member m
            WHERE   m.username in :names
            """)
    List<Member> findByNames(@Param("names") Collection<String> names);

    List<Member> findListByUsername(String username); // 컬랙션
    Member findMemberByUsername(String username); //단건
    Optional<Member> findOptionalByUsername(String username); // 단건 optional

    Slice<Member> findByAge(int age, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("""
            UPDATE  Member m
            SET     m.age = m.age +1
            WHERE   m.age >= :age
            """)
    int bulkAgePlus(@Param("age") int age);

    @Query("""
            SELECT  m
            FROM    Member m
                LEFT JOIN FETCH m.team
            """)
    List<Member> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();
}
