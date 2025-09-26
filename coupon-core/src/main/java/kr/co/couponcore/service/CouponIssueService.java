package kr.co.couponcore.service;

import jakarta.persistence.Table;
import kr.co.couponcore.exception.CouponIssueException;
import kr.co.couponcore.exception.ErrorCode;
import kr.co.couponcore.model.Coupon;
import kr.co.couponcore.model.CouponIssue;
import kr.co.couponcore.repository.mysql.CouponIssueJpaRepository;
import kr.co.couponcore.repository.mysql.CouponIssueRepository;
import kr.co.couponcore.repository.mysql.CouponJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static kr.co.couponcore.exception.ErrorCode.COUPON_NOT_EXIST;
import static kr.co.couponcore.exception.ErrorCode.DUPLICATED_COUPON_ISSUE;

@RequiredArgsConstructor
@Service
public class CouponIssueService {

    // Repository 빈을 주입받아 사용
    private final CouponJpaRepository couponJpaRepository;
    private final CouponIssueJpaRepository couponIssueJpaRepository;
    private final CouponIssueRepository couponIssueRepository;

    //쿠폰 발급검증 & 발급처리
    @Transactional
    public void issue(long couponId, long userId) {
        //coupon을 찾고
        Coupon coupon = findCouponWithLock(couponId);
        //발급수량,기한 검증
        coupon.issue();
        //쿠폰이슈 테이블에 레코드를 저장 - 어떤 user가 어떤 쿠폰 발급받앗나
        saveCouponIssue(couponId,userId);

    }

    //couponID를 받아와 coupon 엔티티 조회
    @Transactional(readOnly = true)
    public Coupon findCoupon(long couponId){
        return couponJpaRepository.findById(couponId).orElseThrow(()->{
            throw new CouponIssueException(COUPON_NOT_EXIST,"쿠폰 정책이 존재하지 않습니다. %s".formatted(couponId));
        });
    }

    @Transactional
    public Coupon findCouponWithLock(long couponId) {
        return couponJpaRepository.findCouponWithLock(couponId).orElseThrow(() -> {
            throw new CouponIssueException(COUPON_NOT_EXIST, "쿠폰 정책이 존재하지 않습니다. %s".formatted(couponId));
        });
    }

    // 쿠폰발급 레코드 저장
    @Transactional
    public CouponIssue saveCouponIssue(long couponId, long userId){
        //검증
        checkAlreadyIssueance(couponId, userId);
        CouponIssue issue = CouponIssue.builder()
                .couponId(couponId)
                .userId(userId)
                .build();
        return couponIssueJpaRepository.save(issue);
    }

    // 요청한 user에게 이미 쿠폰이 발급이 되었는지 체크
    private void checkAlreadyIssueance(long couponId, long userId) {
       CouponIssue issue =  couponIssueRepository.findFirstCouponIssue(couponId, userId);
        if(issue != null){
            throw new CouponIssueException(DUPLICATED_COUPON_ISSUE, "이미 발급된 쿠폰입니다. user_id: %s, coupon_id: %s".formatted(userId, couponId));
        }
    }

}
