package top.dogtcc.test.server1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import top.dogtcc.test.server1.client.OrderServerClient;


@SpringBootApplication(scanBasePackages = {"top.dogtcc"})
@EnableFeignClients
@EnableAspectJAutoProxy
@Configuration
@EnableDiscoveryClient
public class BffApplication {


    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(BffApplication.class, args);

    }
}