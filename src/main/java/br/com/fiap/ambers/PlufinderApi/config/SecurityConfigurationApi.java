package br.com.fiap.ambers.PlufinderApi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.fiap.ambers.PlufinderApi.service.AuthenticationService;
import br.com.fiap.ambers.PlufinderApi.service.LoginService;
import br.com.fiap.ambers.PlufinderApi.service.TokenService;
import br.com.fiap.ambers.PlufinderApi.service.UsuarioService;
import br.com.fiap.ambers.PlufinderApi.security.AuthorizationFilter;

@Configuration
@Order(1)
public class SecurityConfigurationApi extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private TokenService tokenService;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService)
			.passwordEncoder(AuthenticationService.getPasswordEnconder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**")
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/auth")
					.permitAll()
					.antMatchers(HttpMethod.POST, "/usuario")
					.permitAll()
					.antMatchers(HttpMethod.POST, "/login")
					.permitAll()
					.anyRequest()
					.authenticated()
				.and()
					.csrf()
					.disable()
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
					.addFilterBefore(new AuthorizationFilter(tokenService, loginService), UsernamePasswordAuthenticationFilter.class)
				;
	}

}
