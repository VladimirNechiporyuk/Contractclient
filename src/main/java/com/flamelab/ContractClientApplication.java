package com.flamelab;

import com.flamelab.client.ContractFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableFeignClients(clients = ContractFeignClient.class)
@EnableWebMvc
public class ContractClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContractClientApplication.class, args);
    }
}
