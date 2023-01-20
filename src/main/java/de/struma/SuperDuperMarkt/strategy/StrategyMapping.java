package de.struma.SuperDuperMarkt.strategy;

import de.struma.SuperDuperMarkt.model.Artikel;
import de.struma.SuperDuperMarkt.strategy.strategy_kategorien.IStrategie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class StrategyMapping {

    final List<IStrategie> strategyWorker;
    @Autowired(required = false)
    public StrategyMapping(List<IStrategie> strategyWorker) {
        this.strategyWorker = strategyWorker;
    }

    public Artikel validateArtikelWithStrategie(Artikel validierenderArtikel){

        for (IStrategie strategie: strategyWorker) {
            if (strategie.isRightStregie(validierenderArtikel)){
                return strategie.validateArtikel(validierenderArtikel);
            }
        }
        return validierenderArtikel;
    }

}
