package com.camerapipeline.camera_pipeline.core.security.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@ConfigurationProperties(prefix = "security")
public class CorsConfig {

    private final CorsConfiguration cors = new CorsConfiguration();
    private final Log logger = LogFactory.getLog(this.getClass());

    public CorsConfiguration getCors() {
        return cors;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        logger.debug("Registering CORS filter");
        cors.addAllowedOrigin("*");
        cors.addAllowedHeader("*");
        cors.addAllowedMethod("GET");
        cors.addAllowedMethod("PUT");
        cors.addAllowedMethod("POST");
        cors.addAllowedMethod("PATCH");
        cors.addAllowedMethod("DELETE");
        
        source.registerCorsConfiguration("/**", cors);
        return new CorsFilter(source);
    }
}
