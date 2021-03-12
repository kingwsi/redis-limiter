package com.example.limiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
@Configuration
@RestController
public class RedisLimiterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisLimiterApplication.class, args);
    }

    @Limiter(type = LimiterType.PATH)
    @GetMapping("/limiterByPath")
    public String limiterByPath() {
        return "SUCCESS";
    }

    @Limiter(type = LimiterType.USER)
    @GetMapping("/limiterByUserId")
    public String limiterByUserId() {
        return "SUCCESS";
    }

    @Limiter(type = LimiterType.IP, replenishRate = 2)
    @GetMapping("/limiterByIp")
    public String limiterByIp() {
        return "SUCCESS";
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisScript redisRequestRateLimiterScript() {
        DefaultRedisScript redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(
                new ClassPathResource("request_rate_limiter.lua")));
        redisScript.setResultType(List.class);
        return redisScript;
    }

}
