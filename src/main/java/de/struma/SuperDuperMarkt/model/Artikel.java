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

    @NonNull @Id @GeneratedValue(strategy = GenerationType.AUTO)
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
        listForDB.add(new Artikel(1L,"Leerdamer", 50, 2.99d, "Käse", setDate.plusDays(50)));
        listForDB.add(new Artikel(2L,"Edamer", 60, 2.99d, "Käse", setDate.plusDays(70)));
        listForDB.add(new Artikel(3L,"Pizza", 12, 2.99d, "Allgemein", setDate.plusDays(30)));
        listForDB.add(new Artikel(4L,"Brot", 42, 2.99d, "Allgemein", setDate.plusDays(40)));
        listForDB.add(new Artikel(5L,"Dornfelder", 52, 2.99d, "Wein", setDate.plusDays(50)));
        listForDB.add(new Artikel(6L,"Riesling", 32, 2.99d, "Wein", setDate.plusDays(63)));

        return listForDB;
    }
}

