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

    @Value("${ACCOUNT_SID}")
    private String ACCOUNT_SID;

    @Value("${AUTH_TOKEN}")
    private String AUTH_TOKEN;

    @Value("${PRIVATE_KEY}")
    private String PRIVATE_KEY;

    @Value("${RECIPIENT}")
    private String RECIPIENT;

    @Value("${TwilioNo}")
    private String TwilioNo;

    @Value("${MyNo}")
    private String MyNo;

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

    @Bean
    public String getACCOUNT_SID() {
        return ACCOUNT_SID;
    }

    @Bean
    public String getAUTH_TOKEN() {
        return AUTH_TOKEN;
    }

    @Bean
    public String getPRIVATE_KEY() {
        return PRIVATE_KEY;
    }

    @Bean
    public String getRECIPIENT() {
        return RECIPIENT;
    }

    @Bean
    public String getTwilioNo() {
        return TwilioNo;
    }

    @Bean
    public String getMyNo() {
        return MyNo;
    }
}
