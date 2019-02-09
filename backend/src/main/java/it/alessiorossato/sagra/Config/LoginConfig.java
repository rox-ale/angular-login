package it.alessiorossato.sagra.Config;


import it.alessiorossato.sagra.service.UserServiceImpl.UserSecurityService;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

/**
 * Created by magemello on 14/05/2017.
 */
@Configuration
@Order(-20)
public class LoginConfig extends WebSecurityConfigurerAdapter{

/*
    @Autowired
    private Environment env;*/

    @Autowired
    private UserSecurityService userSecurityService;

    private static final String SALT = "salt"; // Salt should be protected carefully

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
      /*  http.csrf().disable()
                .requestMatchers().antMatchers("/login","/oauth/authorize",
                "/oauth/confirm_access")
                .and().authorizeRequests().anyRequest().authenticated();*/
      /*  http.cors().and()
                .csrf().disable()
                .requestMatchers().antMatchers("/login","/oauth/authorize",
                "/oauth/confirm_access","/oauth")
                .and().authorizeRequests().anyRequest().authenticated();

*/
/*
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers("/login","/oauth/authorize",
                "/oauth/confirm_access","/oauth","/oauth/token").permitAll().
                anyRequest().authenticated();
        */

        http.
                authorizeRequests()
                .antMatchers("/login","/oauth/authorize",
                        "/oauth/confirm_access","/oauth","/oauth/token").permitAll()
                .and().csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }
/*
    @Autowired
    public void insertUsers(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("mario").password("secret").roles("USER").and()
                .withUser("admin").password("secret").roles("ADMIN");
    }*/


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//    	 auth.inMemoryAuthentication().withUser("user").password("password").roles("USER"); //This is in-memory authentication
        auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }

}
