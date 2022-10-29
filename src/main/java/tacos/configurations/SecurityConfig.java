package tacos.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/design", "/orders")
                .hasRole("USER")
                .antMatchers("/", "/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login");


//        http
//                .authorizeRequests()
//                .antMatchers("/design", "/orders")
//                .access("hasRole('ROLE_USER')")
//                .antMatchers("/", "/**").access("permitAll");
//
//
//        http
//                .authorizeRequests()
//                .antMatchers("/design", "/orders")
//                .access("hasRole('ROLE_USER') && " +
//                        "T(java.util.Calendar).getInstance().get("+
//                        "T(java.util.Calendar).DAY_OF_WEEK) == " +
//                        "T(java.util.Calendar).TUESDAY")
//                .antMatchers("/", "/**").access("permitAll")
        ;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
