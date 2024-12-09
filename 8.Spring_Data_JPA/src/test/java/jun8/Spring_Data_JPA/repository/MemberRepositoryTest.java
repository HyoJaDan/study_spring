package jun8.Spring_Data_JPA.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jun8.Spring_Data_JPA.dto.MemberDto;
import jun8.Spring_Data_JPA.entity.Member;
import jun8.Spring_Data_JPA.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void testMember(){
        Member member = new Member("username");
        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId()).get();

        assertThat(findMember.getId()).isEqualTo(savedMember.getId());
        assertThat(findMember.getUsername()).isEqualTo(savedMember.getUsername());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        //단건 조회 검증
        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        //리스트 조회 검증
        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        //카운트 검증
        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        //삭제 검증
        memberRepository.delete(member1);
        memberRepository.delete(member2);
        long deletedCount = memberRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
    @Test
    public void findByUsernameAndAgeGreaterThen(){
        Member m1 = new Member("A",10);
        Member m2 = new Member("A",15);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("A", 12 );

        assertThat(result.get(0).getUsername()).isEqualTo("A");
        assertThat(result.get(0).getAge()).isEqualTo(15);
        assertThat(result.size()).isEqualTo(1);
    }
    @Test
    public void findUsernameList() {
        Member m1 = new Member("A", 10);
        Member m2 = new Member("A", 15);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<String> usernameList = memberRepository.findUsernameList();
        for( String s : usernameList){
            System.out.println("s =" + s);
        }
    }

    @Test
    public void findMemberDto() {
        Member m1 = new Member("A", 10);
        memberRepository.save(m1);
        
        Team team = new Team("teamA");
        m1.setTeam(team);
        teamRepository.save(team);

        List<MemberDto> memberDto = memberRepository.findMemberDto();
        for (MemberDto dto : memberDto) {
            System.out.println("dto = " + dto);
        }
    }

    @Test
    public void returnType() {
        Member m1 = new Member("A", 10);
        Member m2 = new Member("B", 15);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> listByUsername = memberRepository.findListByUsername("A");
        Member findMember = memberRepository.findMemberByUsername("A");
        System.out.println(findMember);

        Optional<Member> optionalMember = memberRepository.findOptionalByUsername("A");
        System.out.println(optionalMember.get());
    }

    @Test
    public void paging(){
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));
        memberRepository.save(new Member("member6", 10));

        int age=10;

        // 첫 번째 인자 : 페이징을 시작할 숫자 ( JPA는 0부터 시작 )
        // 두 번째 인자 : 한 페이지에 몇개 가져올건지
        // 세 번째 인자 : 정렬 조건
        PageRequest pageRequest = PageRequest.of(0,3, Sort.by(Sort.Direction.DESC, "username"));

        // when
        Slice<Member> page = memberRepository.findByAge(age, pageRequest);

        // then
        // 1. 내부의 데이터를 가져온다.
        List<Member> content = page.getContent();
        for (Member member : content) {
            System.out.println("member = " + member);
        }

        // 2. 총 데이터의 양 ( total query를 안날리기 때문에 없다.
//        long totalElements = page.getTotalElements();
//        System.out.println("totalElements = " + totalElements);

        // 3. 데이터 크기
        System.out.println(content.size());

        // 4. 페이지 번호 가져오기
        System.out.println(page.getNumber());

        // 5. 전체 페이지 개수 출력 ( total query를 안날리기 때문에 없다.
//        System.out.println(page.getTotalPages());

        //6. 첫 번째 페이지인지 반환
        System.out.println(page.isFirst());

        // 7. 다음 페이지가 존재하는지
        System.out.println(page.hasNext());
    }

    @Test
    public void bulkUpdate(){
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));
        memberRepository.save(new Member("member6", 10));

        int resultCount = memberRepository.bulkAgePlus(10);

        assertThat(resultCount).isEqualTo(6);

        List<Member> result = memberRepository.findListByUsername("member1");
        Member member = result.get(0);
        System.out.println("member = " + member);
    }

    @Test
    public void findMemberLazy(){
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        memberRepository.save(new Member("member1", 10,teamA));
        memberRepository.save(new Member("member2", 10,teamA));
        memberRepository.save(new Member("member3", 10,teamB));
        memberRepository.save(new Member("member4", 10,teamB));

        em.flush();
        em.clear();

        //when
        List<Member> all = memberRepository.findAll();
        for (Member member : all) {
            System.out.println("member = " + member);
            System.out.println("member = " + member.getTeam().getName());
        }
    }

    @Test
    public void fetchJoin(){
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        memberRepository.save(new Member("member1", 10,teamA));
        memberRepository.save(new Member("member2", 10,teamA));
        memberRepository.save(new Member("member3", 10,teamB));
        memberRepository.save(new Member("member4", 10,teamB));

        em.flush();
        em.clear();

        //when
        List<Member> all = memberRepository.findAll();
        for (Member member : all) {
            System.out.println("member = " + member);
            System.out.println("member = " + member.getTeam().getName());
        }
    }
}