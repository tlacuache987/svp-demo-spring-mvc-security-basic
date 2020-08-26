package org.certificatic.spring.mvc.basicsecurity.practica33._configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration

// Habilita configuracion web con Spring Security
@EnableWebSecurity

// Extiende de WebSecurityConfigurerAdapter
public class SecurityContextConfiguration extends WebSecurityConfigurerAdapter {

	public static final String REALM_NAME = "Mi application realm";

	// Sobre escribe el metodo configure(AuthenticationManagerBuilder auth)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
				.withUser("admin").password("admin").roles("ADMIN")
				.and()
				.withUser("xvanhalenx").password("123123").roles("ROOT", "ADMIN")
				.and()
				.withUser("user").password("user").roles("USER");
		
		// auth.userDetailsService(customUserDetailsService());

	}

	// @Bean
	public UserDetailsService customUserDetailsService() {
		return (String username) -> {
			
			if(username == "admin")
				return new User("admin", "admin", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
			
			if(username == "xvanhalenx")
				return new User("xvanhalenx", "123123", Arrays.asList(new SimpleGrantedAuthority("ROLE_ROOT"), 
																	  new SimpleGrantedAuthority("ROLE_ADMIN")));
			
			if(username == "user")
				return new User("user", "user", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
			
			return null;
		};
	}

	// Sobre escribe el metodo configure(HttpSecurity http)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/welcome").authenticated()
				.antMatchers("/").permitAll()
				.antMatchers("/root*/**").hasAuthority("ROLE_ROOT") // .hasRole("ROOT")
				.antMatchers("/admin*/**").hasAuthority("ROLE_ADMIN") // .hasRole("ADMIN")
				.antMatchers("/user*/**").hasAuthority("ROLE_USER") // .hasRole("USER")
			.and()
				.httpBasic()
					//.realmName(REALM_NAME)
					.authenticationEntryPoint(customAuthenticationEntryPoint())
			.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
		
	}

	// Sobre escribe el metodo configure(WebSecurity web)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
				.antMatchers(HttpMethod.OPTIONS, "/**");
	}

	// Define Bean AccessDeniedHandler
	@Bean
	public CustomAccessDeniedHandler customAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	// Define Bean BasicAuthenticationEntryPoint
	@Bean
	public CustomBasicAuthenticationEntryPoint customAuthenticationEntryPoint() {
		return new CustomBasicAuthenticationEntryPoint(REALM_NAME);
	}

	
}
