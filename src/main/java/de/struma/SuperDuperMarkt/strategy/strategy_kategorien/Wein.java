package de.struma.SuperDuperMarkt.strategy.strategy_kategorien;

import de.struma.SuperDuperMarkt.model.Artikel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/*
TODO: Regel für dieses Muster
    •	Qualität über 0
    •	Alle 10 Tage im Bestand +1 Punk Qualität
    •	Fixen Preis nach dem einmal im Regal
 */

@NoArgsConstructor
@Component
public class Wein extends Allgemein implements IStrategy {
    @Override
    public boolean isRightStrategy(Artikel checkArtikel) {
        return checkArtikel.getKategorie().toLowerCase().contains("Wein".toLowerCase());
    }

    @Override
    public Artikel validateArtikel(Artikel injectArtikel, LocalDate validateDatum) {

        setDailyQuality(injectArtikel, validateDatum);
        checkQuality(injectArtikel);
        setTagesPreisOnlyIfNotImRegal(injectArtikel, validateDatum);
        return injectArtikel;
    }

    private void setTagesPreisOnlyIfNotImRegal(Artikel injectArtikel, LocalDate validateDatum) {

        if(injectArtikel.getRegalAuslegen())
            updateTagesPreis(injectArtikel, validateDatum);

        if(injectArtikel.getTagesPreis() == null)
            injectArtikel.setTagesPreis(injectArtikel.getGrundPreis());
    }

    private void setDailyQuality(Artikel injectArtikel, LocalDate validateDatum) {
        long range = calulateRangeToLong(injectArtikel.getBuchungsDatum(),validateDatum);
        range = range / 10;
        int factor = (int) Math.floor(range);
        if(factor<50)
            injectArtikel.setQualitaet(injectArtikel.getGrundQualitaet() + factor);
        else
            injectArtikel.setQualitaet(injectArtikel.getGrundQualitaet() + 50);
    }

    private void checkQuality(Artikel injectArtikel){
        if(injectArtikel.getQualitaet()<1)
            injectArtikel.setRegalAuslegen(false);
    }

}
