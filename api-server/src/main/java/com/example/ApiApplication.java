package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@EnableResourceServer
@SpringBootApplication
public class ApiApplication {

	@Bean
	public ResourceServerConfigurerAdapter resourceServerConfigurerAdapter() {
		return new ResourceServerConfigurerAdapter() {
			@Override
			public void configure(HttpSecurity http) throws Exception {
				http.headers().frameOptions().disable();
				http.authorizeRequests().antMatchers("/members", "/members/**").permitAll();
			}
		};
	}

	/**
	 * API를 조회시 출력될 테스트 데이터
	 * 
	 * @param memberRepository
	 * @return
	 */
	@Bean
	public CommandLineRunner commandLineRunner(MemberRepository memberRepository) {
		return args -> {
			memberRepository.save(new Member("ctk0327", "ctk0327@naver.com", "aaaa", "전태경"));
			memberRepository.save(new Member("fluxian", "fluxian@naver.com", "aaaa", "임지수"));
			memberRepository.save(new Member("oyrin9595", "oyrin9595@naver.com", "aaaa", "오예린"));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
}