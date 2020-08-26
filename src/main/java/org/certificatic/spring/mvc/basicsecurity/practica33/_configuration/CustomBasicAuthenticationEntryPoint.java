package org.certificatic.spring.mvc.basicsecurity.practica33._configuration;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

// Analiza clase CustomBasicAuthenticationEntryPoint
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	private String realmName;
	
	public CustomBasicAuthenticationEntryPoint(String realmName) {
		this.realmName = realmName;
	}

	@Override // Analiza metodo commence
	public void commence(final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException authException) throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		
		response.addHeader("WWW-Authenticate", "Basic realm=\"" + this.getRealmName()+"\"");

		PrintWriter writer = response.getWriter();
		writer.println("HTTP Status 401 : " + authException.getMessage());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setRealmName(realmName);
		super.afterPropertiesSet();
	}
}