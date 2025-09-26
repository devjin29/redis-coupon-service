package kr.co.couponapi.service;

import kr.co.couponapi.controller.dto.CouponIssueRequestDto;
import kr.co.couponcore.component.DistributeLockExecutor;
import kr.co.couponcore.service.CouponIssueService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CouponIssueRequestService {

    // couponcore의 CouponIssueService를 받아와서 사용
    private final CouponIssueService couponIssueService;
//    private final AsyncCouponIssueServiceV1 asyncCouponIssueServiceV1;
//    private final AsyncCouponIssueServiceV2 asyncCouponIssueServiceV2;
//    private final DistributeLockExecutor distributeLockExecutor;
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final DistributeLockExecutor distributeLockExecutor;

    public void issueRequestV1(CouponIssueRequestDto requestDto) {
        //쿠폰발급 API 동시성이슈 해결
        //Redis Lock
       //distributeLockExecutor.execute("lock_"+requestDto.couponId(),10000,10000,()->{
        couponIssueService.issue(requestDto.couponId(), requestDto.userId());//DTO에서 꺼내서 메서드 호출
        //});
        log.info("쿠폰 발급 완료. couponId: %s, userId: %s".formatted(requestDto.couponId(), requestDto.userId()));

    }
//
//    public void asyncIssueRequestV1(CouponIssueRequestDto requestDto) {
//        asyncCouponIssueServiceV1.issue(requestDto.couponId(), requestDto.userId());
//    }
//
//    public void asyncIssueRequestV2(CouponIssueRequestDto requestDto) {
//        asyncCouponIssueServiceV2.issue(requestDto.couponId(), requestDto.userId());
//    }
}
