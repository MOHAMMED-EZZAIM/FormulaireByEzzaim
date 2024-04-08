//package org.sid.formulairebackend.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//import java.util.List;
//
//import static java.util.Arrays.asList;
//@CrossOrigin("*")
//@Configuration
//public class Config {
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
//        corsConfiguration.setAllowedHeaders(asList(
//                "Origin", "Access-Control-Allow-Origin",
//                "content-type", "Accept",
//                "Authorization", "Origin, Accept",
//                "X-Request-With", "Access-Control-Request-Method",
//                "Access-Control-Request-Headers"));
//        corsConfiguration.setExposedHeaders(asList("Origin",
//                "content-type", "Accept", "Authorization",
//                "Access-Control-Allow-Origin",
//                "Access-Control-Allow-Origin",
//                "Access-Control-Allow-Credentials"));
//        corsConfiguration.setAllowedMethods(asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//        return new CorsFilter(urlBasedCorsConfigurationSource);
//    }
//}