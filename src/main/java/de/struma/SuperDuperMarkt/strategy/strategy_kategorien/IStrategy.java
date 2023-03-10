package de.struma.SuperDuperMarkt.strategy.strategy_kategorien;

import de.struma.SuperDuperMarkt.model.Artikel;

import java.time.LocalDate;

public interface IStrategy {
    boolean isRightStrategy(Artikel checkArtikel);
    Artikel validateArtikel(Artikel injectArtikel, LocalDate validateDatum);
}
