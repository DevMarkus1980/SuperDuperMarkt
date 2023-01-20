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

    private void setTagesPreis(Artikel injectArtikel) {
        if(injectArtikel.getBuchungsDatum().isBefore(LocalDate.now())){
            injectArtikel.setTagesPreis(injectArtikel.getGrundPreis()+(0.1*injectArtikel.getQualitaet()));
        }
    }

    private void setDailyQuality(Artikel injectArtikel) {
        if(checkRangeBetweenNowAndVerfallsDatum(injectArtikel) >0){
            Integer first = injectArtikel.getGrundQualitaet();
            Integer second = (int) checkRangeBetweenNowAndVerfallsDatum(injectArtikel);

            injectArtikel.setQualitaet((int) (injectArtikel.getGrundQualitaet()-
                                checkRangeBetweenNowAndVerfallsDatum(injectArtikel)));
        }

    }

    private void checkQuality(Artikel injectArtikel){
        Integer tester = injectArtikel.getQualitaet();
        if(injectArtikel.getQualitaet()<30)
            injectArtikel.setRegalAuslegen(false);
    }

    private void checkVerfallsDatum(Artikel injectArtikel) {
        if(     checkRangeBetweenNowAndVerfallsDatum(injectArtikel)<50 ||
                checkRangeBetweenNowAndVerfallsDatum(injectArtikel)>100)
            injectArtikel.setRegalAuslegen(false);
    }
    private long checkRangeBetweenNowAndVerfallsDatum(Artikel injectedArtikel){
        return ChronoUnit.DAYS.between(
                injectedArtikel.getVerfallsDatum(),
                LocalDate.now());
    }
}
