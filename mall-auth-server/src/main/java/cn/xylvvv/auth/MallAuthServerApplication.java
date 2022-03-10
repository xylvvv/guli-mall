package cn.xylvvv.auth;

import cn.xylvvv.common.config.MallSessionConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 核心原理：
 * @EnableRedisHttpSession 导入 RedisHttpSessionConfiguration 配置
 * 1、给容器中添加了一个组件 RedisOperationsSessionRepository：Redis操作session，session的增删改查封装类；
 * 2、继承 SpringHttpSessionConfiguration 初始化了一个 SessionRepositoryFilter：session 存储过滤器；每个请求过来都必须经过 Filter 组件；
 *  创建的时候，自动从容器中获取到了 SessionRepository；
 *  SessionRepositoryFilter：
 * ● 将原生的 HttpServletRequest Response 包装成 SessionRepositoryRequestWrapper ResponseWrapper；包装后的对象应用到了后面整个执行链；
 * ● 以后获取 request.getSession(); 都会调用 wrappedRequesr.getSession(); 从SessionRepository获取；
 * 3、装饰者模式
 * 完全模拟了以前session的所有功能（自动续期等，redis中的数据也是有过期时间的）
 */
@EnableRedisHttpSession // 整合redis作为session存储
@ComponentScan(basePackages = { "cn.xylvvv.auth" }, basePackageClasses = { MallSessionConfig.class })
@EnableFeignClients
@SpringBootApplication
public class MallAuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallAuthServerApplication.class, args);
    }
}
