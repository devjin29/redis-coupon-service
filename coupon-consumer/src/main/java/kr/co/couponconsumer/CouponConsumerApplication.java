package kr.co.couponconsumer;

import kr.co.couponcore.CouponCoreConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(CouponCoreConfiguration.class)
@SpringBootApplication
public class CouponConsumerApplication {

    public static void main(String[] args) {
        //멀티모듈설정
        System.setProperty("spring.config.name", "application-core,application-consumer");
        SpringApplication.run(CouponConsumerApplication.class, args);
    }

}
