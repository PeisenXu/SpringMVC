package com.sena;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {
    @Value("${mandrill.key}")
    private String mandrillApiKey;

    @Bean
    public MandrillApi mandrillApi(){
        return new MandrillApi(mandrillApiKey);
    }
}
