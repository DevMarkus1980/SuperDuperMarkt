package de.struma.SuperDuperMarkt.service;

import de.struma.SuperDuperMarkt.model.Artikel;
import de.struma.SuperDuperMarkt.repository.ArtikelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ArtikelService {
    ArtikelRepository artikelRepository;

    public ArtikelService(ArtikelRepository artikelRepository){
        this.artikelRepository = artikelRepository;
    }

    public List<Artikel> getArtikelList(){
        return artikelRepository.findAll();
    }

    public void setDummyList(){
        artikelRepository.saveAll(new Artikel().dummyList());
    }

}
