package de.struma.SuperDuperMarkt.strategy.strategy_kategorien;

import de.struma.SuperDuperMarkt.model.Artikel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@NoArgsConstructor
@Component
public class Kaese extends Allgemein implements IStrategy {
    @Override
    public boolean isRightStregie(Artikel checkArtikel) {
        return checkArtikel.getKategorie().toLowerCase().contains("Käse".toLowerCase());
    }

    @Override
    public Artikel validateArtikel(Artikel injectArtikel) {

        setDailyQuality(injectArtikel);
        checkQuality(injectArtikel);
        checkVerfallsDatum(injectArtikel);
        updateTagesPreis(injectArtikel);
        return injectArtikel;
    }

    private void setDailyQuality(Artikel injectArtikel) {
        if(calulateRangeToLong(injectArtikel.getBuchungsDatum(), LocalDate.now()) >0){
            injectArtikel.setQualitaet((int) (
                    injectArtikel.getGrundQualitaet()-
                    calulateRangeToLong(injectArtikel.getBuchungsDatum(), LocalDate.now())));
        }
        else
            injectArtikel.setQualitaet(injectArtikel.getGrundQualitaet());
    }


    private void checkQuality(Artikel injectArtikel){
        if(injectArtikel.getQualitaet()<30)
            injectArtikel.setRegalAuslegen(false);
    }

    private void checkVerfallsDatum(Artikel injectArtikel) {
        long range = calulateRangeToLong(LocalDate.now(), injectArtikel.getVerfallsDatum());
        if( range<50L || range>100 )
            injectArtikel.setRegalAuslegen(false);
    }

}
