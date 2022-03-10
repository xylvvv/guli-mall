package cn.xylvvv.gulimall.product;

import cn.xylvvv.common.config.MallSessionConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@ComponentScan(basePackages = {"cn.xylvvv.gulimall.product"}, basePackageClasses = {MallSessionConfig.class})
@EnableFeignClients
@MapperScan("cn.xylvvv.gulimall.product.dao")
@SpringBootApplication
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
