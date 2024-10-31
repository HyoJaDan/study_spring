# Spring and JPA Advanced Study

김영한 강의에서 다룬 스프링과 JPA의 개념과 실전 예제를 바탕으로 프로젝트별 학습 내용을 정리합니다. 이 `README`는 각 프로젝트의 학습 목표와 구성 요소를 명확히 설명하여, 학습자들이 스프링 프레임워크와 JPA를 깊이 이해할 수 있도록 합니다.


더 많은 학습 자료와 참고 자료는 [여기](https://secret-pyrite-78d.notion.site/18f82950fc9c4979b5a53a4bc15b32e7?v=62f5acc40e684af3bef27e709d042459)에서 확인할 수 있습니다.

---

## 목차
1. [Spring Core Principle](#1-spring-core-principle)
2. [Spring MVC](#2-spring-mvc)
3. [Spring MVC 2](#3-spring-mvc-2)
4. [Spring MVC 3 - Item Service](#4-spring-mvc-3---item-service)
5. [JPA for ORM](#5-jpa-for-orm)
6. [Spring Boot and JPA Utilization - jpashop](#6-spring-boot-and-jpa-utilization---jpashop)

---

## 1. Spring Core Principle

**개요**  
스프링의 핵심 원칙을 학습하여, IoC(Inversion of Control)와 DI(Dependency Injection) 등의 개념을 이해하고 스프링 컨테이너가 객체 관리에 어떻게 기여하는지 살펴봅니다.

**주요 학습 내용**
- **IoC와 DI의 이해**: 스프링이 객체의 생성과 관리를 어떻게 제어하는지 학습.
- **싱글톤 패턴**: 스프링 컨테이너에서 빈 관리와 싱글톤 패턴의 적용 방식 이해.
- **DI 컨테이너 사용법**: XML 및 Java 기반 설정을 통해 의존성 주입 컨테이너를 설정하고 관리.
  
**구성 파일**
- `AppConfig.java`: 애플리케이션 전반의 빈 설정 및 의존성 주입을 담당.
- `MemberService`와 `OrderService`: 서비스 인터페이스와 구현체를 통해 DI와 IoC 적용.

---

## 2. Spring MVC

**개요**  
Spring MVC의 기본 개념과 동작 원리를 학습하여, 웹 애플리케이션의 요청-응답 흐름을 이해하고 컨트롤러, 뷰, 모델 간의 상호작용을 학습합니다.

**주요 학습 내용**
- **DispatcherServlet 구조**: Spring MVC의 핵심인 DispatcherServlet의 역할 이해.
- **컨트롤러와 뷰**: 요청을 받아 데이터를 처리하고, 이를 사용자에게 전달하는 방법 학습.
- **모델**: 비즈니스 로직과 데이터를 뷰에 전달하는 역할 학습.

**구성 파일**
- `HomeController.java`: 기본 페이지와 각 요청을 처리하는 컨트롤러 구현.
- `home.jsp`: 모델 데이터를 활용하여 사용자에게 정보를 보여주는 뷰 템플릿.

---

## 3. Spring MVC 2

**개요**  
Spring MVC의 심화 과정으로, 예외 처리, 인증 및 권한 부여, 파일 업로드 등 다양한 웹 애플리케이션의 요구사항을 해결하는 방법을 학습합니다.

**주요 학습 내용**
- **인터셉터와 필터**: 요청 전/후에 실행되는 인터셉터와 필터 설정을 통한 요청 관리.
- **예외 처리**: 전역 예외 처리 설정으로 오류 발생 시 사용자에게 적절한 메시지를 전달.
- **파일 업로드 처리**: MultipartFile을 사용하여 파일 업로드를 구현하는 방법 학습.

**구성 파일**
- `WebConfig.java`: 인터셉터, 필터, 예외 처리 등 전반적인 웹 설정 파일.
- `ErrorController.java`: 전역 예외 처리를 위한 컨트롤러.

---

## 4. Spring MVC 3 - Item Service

**개요**  
Spring MVC를 사용하여 아이템 관리 서비스(CRUD)를 구현하며 실전 예제를 통해 MVC 패턴의 전체 흐름을 경험합니다.

**주요 학습 내용**
- **CRUD 기능 구현**: 아이템 생성, 조회, 수정, 삭제 기능을 구현하며 MVC 흐름 이해.
- **Form 태그 활용**: Spring Form을 사용하여 데이터를 입력하고 유효성 검증.
- **데이터베이스 연동**: JDBC 또는 JPA를 사용하여 데이터베이스와 연동하는 방법 학습.

**구성 파일**
- `ItemController.java`: 아이템의 CRUD 기능을 처리하는 컨트롤러.
- `item-form.jsp`, `item-list.jsp`: 아이템 관리 기능을 위한 뷰 템플릿.

---

## 5. JPA for ORM

**개요**  
JPA의 기본 개념과 ORM(Object-Relational Mapping)을 통해 객체 지향 프로그래밍과 관계형 데이터베이스 간의 매핑을 학습합니다.

**주요 학습 내용**
- **엔티티 매핑**: 데이터베이스 테이블과 객체 간의 매핑 설정.
- **JPQL 사용**: JPA 쿼리 언어를 사용해 데이터를 효율적으로 검색.
- **영속성 컨텍스트**: 데이터 변경 사항을 자동으로 반영하는 영속성 관리.

**구성 파일**
- `Member.java`, `Order.java`: JPA 엔티티 클래스와 매핑 설정.
- `MemberRepository.java`: JPQL을 활용한 데이터 조회 메서드 구현.

---

## 6. Spring Boot and JPA Utilization - jpashop

**개요**  
Spring Boot와 JPA를 활용한 실전 프로젝트 예제로, 상거래 애플리케이션(jpashop)을 구현하며 실무에서의 스프링과 JPA 활용 방법을 학습합니다.

**주요 학습 내용**
- **Spring Boot 설정**: 빠른 개발을 위한 Spring Boot 설정 및 활용법.
- **JPA 활용**: 복잡한 비즈니스 로직을 처리하며, 다양한 관계 매핑을 사용한 데이터베이스 설계.
- **API 개발**: REST API를 통해 데이터를 주고받는 방법 학습.

**구성 파일**
- `JpashopApplication.java`: 스프링 부트 애플리케이션 시작점.
- `OrderService.java`, `MemberService.java`: 주요 비즈니스 로직을 처리하는 서비스 계층.
- `OrderController.java`, `MemberController.java`: API 요청을 처리하는 컨트롤러.

---
