package kr.co.couponcore.service;

import kr.co.couponcore.repository.redis.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AsyncCouponIssueServiceV1 {

    private final RedisRepository redisRepository;

    //couponIssueService의 issue메서드와 동일하게 인터페이스 가져와서 사용
    //쿠폰 발급검증 & 발급처리
    public void issue(long couponId, long userId) {
        //내부적으로 redis 호출
        // 1. 유저의 요청을 sorted set 적재
        String key = "issue.request.sorted_set.couponId=%s".formatted(couponId);
        //zAdd(String key,String value,double Score)
        redisRepository.zAdd(key,String.valueOf(userId), System.currentTimeMillis());
    }
}
