package com.example.nour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.nour.model.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/**", "/webjars/**").permitAll()
                .antMatchers("/photographer/**").hasRole("PHOTO")
                .antMatchers("/school/**").hasRole("ADMIN")    
                .antMatchers("/parent/**").hasRole("PARENT") 
//                .antMatchers("/region/all","/country/all","/location/all").hasAnyRole("SALES","CEO","FIN_AC")
//                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .successHandler(myAuthenticationSuccessHandler())
                .permitAll()
                .and()
            .logout()
                .permitAll()
                .and()
            .exceptionHandling().accessDeniedPage("/accessDenied.html");
    }
    
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
		 .userDetailsService(userDetailsService)
		 .passwordEncoder(bCryptPasswordEncoder);
	}
    
}
