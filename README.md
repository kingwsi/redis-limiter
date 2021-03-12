### Redis实现的令牌桶算法限流

#### 说明
* 内容参考Spring Gateway的`RedisRateLimiter` 以及 `request_rate_limiter.lua`脚本
* 使用Redis + Lua自定义脚本的方式实现一个令牌桶算法，对指定接口请求速率的控制
* 封装了基于注解实现的令牌桶限流方法，可针对接口/用户/IP 速率限制
#### 使用场景
* Gateway本身做了限流配置，但是某个接口要实现更细粒度的速率控制
* 未使用Gateway,或是不想在网关做控制
* ...

#### 测试
* 运行项目 mvn spring-boot:run
* 访问 `http://localhost:8080/limiterByPath`、`http://localhost:8080/limiterByUserId`、`http://localhost:8080/limiterByIp`
* 可多次刷新页面查看效果