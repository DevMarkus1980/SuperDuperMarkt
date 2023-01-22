package de.struma.SuperDuperMarkt.strategy.strategy_kategorien;

import de.struma.SuperDuperMarkt.model.Artikel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/*
TODO: Regel für dieses Muster
    •	Min. Qualität von 20
    •	Verfallsdatum nicht unter 5 & Verfallsdatum länger als 20
    •	-1 Punkt Qualität je Tag
    •	Tages aktuellen Preis
 */

@NoArgsConstructor
@Component
public class Wurst extends Allgemein implements IStrategy {

    @Override
    public boolean isRightStrategy(Artikel checkArtikel) {
        return checkArtikel.getKategorie().toLowerCase().contains("Wurst".toLowerCase());
    }
    @Override
    public Artikel validateArtikel(Artikel injectArtikel, LocalDate validateDatum) {

        setDailyQuality(injectArtikel, validateDatum);
        checkQuality(injectArtikel);
        checkVerfallsDatum(injectArtikel,validateDatum);
        updateTagesPreis(injectArtikel, validateDatum);
        return injectArtikel;
    }
    private void setDailyQuality(Artikel injectArtikel, LocalDate validateDatum) {
        if(calulateRangeToLong(injectArtikel.getBuchungsDatum(), validateDatum) >0){
            injectArtikel.setQualitaet((int) (
                    injectArtikel.getGrundQualitaet()-
                    calulateRangeToLong(injectArtikel.getBuchungsDatum(), validateDatum)));
        }
        else
            injectArtikel.setQualitaet(injectArtikel.getGrundQualitaet());
    }
    private void checkQuality(Artikel injectArtikel){
        injectArtikel.setRegalAuslegen(injectArtikel.getQualitaet() >= 20);
    }
    private void checkVerfallsDatum(Artikel injectArtikel, LocalDate validateDatum) {
        long range = calulateRangeToLong(validateDatum, injectArtikel.getVerfallsDatum());
        if( range<5L || range>20 )
            injectArtikel.setRegalAuslegen(false);
    }

}
