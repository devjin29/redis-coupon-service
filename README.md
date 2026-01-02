# Spring Redis Coupon Service
Redis를 활용한 쿠폰 발급/관리 시스템 프로젝트

## 1. 프로젝트 배경

대규모 프로모션 이벤트(선착순 쿠폰 이벤트)에서는  
짧은 시간에 대량의 요청이 몰리며,  
쿠폰 발급 로직이 서비스 전체 성능과 안정성에 직접적인 영향을 줍니다.

이전 실무 경험에서 특정 시간대에 API 호출이 집중되며  
동기 방식 처리로 인해 응답 지연이 발생하는 문제를 경험했습니다.  

이를 해결하기 위해 비동기 처리(@Async)를 도입하며  
“요청 수용과 실제 처리 로직을 분리하는 구조”의 필요성을 체감했습니다.


이러한 경험을 바탕으로,  
**대량 트래픽 환경에서의 동시성 문제를 구조적으로 해결하는 방법**을 정리해보고자  
Redis 기반 선착순 쿠폰 발급 시스템을 설계·구현하였습니다.

## Main Feature
## 쿠폰 발급 검증
- 발급 기한
- 발급 수량
- 중복 발급

## 쿠폰 발급 수량 관리
Redis Set기반 재고 관리

## 비동기 쿠폰 발급
Redis List (발급 Queue)

## 기술 스택
Server
Java 17, Spring Boot 3.1, Spring Mvc, JPA, QueryDsl

Database
Mysql, Redis, H2

Etc
Locust, Gradle, Docker

## 주요 기능
- 쿠폰 발급 및 관리
- Redis를 활용한 동시성 제어
- API 기반 서비스
