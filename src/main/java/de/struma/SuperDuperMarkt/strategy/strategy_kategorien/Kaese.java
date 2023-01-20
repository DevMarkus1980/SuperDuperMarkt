package de.struma.SuperDuperMarkt.strategy.strategy_kategorien;

import de.struma.SuperDuperMarkt.model.Artikel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
@NoArgsConstructor
@Component
public class Kaese implements IStrategie {
    @Override
    public boolean isRightStregie(Artikel checkArtikel) {
        return checkArtikel.getKategorie().toLowerCase().contains("Käse".toLowerCase());
    }

    @Override
    public Artikel validateArtikel(Artikel injectArtikel) {

        setDailyQuality(injectArtikel);
        checkQuality(injectArtikel);
        checkVerfallsDatum(injectArtikel);
        setTagesPreis(injectArtikel);
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

    private void setTagesPreis(Artikel injectArtikel) {
        if(injectArtikel.getBuchungsDatum().isBefore(LocalDate.now())){
            double temp = injectArtikel.getGrundPreis()+(0.1*injectArtikel.getQualitaet());
            injectArtikel.setTagesPreis(temp);
        }
        else
            injectArtikel.setTagesPreis(injectArtikel.getGrundPreis());
    }

    private void checkQuality(Artikel injectArtikel){
        Integer tester = injectArtikel.getQualitaet();
        if(injectArtikel.getQualitaet()<30)
            injectArtikel.setRegalAuslegen(false);
    }

    private void checkVerfallsDatum(Artikel injectArtikel) {
        long range = calulateRangeToLong(LocalDate.now(), injectArtikel.getVerfallsDatum());
        if( range<50L || range>100 )
            injectArtikel.setRegalAuslegen(false);
    }
    private long calulateRangeToLong(LocalDate vonDatum, LocalDate bisDatum){
        return ChronoUnit.DAYS.between(
                vonDatum,
                bisDatum);
    }
}
