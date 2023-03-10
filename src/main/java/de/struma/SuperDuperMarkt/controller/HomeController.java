package de.struma.SuperDuperMarkt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping(value = {"/home", "/"})
@Controller
public class HomeController {

	@GetMapping
	public String showHome(Model model) {
		return "pages/home";
	}
	

}
