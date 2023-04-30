package com.luthando.server;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.luthando.server.enumeration.Status;
import com.luthando.server.model.Server;
import com.luthando.server.repo.ServerRepo;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
	
	//bean that runs after application has been initialized
	@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		//return error function
		return args -> {
			serverRepo.save(new Server(null, "192.168.1.160", "Fedora Linux", "16B", "Work PC", "http://localhost/8080/server/image/download.png", Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.150", "Ubuntu Linux", "64GB", "Web Server", "http://localhost/8080/server/image/download1.png", Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.68", "Red Hat", "32GB", "Dell Tower", "http://localhost/8080/server/image/download2.png", Status.SERVER_DOWN));
			serverRepo.save(new Server(null, "192.168.1.23", "MS 2008", "16B", "Mail Server", "http://localhost/8080/server/image/download3.png", Status.SERVER_UP));
		};
	}
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
