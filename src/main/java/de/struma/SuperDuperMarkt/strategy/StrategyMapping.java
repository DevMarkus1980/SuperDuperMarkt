package de.struma.SuperDuperMarkt.strategy;

import de.struma.SuperDuperMarkt.model.Artikel;
import de.struma.SuperDuperMarkt.strategy.strategy_kategorien.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class StrategyMapping {

    final List<IStrategy> strategyWorker;
    @Autowired(required = false)
    public StrategyMapping(List<IStrategy> strategyWorker) {
        this.strategyWorker = strategyWorker;
    }

    public Artikel validateArtikelWithStrategie(Artikel validierenderArtikel){

        for (IStrategy strategie: strategyWorker) {
            if (strategie.isRightStregie(validierenderArtikel)){
                return strategie.validateArtikel(validierenderArtikel);
            }
        }
        return validierenderArtikel;
    }

}
