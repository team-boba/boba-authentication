package com.beaconfireboba.authserver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Getter
@Setter
@Configuration
public class MailConfig {
    @Value("spring.mail.username")
    String from;

}
