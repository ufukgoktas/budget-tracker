package com.ufuk.budget.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "jwt.token")
@Configuration
public class JwtProperties {
    private String jwtExpiration; // ms cinsinden yani token sadece 70 sn boyunca geçerli.
    private String secretKey; //Bu sabit, JWT'nin imzalanması ve doğrulanması için kullanılan gizli anahtarı belirtir.

    public String getJwtExpiration() {
        return jwtExpiration;
    }

    public void setJwtExpiration(String jwtExpiration) {
        this.jwtExpiration = jwtExpiration;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
