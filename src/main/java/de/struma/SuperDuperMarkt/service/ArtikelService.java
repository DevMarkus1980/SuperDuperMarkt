package de.struma.SuperDuperMarkt.service;

import de.struma.SuperDuperMarkt.model.Artikel;
import de.struma.SuperDuperMarkt.repository.ArtikelRepository;
import de.struma.SuperDuperMarkt.strategy.StrategyMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class ArtikelService {
    ArtikelRepository artikelRepository;
    StrategyMapping strategyMapping;

    public ArtikelService(ArtikelRepository artikelRepository, StrategyMapping strategyMapping){
        this.artikelRepository = artikelRepository;
        this.strategyMapping = strategyMapping;
    }

    public List<Artikel> getArtikelList(){
        SetDummyList();
        validateArtikel(LocalDate.now());
        return artikelRepository.findAll();
    }

    private void setDummyList(){
        artikelRepository.saveAllAndFlush(new Artikel().dummyList());
    }

    public Object findAllByRegalAuslegen(boolean eingeraumtOderNicht, LocalDate updateDatum) {
        SetDummyList();
        validateArtikel(updateDatum);
        return artikelRepository.findAllByRegalAuslegen(eingeraumtOderNicht);
    }

    // Methoden gegen Redundanzen
    public void validateArtikel(LocalDate validateDatum){
        for (Artikel artikel:artikelRepository.findAll()) {
            strategyMapping.validateArtikelWithStrategie(artikel, validateDatum);
            artikelRepository.saveAndFlush(artikel);
        }
    }

    private void SetDummyList() {
        if(artikelRepository.count()<1)
            setDummyList();
    }
}
