package ttadm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
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

@Configuration
@EnableWebSecurity
@Order(0)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	
	{
		System.out.println("SecurityConfig");
	}
	
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("user1").password("123").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("Pa$$word").roles("ADMIN");
        //auth.inMemoryAuthentication().withUser("dba").password("123").roles("ADMIN","DBA");//dba have two roles.
        auth.inMemoryAuthentication().withUser("order").password("111").roles("ORDERS");//dba have two roles.
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
        .antMatchers("/").permitAll() 
        .antMatchers("/admin/**").access("hasRole('ADMIN')")
        //.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
        .antMatchers("/eshop/**").access("hasRole('ORDERS')")
        .and().formLogin()
        	.successHandler(customAuthenticationSuccessHandler)
        	.loginPage("/")
			//.failureUrl("/login?error")
			.usernameParameter("username").passwordParameter("password")				
			.and()
			.logout().logoutSuccessUrl("/?logout")
        .and()
        
        	.exceptionHandling().accessDeniedPage("/403");
  
    }

}
