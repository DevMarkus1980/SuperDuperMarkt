package de.struma.SuperDuperMarkt.controller;

import de.struma.SuperDuperMarkt.model.Artikel;
import de.struma.SuperDuperMarkt.service.ArtikelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping(value = {"/import"})
@Controller
public class ImportController {

	ArtikelService artikelService;
	public ImportController(ArtikelService artikelService){this.artikelService = artikelService;}

	@GetMapping
	public String showHome(Model model) {
		model.addAttribute("newArtikel", new Artikel());
		return "pages/import";
	}
	@PostMapping(value = {"/neue"})
	public String showHome(Model model, @ModelAttribute Artikel artikel) {
		artikelService.createNewArtikel(artikel);
		model.addAttribute("newArtikel", new Artikel());
		return "redirect:/import";
	}


}
