package io.starter.service.config;

import io.starter.service.controller.mapping.WebMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 04.02.2018
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN = "ADMIN";

    @Value("${starter.web.uid:admin}")
    private String uid;

    @Value("${starter.web.password:1234}")
    private String password;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(WebMapper.LOGIN,
                        "/api/**",
                        "/css/**", "/static/css/**", "/webjars/bootstrap/**").permitAll()
                .antMatchers(WebMapper.SECURED + "/**", WebMapper.SWAGGER_DOC, WebMapper.SWAGGER_UI).hasAnyRole(ADMIN)
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .failureUrl(WebMapper.LOGIN + "?error=true")
                .defaultSuccessUrl(WebMapper.HOME, true)
                .loginPage(WebMapper.LOGIN)
                .usernameParameter("uid")
                .passwordParameter("password")
                .permitAll()
            .and()
                .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(uid)
                .password(password)
                .roles(ADMIN);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers( "/static/**", "/css/**", "/webjars/bootstrap/**");
    }
}
