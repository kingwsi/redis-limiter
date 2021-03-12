package com.example.limiter;

import java.lang.annotation.*;

/**
 * description: Limiter <br>
 * date: 2021/3/12 13:22 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Limiter {
    LimiterType type() default LimiterType.PATH;

    // 每秒生产令牌数量
    int replenishRate() default 1;

    // 桶容量（并发数量）
    int burstCapacity() default 5;

    // 每次请求消耗令牌数量
    int requestedTokens() default 1;
}
