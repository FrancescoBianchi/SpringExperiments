package uk.co.wowcher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Deals with the configuration of the MVC functionalities of the application.
 * We need it only to register the resolver for multipart requests.
 * 
 * @author francesco.bianchi
 */
@Configuration
@ComponentScan({"uk.co.wowcher", "uk.co.wowcher.service.impl"})
@EnableWebMvc
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    
	/**
	 * Registration of the bean in charge of parsing the multipart request
	 * 
	 * @return the bean in charge of parsing the multipart request
	 */
	@Bean
    public MultipartResolver multipartResolver() {
    	return new CommonsMultipartResolver();
    }
	
}
