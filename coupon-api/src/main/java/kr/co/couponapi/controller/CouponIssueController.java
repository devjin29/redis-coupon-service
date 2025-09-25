package kr.co.couponapi.controller;

import kr.co.couponapi.controller.dto.CouponIssueRequestDto;
import kr.co.couponapi.controller.dto.CouponIssueResponseDto;
import kr.co.couponapi.service.CouponIssueRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CouponIssueController {

    private final CouponIssueRequestService couponIssueRequestService;

//    @PostMapping("/v1/issue")
//    public boolean issueV1(@RequestBody CouponIssueRequestDto body) {
//        couponIssueRequestService.issueRequestV1(body);
//        return true;
//    }
//
    @PostMapping("/v1/issue")
    public CouponIssueResponseDto issueV1(@RequestBody CouponIssueRequestDto body) {
        couponIssueRequestService.issueRequestV1(body);
        return new CouponIssueResponseDto(true, null);
    }
//
//    @PostMapping("/v1/issue-async")
//    public CouponIssueResponseDto issueAsyncV1(@RequestBody CouponIssueRequestDto body) {
//        couponIssueRequestService.asyncIssueRequestV1(body);
//        return new CouponIssueResponseDto(true, null);
//    }
//
//    @PostMapping("/v2/issue-async")
//    public CouponIssueResponseDto issueAsyncV2(@RequestBody CouponIssueRequestDto body) {
//        couponIssueRequestService.asyncIssueRequestV2(body);
//        return new CouponIssueResponseDto(true, null);
//    }
}