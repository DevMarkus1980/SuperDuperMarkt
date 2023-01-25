package de.struma.SuperDuperMarkt.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String bezeichnung;
    @NonNull
    private Integer grundQualitaet;
    private Integer qualitaet;
    @NonNull
    private Double grundPreis;
    private Double tagesPreis;
    @NonNull
    private String kategorie;
    private Boolean regalAuslegen = true;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NonNull
    private LocalDate verfallsDatum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private
    LocalDate buchungsDatum = LocalDate.now();



    //    Diese Methode dient nur zum Testen
    public List<Artikel> dummyList() {

        LocalDate setDate = LocalDate.now();
        List<Artikel> listForDB = new ArrayList<>();
        listForDB.add(new Artikel("Valider-Käse", 30, 2.99d, "Käse", setDate.plusDays(51)));
        listForDB.add(new Artikel("Invalide-Qualität-Käse", 29, 2.99d, "Käse", setDate.plusDays(51)));
        listForDB.add(new Artikel("Invalide-MHD-Käse", 30, 2.99d, "Käse", setDate.plusDays(49)));

        listForDB.add(new Artikel("Valide-Allgemein", 10, 2.00d, "Allgemein", setDate.plusDays(30)));
        listForDB.add(new Artikel("Valide-Allgemein", 10, 2.00d, "Allgemein", setDate.plusDays(30)));

        listForDB.add(new Artikel("Valide-Wein", 52, 2.99d, "Wein", setDate.plusDays(50)));
        listForDB.add(new Artikel("Invalider-Wein", 0, 2.99d, "Wein", setDate.plusDays(63)));

        return listForDB;
    }
}

