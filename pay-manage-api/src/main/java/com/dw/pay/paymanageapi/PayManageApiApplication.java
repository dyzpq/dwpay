package com.dw.pay.paymanageapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages={"com.dw.pay"} ,exclude = {MultipartAutoConfiguration.class})
@ServletComponentScan
@MapperScan(basePackages={"com.dw.pay.*.mapper"})
public class PayManageApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayManageApiApplication.class, args);
	}

	@Bean
	@Order(0)
	public CorsFilter corsFilter()
	{
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(Boolean.valueOf(true));
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.setMaxAge(Long.valueOf(18000L));
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

}
