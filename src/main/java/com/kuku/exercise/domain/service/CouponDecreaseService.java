package com.kuku.exercise.domain.service;

import com.kuku.exercise.domain.Coupon;
import com.kuku.exercise.domain.repository.CouponRepository;
import com.kuku.exercise.infrastructure.config.DistributedLock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CouponDecreaseService {

    private final CouponRepository couponRepository;

    @Transactional
    public void couponDecrease(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(IllegalArgumentException::new);

        coupon.decrease();
    }

    @DistributedLock(key = "#lockName")
    public void couponDecrease(String lockName, Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(IllegalArgumentException::new);

        coupon.decrease();
    }
}