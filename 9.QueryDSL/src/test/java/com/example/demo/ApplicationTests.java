package com.example.demo;

import com.example.demo.entity.Hello;
import com.example.demo.entity.QHello;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class QuerydslApplicationTests {
	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {
		Hello hello = new Hello();
		em.persist(hello);
		JPAQueryFactory query = new JPAQueryFactory(em);

		//Querydsl Q타입 동작 확인
		QHello qHello = QHello.hello;
		Hello result = query
				.selectFrom(qHello)
				.fetchOne();
		Assertions.assertThat(result).isEqualTo(hello);

		//lombok 동작 확인 (hello.getId())
		Assertions.assertThat(result.getId()).isEqualTo(hello.getId());
	}
}