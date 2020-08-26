package org.certificatic.spring.mvc.basicsecurity.practica33.controller;

import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController // Analiza Root Controller
public class RootController {

	@RequestMapping(value = "/root", method = RequestMethod.GET)
	public String root() {

		log.info("show root info ------------------");

		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return "Welcome " + principal.getUsername() + " you're Root. Assigned Roles: " + principal.getAuthorities()
				.stream().map(auth -> auth.getAuthority()).collect(Collectors.joining(", "));
	}

}
