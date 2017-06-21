package ttadm.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import ttadm.bean.SessionBean;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;
	
	@Autowired
	CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Autowired
	SessionBean sessBean;

	
	{
		
		System.out.println("WebSecurityConfig");
	}
	
     
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
    	//For russian charset
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter,CsrfFilter.class);

      http.authorizeRequests()
        .antMatchers("/", "/loginPage").permitAll() 
        .antMatchers("/admin/**").access("hasRole('ADMIN')")
        //.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
        .antMatchers("/eshop/**").access("hasRole('ORDERS')")
        .and().formLogin()
        	.successHandler(customAuthenticationSuccessHandler)
        	.loginPage("/login")
			//.defaultSuccessUrl("/admin")
			.failureUrl("/login?error")
			.usernameParameter("username").passwordParameter("password")				
			.and()
			.logout().logoutSuccessUrl("/login?logout")
        .and()
        	.exceptionHandling().accessDeniedPage("/403");
  
    }
	

}
