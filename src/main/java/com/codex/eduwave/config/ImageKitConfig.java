package com.codex.eduwave.config;

import io.imagekit.sdk.ImageKit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageKitConfig {
    @Value("${imagekit.privateKey}")
    private String privateKey;

    @Value("${imagekit.publicKey}")
    private String publicKey;

    @Value("${imagekit.urlEndpoint}")
    private String urlEndpoint;
    @Bean
    public ImageKit imageKit() {
        io.imagekit.sdk.config.Configuration configuration = new io.imagekit.sdk.config.Configuration(publicKey,privateKey,urlEndpoint);
        ImageKit imageKit = ImageKit.getInstance();
        imageKit.setConfig(configuration);
        return imageKit;
    }
}
