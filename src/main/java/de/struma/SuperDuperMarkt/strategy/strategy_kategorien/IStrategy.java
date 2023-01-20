package de.struma.SuperDuperMarkt.strategy.strategy_kategorien;

import de.struma.SuperDuperMarkt.model.Artikel;

public interface IStrategy {
    boolean isRightStregie(Artikel checkArtikel);
    Artikel validateArtikel(Artikel injectArtikel);
}