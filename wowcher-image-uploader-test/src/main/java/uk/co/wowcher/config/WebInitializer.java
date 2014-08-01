package uk.co.wowcher.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer {

	private int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB
	
	public void onStartup(ServletContext servletContext)
			throws ServletException {

		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(Config.class);

		ctx.setServletContext(servletContext);

		Dynamic apiServlet = servletContext.addServlet("api",
				new DispatcherServlet(ctx));
		apiServlet.addMapping("/api/*");
		apiServlet.setMultipartConfig(new MultipartConfigElement(null, maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2));
	}

}