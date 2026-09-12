package za.co.christianassembliesparkwood.membershiponboarding.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String USER_ROLE = "USER";
    private static final String ADMIN_ROLE = "ADMIN";

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        var admin = User.withUsername("Admin")
                .password(encoder.encode("MasterKey"))
                .roles(USER_ROLE, ADMIN_ROLE)
                .build();
        var user = User.withUsername("user")
                .password(encoder.encode("password"))
                .roles(USER_ROLE)
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/member/create").hasRole(USER_ROLE)
                        .requestMatchers(HttpMethod.GET, "/member/read/**").hasRole(USER_ROLE)
                        .requestMatchers(HttpMethod.PUT, "/member/update").hasRole(ADMIN_ROLE)
                        .requestMatchers(HttpMethod.DELETE, "/member/delete/**").hasRole(ADMIN_ROLE)
                        .requestMatchers(HttpMethod.GET, "/member/all").hasRole(USER_ROLE)
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable());
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
