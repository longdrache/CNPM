package com.hcmute.shop.Configuration;


import com.hcmute.shop.Configuration.LoginHandler.SuccessHandler;
import com.hcmute.shop.Model.User;
import com.hcmute.shop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
public class SecurityAdapter extends WebSecurityConfigurerAdapter {
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public final ApplicationUserService applicationUserService;
    @Autowired
    SuccessHandler successHandler;
    @Autowired
    UserService userService;

    public SecurityAdapter(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/").loginProcessingUrl("/login").successHandler(successHandler).failureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                boolean kt= userService.isActive(httpServletRequest.getParameter("username"));
                if(kt) {
                    httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Fail");
                }
                else {
                    httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND,"Not active");
                }
            }
        })
         .and()
                .rememberMe()
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(7))
                .key(UUID.randomUUID().toString())
                .userDetailsService(applicationUserService)
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
                .clearAuthentication(true).invalidateHttpSession(true)
                .deleteCookies("JSESSIONID","remember-me")
                .logoutSuccessUrl("/");



    }





    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;

    }


}
