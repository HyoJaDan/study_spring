package hello3.hello.spring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// JPA 는 객채랑 ORM(Object, Relational, Map 오브젝트랑 관계를 매핑한다.)이란 뜻
// 1. 이걸하면 이제 JPA 가 관리하는 Entity 라고 안다.
@Entity
public class Member {
    // 2. id : pk다. DB 에서 값을 생성해주고 있다.
    // 3. GeneratedValue : 쿼리에서 우리가 값을 생성하면 id를 자동으로 매칭해줬는데 이를 identity라고 한다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
