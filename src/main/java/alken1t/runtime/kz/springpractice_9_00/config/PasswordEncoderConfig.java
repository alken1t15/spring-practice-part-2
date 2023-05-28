package alken1t.runtime.kz.springpractice_9_00.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {


 @Bean
    PasswordEncoder passwordEncoder(){
     return NoOpPasswordEncoder.getInstance();
 }
}