package com.duypk.socket.config;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Value("${jwt.signerKey}")
	private String SIGNER_KEY;
	
	@Bean
    SecurityFilterChain defaultSpringSecFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(req ->
                                req.requestMatchers("/botchat-mess/**","/auth/**").permitAll()
                                .anyRequest().authenticated())
                    .formLogin(form -> form
                                .loginPage("/login")
                                .permitAll())
                    .logout(logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")
                                .permitAll());
                    
        http.oauth2ResourceServer(oauth2 ->
        			oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder()))
        				  .authenticationEntryPoint(new DefaultAuthenticationEntryPoint())
        			);
                    
        http.csrf(AbstractHttpConfigurer::disable);
        
        return http.build();
    }
	
	@Bean
	JwtDecoder jwtDecoder() {
		SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNER_KEY.getBytes(), "HS512");
		return NimbusJwtDecoder
				.withSecretKey(secretKeySpec)
				.macAlgorithm(MacAlgorithm.HS512)
				.build();
	}

}