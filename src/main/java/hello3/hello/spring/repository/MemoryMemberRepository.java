package hello3.hello.spring.repository;

import hello3.hello.spring.domain.Member;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;

import java.util.*;

public class MemoryMemberRepository  implements MemberRepository{
    private static Map<Long, Member> store=new HashMap<>();
    private static long sequence =0L;
    @Override
    public Member save(Member member) {
        //  member 의 id를 정해주고 store 에 저장하고, member 을 반환
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // store.get 으로 id 로 값을 반환. 하지만 반환값이 없는 경우(null 인 경우)때문에 Optional.ofNullable 을 붙였다.
        return Optional.ofNullable(store.get(id));
    }
 
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member->member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        // store 은 map 형태이지만 이 함수의 반환값은 List를 사용하는 것을 볼수 있다. 현업에서 List 를 많이 사용하고, 순환하여 참조할수 있기때문에 사용했다.
        return new ArrayList<>(store.values());
    }


    public void clearStore() {
        store.clear();
    }
}
