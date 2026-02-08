package org.zisik.edu.external.aladin;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aladin.api")
@Getter
@Setter
public class AladinApiConfig {

    private String baseUrl = "http://www.aladin.co.kr/ttb/api";
    private String ttbKey;
}
