package de.struma.SuperDuperMarkt.controller;

import de.struma.SuperDuperMarkt.service.ArtikelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Slf4j
@RequestMapping(value = {"/regal"})
@Controller
public class RegalController {

	ArtikelService artikelService;

	public RegalController(ArtikelService artikelService){
		this.artikelService = artikelService;
	}

	@GetMapping
	public String showRegalToday(Model model) {
		model.addAttribute("datum", LocalDate.now());
		model.addAttribute("view", "regal");
		model.addAttribute("artikelList", artikelService.findAllByRegalAuslegen(LocalDate.now()));
		return "pages/regal";
	}
	@GetMapping(value = "/increment/")
	public String showRegalAfterUpdateDate(@RequestParam(name ="datum") String datum, Model model) {

		LocalDate datumNew = LocalDate.parse(datum);
		datumNew = datumNew.plusDays(1);
		model.addAttribute("datum", datumNew);
		model.addAttribute("view", "regal");
		model.addAttribute("artikelList", artikelService.findAllByRegalAuslegen(datumNew));
		return "pages/regal";
	}

}
