package org.certificatic.spring.mvc.basicsecurity.practica33._dipatcherservletinitializer;

import org.certificatic.spring.mvc.basicsecurity.practica33._configuration.RootContextConfiguration;
import org.certificatic.spring.mvc.basicsecurity.practica33._configuration.SecurityContextConfiguration;
import org.certificatic.spring.mvc.basicsecurity.practica33._configuration.ServletContextConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// Extiende de AbstractAnnotationConfigDispatcherServletInitializer
public class SpringWebMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	// Sobre escribe el metodo getRootConfigClasses
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// Clases que levantan el Root Application Context
		return new Class[] { RootContextConfiguration.class, SecurityContextConfiguration.class};
	}

	// Sobre escribe el metodo getServletConfigClasses
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// Clase que levanta Dispatcher Servlet (Web Application Context)
		return new Class[] { ServletContextConfiguration.class  }; 
	}

	// Sobre escribe el metodo getServletMappings
	@Override
	protected String[] getServletMappings() {
		// servlet-mapping del Dispatcher Servlet
		return new String[] { "/" };
	}
	
}