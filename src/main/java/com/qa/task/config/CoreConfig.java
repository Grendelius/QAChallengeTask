package com.qa.task.config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CoreConfig {

    @Bean
    public HttpClient httpClient() {
        return HttpClients.createMinimal();
    }

}
