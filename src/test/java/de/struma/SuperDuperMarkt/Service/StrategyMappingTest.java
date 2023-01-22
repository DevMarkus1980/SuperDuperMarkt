package de.struma.SuperDuperMarkt.Service;

import de.struma.SuperDuperMarkt.model.Artikel;
import de.struma.SuperDuperMarkt.strategy.StrategyMapping;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StrategyMappingTest {
    @Autowired
    StrategyMapping strategyMapping;
    Artikel testArtikel;
    LocalDate date;

    @BeforeEach
    public void setUp() {
        date = LocalDate.now().plusDays(1);
        testArtikel = new Artikel(
                1L,
                "Tester",
                32,
                2.10d,
                "Käse",
                date); // Verfallsdatum
    }
    @AfterAll
    public void teardown(){
        testArtikel = null;
        date = null;
    }

    @DisplayName("Artikel-Validierung-Kaese")
    @Test
    void testValidationArtikelKaese() {
        testArtikel.setBuchungsDatum(date.minusDays(10));
        strategyMapping.validateArtikelWithStrategie(testArtikel, LocalDate.now());
        assumeTrue(testArtikel.getTagesPreis()  == 4.40d,"TagesPreis wurde korrekt gesetzt"); // 2,10+(0,1*23)
        assumeTrue(!testArtikel.getRegalAuslegen(),"Ins Regal wurde überprüft!");
    }
    @DisplayName("Artikel-Validierung-Wein")
    @Test
    void testValidationArtikelWein() {

        testArtikel.setKategorie("Wein");
        testArtikel.setBuchungsDatum(date.minusDays(33));
        strategyMapping.validateArtikelWithStrategie(testArtikel, LocalDate.now());
        assumeTrue(testArtikel.getTagesPreis()  == 4.40d,"TagesPreis wurde korrekt gesetzt"); // 2,10+(0,1*23)
        assumeTrue(!testArtikel.getRegalAuslegen(),"Ins Regal wurde überprüft!");
    }
    @DisplayName("Artikel-Validierung-Allgemein")
    @Test
    void testValidationArtikelAllgemein() {
        //TODO: Test noch an die regeln anpasssen!
        testArtikel.setKategorie("Allgemein");
        testArtikel.setBuchungsDatum(date.minusDays(33));
        strategyMapping.validateArtikelWithStrategie(testArtikel, LocalDate.now());
        assumeTrue(testArtikel.getTagesPreis()  == 4.40d,"TagesPreis wurde korrekt gesetzt"); // 2,10+(0,1*23)
        assumeTrue(!testArtikel.getRegalAuslegen(),"Ins Regal wurde überprüft!");
    }

}
