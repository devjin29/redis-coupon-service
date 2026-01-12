package kr.co.couponcore.repository.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RedisRepository {

    //RedisTemplate빈주입 → ZADD 호출
    private final RedisTemplate<String, String> redisTemplate;

    public Boolean zAdd(String key, String value, double score) {
        //같은 값 요청 들어올경우, 저장 방지(ZADD NX옵션)
        return redisTemplate.opsForZSet().addIfAbsent(key, value, score);
    }
}
