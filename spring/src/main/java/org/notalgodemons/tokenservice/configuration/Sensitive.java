package org.notalgodemons.tokenservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:custom.properties")
public class Sensitive {

    @Value("${creatorWallet}")
    private String creatorWallet;

    @Value("${contractAddress}")
    private String contractAddress;

    @Value("${studentPrivateKey}")
    private String studentPrivateKey;

    @Bean
    public String getCreatorWallet() {
        return creatorWallet;
    }

    @Bean
    public String getContractAddress() {
        return contractAddress;
    }

    @Bean
    public String getStudentPrivateKey() {
        return studentPrivateKey;
    }
}
