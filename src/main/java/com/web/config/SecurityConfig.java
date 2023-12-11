package com.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private MemberUserDetailService memberUserDetailService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		System.out.println("--- Security Run ---");
		http
			.cors()
			.and()
			.csrf().disable()
			.headers().frameOptions().disable()
			.and()
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/mypage/**").hasRole("USER") // 로그인만 접근
			.antMatchers("/support/**").authenticated() // 로그인만 접근
			.antMatchers("/admin/**").hasRole("ADMIN") // 권한 설정 후 재설정 할 예정 
			.antMatchers("/pointshop/product").hasAnyRole("USER", "ADMIN") // 권한 설정 후 재설정 할 예정
			.antMatchers("/pointshop/productInsert").hasRole("ADMIN") // 권한 설정 후 재설정 할 예정
			.antMatchers("/pointshop/productModify").hasRole("ADMIN") // 권한 설정 후 재설정 할 예정
			.and()
			.formLogin()
			.loginPage("/login")// 이쪽 페이지로 이동함
			.defaultSuccessUrl("/")
			.loginProcessingUrl("/login")	// 호출시 비밀번호 인증 페이지 호출
			.usernameParameter("id")
			.passwordParameter("password")
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.and()
			.exceptionHandling().accessDeniedPage("/") // 접근불가 페이지 >> home
			.and() 
			.logout()
			.logoutUrl("/logout")
			.invalidateHttpSession(true).logoutSuccessUrl("/")
			.and()
			.userDetailsService(memberUserDetailService)
		;
		return http.build();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
