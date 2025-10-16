# Spring Redis Coupon Service
Redis를 활용한 쿠폰 발급/관리 시스템 프로젝트

## Main Feature
# 쿠폰 발급 검증
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
