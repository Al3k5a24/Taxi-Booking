package com.Aleksa.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private LogOutHandler lgh;
	//uklanja security pri ucitavanju sajta
	//security je sad samo pod admin sekcijom
	@Bean
	public SecurityFilterChain sfc(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/admin/**").authenticated()
				.requestMatchers("/**").permitAll()
			)
			.formLogin(form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/admin/dashboard", true)
				.permitAll()
			)
			.logout(logout -> logout
				.logoutUrl("/logout")
			);
		httpSecurity.csrf(csrf->csrf.disable()).authorizeHttpRequests(authorize->authorize.
				requestMatchers("/admin/**").authenticated()
				.requestMatchers("/**").permitAll()).formLogin(
						form->form
						.permitAll()).formLogin(form->form.loginPage("/login")
						.defaultSuccessUrl("/admin/dashboard", true) )
		.logout(logout->logout
				.logoutUrl("/logout"))
		
		;
		return httpSecurity.build();
	}
	
//  Ova metoda omogućava da se lozinke šifruju (hash-uju) pre nego što se sačuvaju u bazu
//	i da se sigurno proveravaju prilikom logovanja korisnika

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }2
}
