package org.certificatic.spring.mvc.basicsecurity.practica33._configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration

// Habilita Spring Web MVC
@EnableWebMvc

// Habilita escaneo de beans sobre el paquete "org.certificatic.spring.mvc.basicsecurity.practica33.controller"
@ComponentScan(basePackages = "org.certificatic.spring.mvc.basicsecurity.practica33.controller")
public class ServletContextConfiguration {

}