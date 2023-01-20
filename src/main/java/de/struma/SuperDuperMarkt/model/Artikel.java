package de.struma.SuperDuperMarkt.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Artikel {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id private Long id;
    @NonNull private String bezeichnung;
    @NonNull private Integer qualitaet;
    @NonNull private Double preis;
    @NonNull private String kategorie;
    private Boolean regalAuslegen = true;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NonNull private LocalDate verfallsDatum;


    //    Diese Methode dient nur zum Testen
    public List<Artikel> dummyList() {

        LocalDate setDate = LocalDate.now();
        List<Artikel> listForDB = new ArrayList<>();
        listForDB.add(new Artikel("Leerdamer", 32, 2.99d, "Käse", setDate.plusDays(1)));
        listForDB.add(new Artikel("Edamer", 32, 2.99d, "Käse", setDate.plusDays(2)));
        listForDB.add(new Artikel("Pizza", 32, 2.99d, "Allgemein", setDate.plusDays(3)));
        listForDB.add(new Artikel("Brot", 32, 2.99d, "Allgemein", setDate.plusDays(4)));
        listForDB.add(new Artikel("Dornfelder", 32, 2.99d, "Wein", setDate.plusDays(5)));
        listForDB.add(new Artikel("Riesling", 32, 2.99d, "Wein", setDate.plusDays(6)));

        return listForDB;
    }
}

