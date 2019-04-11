package top.dogtcc.test.server3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = {"top.dogtcc"})
@EnableFeignClients
@EnableAspectJAutoProxy
@Configuration
@EnableDiscoveryClient
public class GoodsApplication {

    public static void main(String[] args) {

        SpringApplication.run(GoodsApplication.class, args);

    }

}