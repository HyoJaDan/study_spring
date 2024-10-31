# Spring & JPA Advanced Study

김영한 강의의 스프링 및 JPA 학습 프로젝트에 대한 정리입니다. 본 리포지토리는 스프링의 핵심 원리, Spring MVC, JPA, ORM을 이해하고, 실전 예제를 통해 심화 학습을 돕습니다. 각 항목은 강의 내용을 바탕으로 실습하면서 주요 개념과 코드 구현 방식을 설명합니다.

추가 참고 자료 및 강의 요약은 [여기](https://secret-pyrite-78d.notion.site/418aba1de86e42a8b6200173d6018e6f?v=1d33ca0cdb5a4dc5bdc6ee61a3731d25)에서 확인할 수 있습니다.

---

## 목차
1. [Spring 핵심 원리](#1-spring-핵심-원리)
2. [Spring MVC](#2-spring-mvc)
3. [Spring MVC 2](#3-spring-mvc-2)
4. [Spring MVC 3](#4-spring-mvc-3)
5. [JPA for ORM](#5-jpa-for-orm)
6. [Spring Boot와 JPA 활용](#6-spring-boot와-jpa-활용)

---

## 1. Spring 핵심 원리

**개요**  
스프링의 IoC(Inversion of Control)와 DI(Dependency Injection)를 기반으로 한 객체 지향 설계를 이해합니다. 강의에서는 의존성 주입, 싱글톤 패턴, 컴포넌트 스캔, 자동 주입, 빈 생명주기 콜백, 빈 스코프 등의 개념을 다룹니다.

**주요 학습 내용**
- **객체 지향 설계**: 스프링이 객체 간 의존성을 주입하며, IoC를 통해 제어권을 컨테이너에 넘기는 방식.
- **DI 방식**: 생성자 주입을 통해 모듈 간 결합도를 낮추고, 테스트 용이성을 높이는 방법.
- **싱글톤 컨테이너**: 스프링의 빈 관리 방식으로 객체를 하나만 생성해 관리하는 패턴 이해.
- **컴포넌트 스캔**: 어노테이션을 통해 자동으로 빈을 탐색하고 등록.
- **빈 생명주기 콜백과 스코프**: 빈이 생성되고 소멸될 때의 동작 정의.

**구성 파일**
- `AppConfig.java`: 스프링 컨테이너의 빈을 설정하고 의존성 주입을 관리.
- `OrderService` 및 `MemberService`: DI와 IoC를 적용한 서비스 계층.

---

## 2. Spring MVC

**개요**  
Spring MVC의 기본 원리와 동작 순서를 학습하여 웹 애플리케이션의 요청-응답 흐름을 이해합니다. DispatcherServlet의 역할, 서블릿을 이용한 데이터 전달 방식 및 컨트롤러와 뷰, 모델의 관계를 다룹니다.

**주요 학습 내용**
- **MVC 동작 순서**: DispatcherServlet, HandlerMapping, HandlerAdapter의 역할과 순서.
- **서블릿 설정**: `@WebServlet`과 `HttpServletRequest`, `HttpServletResponse`를 통해 백엔드와 프론트엔드의 통신을 처리.
- **HTTP 요청과 응답**: 클라이언트와 서버 간 데이터 전달 방식으로, `@RequestParam`과 `@ModelAttribute` 사용법.

**구성 파일**
- `HomeController.java`: 요청을 처리하고 뷰로 데이터를 전달하는 컨트롤러.
- `home.jsp`: 전달받은 데이터를 사용자에게 보여주는 뷰 템플릿.

---

## 3. Spring MVC 2

**개요**  
Spring MVC의 고급 기능을 이해하며 예외 처리, 필터, 파일 업로드, 다형성을 활용한 HandlerAdapter 구성 등 실제 프로젝트에서 자주 쓰이는 기능을 구현합니다.

**주요 학습 내용**
- **인터셉터와 필터**: 요청 처리 전후 로직을 추가하여 요청 관리를 최적화.
- **파일 업로드**: `MultipartFile`을 활용한 파일 업로드 기능.
- **예외 처리**: 전역 예외 처리 설정으로 오류 발생 시 적절한 응답을 생성.

**구성 파일**
- `WebConfig.java`: 예외 처리, 인터셉터, 필터 설정을 위한 설정 파일.
- `FileUploadController.java`: 파일 업로드 요청을 처리하는 컨트롤러.

---

## 4. Spring MVC 3 - Item Service

**개요**  
Spring MVC로 아이템 관리 시스템을 구현하여 CRUD 기능을 연습합니다. 이 과정에서 Spring Form 태그와 데이터 유효성 검증을 통해 사용자 입력을 관리합니다.

**주요 학습 내용**
- **CRUD 기능**: 아이템의 생성, 조회, 수정, 삭제 기능.
- **폼 데이터 처리**: Spring Form 태그를 사용한 입력 데이터 유효성 검증.
- **JPA와의 연동**: DB와 직접 연동하여 데이터를 조회하고 관리하는 방법.

**구성 파일**
- `ItemController.java`: CRUD 기능을 구현하는 컨트롤러.
- `item-form.jsp`, `item-list.jsp`: 아이템 관리 뷰 파일.

---

## 5. JPA for ORM

**개요**  
JPA를 통해 객체와 관계형 데이터베이스를 매핑하는 기본 원리와 기술을 이해합니다. 이 과정에서 엔티티 매핑, 영속성 컨텍스트 관리, JPQL, 연관관계 매핑 등을 학습합니다.

**주요 학습 내용**
- **엔티티 매핑**: 객체와 테이블을 매핑하는 기본 설정.
- **JPQL 사용**: 데이터베이스 검색을 위한 쿼리 작성법.
- **연관관계 매핑**: 다대일, 일대다, 일대일, 다대다 관계 설정.

**구성 파일**
- `Member.java`, `Order.java`: 엔티티 클래스와 매핑 설정.
- `MemberRepository.java`: JPQL을 활용한 데이터 조회 메서드.

---

## 6. Spring Boot와 JPA 활용 - jpashop

**개요**  
Spring Boot와 JPA를 활용한 실전 프로젝트 예제로, 상거래 애플리케이션(jpashop)을 통해 스프링과 JPA의 연동을 학습합니다. API 개발 및 성능 최적화를 위해 다양한 관계 매핑과 JPA의 고급 기능을 활용합니다.

**주요 학습 내용**
- **Spring Boot 초기 설정**: 설정을 자동화하여 개발을 빠르게 시작.
- **JPA를 통한 데이터 관리**: 복잡한 비즈니스 로직을 구현하고 다양한 관계 매핑을 설정.
- **REST API 개발**: CRUD 기능을 API로 제공하며 클라이언트와의 상호작용 학습.

**구성 파일**
- `JpashopApplication.java`: Spring Boot 애플리케이션 진입점.
- `OrderService.java`, `MemberService.java`: 주요 비즈니스 로직을 처리하는 서비스 계층.
- `OrderController.java`, `MemberController.java`: API 요청을 처리하는 컨트롤러.

---