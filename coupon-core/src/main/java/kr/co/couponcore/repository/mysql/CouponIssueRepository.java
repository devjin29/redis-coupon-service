package kr.co.couponcore.repository.mysql;

import com.querydsl.jpa.JPQLQueryFactory;
import kr.co.couponcore.model.CouponIssue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static kr.co.couponcore.model.QCouponIssue.couponIssue;

@RequiredArgsConstructor
@Repository
public class CouponIssueRepository {
    //쿼리dsl 관련 설정
    //쿼리팩토리 가져오기
    private final JPQLQueryFactory queryFactory;

   // 요청한 user에게 이미 쿠폰이 발급이 되었는지 체크
    public CouponIssue findFirstCouponIssue(long couponId, long userId) {
        //return queryFactory.selectFrom(QCouponIssue.couponIssue)
        return queryFactory.selectFrom(couponIssue)
                .where(couponIssue.couponId.eq(couponId))
                .where(couponIssue.userId.eq(userId))
                .fetchFirst();

    }
}
