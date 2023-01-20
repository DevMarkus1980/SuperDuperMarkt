package de.struma.SuperDuperMarkt.service;

import de.struma.SuperDuperMarkt.model.Artikel;
import de.struma.SuperDuperMarkt.repository.ArtikelRepository;
import de.struma.SuperDuperMarkt.strategy.StrategyMapping;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
        if(artikelRepository.count()<1)
            setDummyList();
        validateArtikel();
        return artikelRepository.findAll();
    }

    private void setDummyList(){
        artikelRepository.saveAllAndFlush(new Artikel().dummyList());
    }

    public void validateArtikel(){
        for (Artikel artikel:artikelRepository.findAll()) {
            strategyMapping.validateArtikelWithStrategie(artikel);
            artikelRepository.saveAndFlush(artikel);


        }
    }

}
