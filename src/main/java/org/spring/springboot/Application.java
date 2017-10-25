package org.spring.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.spring.springboot.redis.ExecutorServiceUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring Boot 应用启动类
 *
 * Created by ym on 11/04/2017
 */
// Spring Boot 应用的标识
@SpringBootApplication
// mapper 接口类扫描包配置
@EnableSwagger2
@EnableAsync
@EnableScheduling
@MapperScan("org.spring.springboot.dao")
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        //

        SpringApplication.run(Application.class,args);
        ExecutorServiceUtils.runScheduledThread();

        /*//初始化车流定时状态为0,代表没执行
        RedisTemplate redisTemplate = SpringContextUtils.getBean(RedisTemplate.class);
        RedisCache.putHash(redisTemplate, "scheduled:", "carflow", "0");*/
    }
}
