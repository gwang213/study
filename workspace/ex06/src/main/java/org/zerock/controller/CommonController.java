package org.zerock.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class CommonController {

	@GetMapping("/accessError")
	public void accessDenied(Authentication auth,Model model) {
		log.info("access Denied: "+auth);
		
		model.addAttribute("msg","Access Denied");
	}
}
