package com.Aleksa.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	//uklanja security pri ucitavanju sajta
	//security je sad samo pod admin sekcijom
	@Bean
	public SecurityFilterChain sfc(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(csrf->csrf.disable()).authorizeHttpRequests(authorize->authorize.
				requestMatchers("/admin/**").authenticated()
				.requestMatchers("/**").permitAll()).formLogin(
						form->form
						.permitAll()).logout(logout->logout.addLogoutHandler(null))
		
		;
		return httpSecurity.build();
	}
	
//  Ova metoda omogućava da se lozinke šifruju (hash-uju) pre nego što se sačuvaju u bazu
//	i da se sigurno proveravaju prilikom logovanja korisnika
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
}
