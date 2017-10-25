//package org.spring.springboot.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// * @author sapphire
// * @version V1.0.0
// * @Description Define collapse domain request access  定义垮域请求访问头
// * @date 2017/5/8
// */
//@Configuration
//public class CorsHeader {
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
//                        .allowCredentials(true)
//                        .allowedMethods("PUT", "DELETE", "POST", "GET")
////                        .allowedHeaders("header1", "header2", "header3")
////                        .exposedHeaders("header1", "header2")
//                        .allowCredentials(false).maxAge(3600);
//            }
//        };
//    }
//}
