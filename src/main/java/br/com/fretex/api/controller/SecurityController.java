package br.com.fretex.api.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fretex.config.AuthUser;

@Controller
public class SecurityController {

	@RequestMapping(value = "/user-auth", method = RequestMethod.GET)
	@ResponseBody
	public AuthUser user() {
		return (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
