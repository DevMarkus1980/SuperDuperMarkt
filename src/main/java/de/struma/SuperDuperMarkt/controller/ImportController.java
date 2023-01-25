package de.struma.SuperDuperMarkt.controller;

import de.struma.SuperDuperMarkt.model.Artikel;
import de.struma.SuperDuperMarkt.service.ArtikelService;
import de.struma.SuperDuperMarkt.service.ImportService;
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
	ImportService importService;
	public ImportController(ArtikelService artikelService, ImportService importService){
		this.artikelService = artikelService;
		this.importService = importService;
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
		importService.readCsvToRepo(file);
		model.addAttribute("message", "File uploaded successfully!");
		model.addAttribute("newArtikel", new Artikel());
		return "pages/import";

	}


}
