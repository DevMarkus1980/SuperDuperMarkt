package de.struma.SuperDuperMarkt.controller;

import de.struma.SuperDuperMarkt.model.Artikel;
import de.struma.SuperDuperMarkt.service.ArtikelService;
import de.struma.SuperDuperMarkt.service.CSVService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RequestMapping(value = {"/import"})
@Controller
public class ImportController {

	ArtikelService artikelService;
	CSVService csvService;
	public ImportController(ArtikelService artikelService, CSVService csvService){
		this.artikelService = artikelService;
		this.csvService = csvService;
	}

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
	@PostMapping("/csv")
	public String handleFileUpload(Model model,
		   @RequestParam("file") MultipartFile file) throws IOException {
		csvService.readFile(file);
		model.addAttribute("message", "File uploaded successfully!");
		return "pages/import";

	}


}
