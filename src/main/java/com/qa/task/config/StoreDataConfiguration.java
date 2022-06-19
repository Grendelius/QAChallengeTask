package com.qa.task.config;

import com.qa.task.core.UserData;
import com.qa.task.model.User;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "store")
public class StoreDataConfiguration {

    @Setter
    private List<User> users;

    @Bean
    public UserData userData() {
        return new UserData(users);
    }

}
