package de.struma.SuperDuperMarkt.strategy.strategy_kategorien;

import de.struma.SuperDuperMarkt.model.Artikel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@NoArgsConstructor
@Component
public class Wein extends Allgemein implements IStrategy {
    @Override
    public boolean isRightStregie(Artikel checkArtikel) {
        return checkArtikel.getKategorie().toLowerCase().contains("Wein".toLowerCase());
    }

    @Override
    public Artikel validateArtikel(Artikel injectArtikel) {

        setDailyQuality(injectArtikel);
        checkQuality(injectArtikel);
        setTagesPreisOnlyIfNotImRegal(injectArtikel);
        return injectArtikel;
    }

    private void setTagesPreisOnlyIfNotImRegal(Artikel injectArtikel) {
        if(injectArtikel.getRegalAuslegen())
            updateTagesPreis(injectArtikel);

        if(injectArtikel.getTagesPreis() == null)
            injectArtikel.setTagesPreis(injectArtikel.getGrundPreis());
    }

    private void setDailyQuality(Artikel injectArtikel) {
        long range = calulateRangeToLong(injectArtikel.getBuchungsDatum(),LocalDate.now());
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
