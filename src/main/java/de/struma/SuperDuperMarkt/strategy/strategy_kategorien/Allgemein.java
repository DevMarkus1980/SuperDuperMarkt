package de.struma.SuperDuperMarkt.strategy.strategy_kategorien;

import de.struma.SuperDuperMarkt.model.Artikel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/*
TODO: Regel für dieses Muster
    •	Tagespreis = Tagespreis * (0,10€ * Qualität)
    •	Grundpreis und Tagespreis

 */
@Component
public class Allgemein implements IStrategy{

    @Override
    public boolean isRightStregie(Artikel checkArtikel) {
        return checkArtikel.getKategorie().toLowerCase().contains("Allgemein".toLowerCase());
    }

    @Override
    public Artikel validateArtikel(Artikel injectArtikel, LocalDate validateDatum) {
        setDailyQuality(injectArtikel, validateDatum);
        updateTagesPreis(injectArtikel, validateDatum);
        return injectArtikel;
    }

    private void setDailyQuality(Artikel injectArtikel, LocalDate validateDatum){
        injectArtikel.setQualitaet(injectArtikel.getGrundQualitaet());
    }

    protected void updateTagesPreis(Artikel injectArtikel, LocalDate validateDatum) {
        double temp = injectArtikel.getGrundPreis()+(0.1*injectArtikel.getQualitaet());
        injectArtikel.setTagesPreis(temp);
    }

    protected long calulateRangeToLong(LocalDate vonDatum, LocalDate bisDatum){
        return ChronoUnit.DAYS.between(
                vonDatum,
                bisDatum);
    }
}
