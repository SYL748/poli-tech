package com.magic.MagicServer.config;

/*
 Purpose: Central place for application-wide configuration in this Spring Boot app.
 Use this class to introduce configuration for:
 - HTTP/CORS policy
 - JSON serialization (Jackson)
 - MVC customization (formatters, converters, interceptors)
 - Embedded Tomcat tuning beyond properties
 - Security defaults (if Spring Security is added)

 All sections below are commented-out templates. You can copy or uncomment any
 part as needed when you decide to enable that configuration.
*/
public class AppConfig {
	/*
	// Enable this class as a Spring configuration component
	//@Configuration
	//public class AppConfig implements WebMvcConfigurer {

		// --- Global CORS example ---
		//@Override
		//public void addCorsMappings(CorsRegistry registry) {
		//	registry.addMapping("/**")
		//		.allowedOriginPatterns("http://localhost:3000")
		//		.allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS")
		//		.allowedHeaders("*")
		//		.allowCredentials(true)
		//		.maxAge(3600);
		//}

		// --- Jackson customization example ---
		//@Bean
		//public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
		//	return builder -> {
		//		builder.modules(new JavaTimeModule());
		//		builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		//	};
		//}

		// --- MVC customization example ---
		//@Override
		//public void addFormatters(FormatterRegistry registry) {
		//	// registry.addConverter(new YourCustomConverter());
		//}

	//}

	// --- Tomcat advanced tuning example ---
	//@Bean
	//public TomcatServletWebServerFactory tomcatFactory() {
	//	TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
	//	factory.addConnectorCustomizers(connector -> {
	//		connector.setProperty("maxHttpHeaderSize", "65536");
	//		connector.setProperty("relaxedQueryChars", "|{}[]");
	//	});
	//	return factory;
	//}

	// --- Spring Security baseline (if security starter is added) ---
	//@Bean
	//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	//	http
	//		.csrf(csrf -> csrf.disable())
	//		.authorizeHttpRequests(auth -> auth
	//			.requestMatchers("/actuator/health").permitAll()
	//			.anyRequest().permitAll()
	//		);
	//	return http.build();
	//}

	// --- Imports for the commented examples ---
	// import org.springframework.context.annotation.Bean;
	// import org.springframework.context.annotation.Configuration;
	// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
	// import org.springframework.web.servlet.config.annotation.CorsRegistry;
	// import org.springframework.format.FormatterRegistry;
	// import com.fasterxml.jackson.databind.SerializationFeature;
	// import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
	// import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
	// import org.springframework.security.web.SecurityFilterChain;
	// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	*/
}


