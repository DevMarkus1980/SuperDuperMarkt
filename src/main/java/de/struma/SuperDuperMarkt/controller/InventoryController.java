package de.struma.SuperDuperMarkt.controller;

import de.struma.SuperDuperMarkt.model.Artikel;
import de.struma.SuperDuperMarkt.service.ArtikelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequestMapping(value = {"/inventory"})
@Controller
public class InventoryController {

	ArtikelService artikelService;

	public InventoryController(ArtikelService artikelService){
		this.artikelService = artikelService;
	}

	@GetMapping
	public String showInventory(Model model) {
		model.addAttribute("artikelList", artikelService.getArtikelList());
		return "pages/inventory";
	}
	

}
