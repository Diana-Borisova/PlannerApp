package com.example.demo.config;


import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Value("dbeukad1v")
    private String cloudName;
    @Value("454669545666359")
    private String apiKey;
    @Value("CLOUDINARY_SECRET")
    private String apiSecret;

    @Bean
    public Cloudinary createCloudinaryConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        return new Cloudinary(config);
    }
}
