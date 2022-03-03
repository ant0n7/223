package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * AppConfig - Configuration class for Project, maintains spring {@link Bean}s
 *
 * <ul>
 *     <li>To Produce a {@link ModelMapper} {@link Bean}</li>
 *     <li>To Produce a {@link PasswordEncoder} {@link Bean}</li>
 *     <li>To Produce a {@link DaoAuthenticationProvider} {@link Bean}</li>
 * </ul>
 *
 * @author Remo Aeberli
 */
@Configuration
public class AppConfig {
    /**
     * SonarLint Rule java:S3305 ignored, because Service needs to be autowired. Injecting this field into the only method that uses it wouldn't work in this case.
     */
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
