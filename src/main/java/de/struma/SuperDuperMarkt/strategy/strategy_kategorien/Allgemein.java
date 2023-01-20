package de.struma.SuperDuperMarkt.strategy.strategy_kategorien;

import de.struma.SuperDuperMarkt.model.Artikel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
@Component
public class Allgemein implements IStrategy{

    @Override
    public boolean isRightStregie(Artikel checkArtikel) {
        return checkArtikel.getKategorie().toLowerCase().contains("Allgemein".toLowerCase());
    }

    @Override
    public Artikel validateArtikel(Artikel injectArtikel) {
        setDailyQuality(injectArtikel);
        updateTagesPreis(injectArtikel);
        return injectArtikel;
    }

    protected void updateTagesPreis(Artikel injectArtikel) {
        if(injectArtikel.getBuchungsDatum().isBefore(LocalDate.now())){
            double temp = injectArtikel.getGrundPreis()+(0.1*injectArtikel.getQualitaet());
            injectArtikel.setTagesPreis(temp);
        }
        else
            injectArtikel.setTagesPreis(injectArtikel.getGrundPreis());
    }

    protected long calulateRangeToLong(LocalDate vonDatum, LocalDate bisDatum){
        return ChronoUnit.DAYS.between(
                vonDatum,
                bisDatum);
    }
    private void setDailyQuality(Artikel injectArtikel){
        injectArtikel.setQualitaet(injectArtikel.getGrundQualitaet());
    }
}
