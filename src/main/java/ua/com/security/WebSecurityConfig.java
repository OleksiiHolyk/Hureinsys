package ua.com.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*        http
                .authorizeRequests()
                    .antMatchers("/images*//**","/css*//**","/js*//**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login").permitAll()
                .and()
                    .logout().permitAll();*/



//permitAll
        http
                .authorizeRequests()
                    .antMatchers("/users/**").hasRole("USER")
                    .anyRequest().permitAll()
                .and()
                    .httpBasic()
                .and()
                    .csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("ben").password("ben").authorities("ROLE_USER")
                .and()
                .withUser("den").password("den").authorities("ROLE_USER", "ROLE_ADMIN");
    }
}