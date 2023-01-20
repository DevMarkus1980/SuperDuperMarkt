package de.struma.SuperDuperMarkt.strategy.strategy_kategorien;

import de.struma.SuperDuperMarkt.model.Artikel;

import java.time.LocalDate;

public class Allgemein {
    public Artikel validateArtikel(Artikel injectArtikel) {
        if(injectArtikel.getBuchungsDatum().isBefore(LocalDate.now())){
            injectArtikel.setGrundPreis(injectArtikel.getGrundPreis()+(0.1*injectArtikel.getQualitaet()));
        }
        return injectArtikel;
    }
}
