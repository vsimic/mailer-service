package rs.etf.vladan.simic;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringCloudApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class MailingNotifierApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailingNotifierApplication.class, args);
    }
}
