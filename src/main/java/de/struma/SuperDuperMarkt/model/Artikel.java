package de.struma.SuperDuperMarkt.model;

import de.struma.SuperDuperMarkt.repository.ArtikelRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artikel {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String bezeichnung;
    private Integer qualitaet;
    private Double preis;
    private String kategorie;
    @DateTimeFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private LocalDateTime verfallsDatum;



    //    Diese Methode dient nur zum Testen
    public List<Artikel> dumbSetter() {

        LocalDateTime setDate = LocalDateTime.now();
        List<Artikel> listForDB = new ArrayList<>();
        listForDB.add(new Artikel(1L, "Leerdamer", 32, 2.99d, "Käse", setDate.plusDays(1)));
        listForDB.add(new Artikel(2L, "Edamer", 32, 2.99d, "Käse", setDate.plusDays(2)));
        listForDB.add(new Artikel(3L, "Pizza", 32, 2.99d, "Allgemein", setDate.plusDays(3)));
        listForDB.add(new Artikel(4L, "Brot", 32, 2.99d, "Allgemein", setDate.plusDays(4)));
        listForDB.add(new Artikel(5L, "Dornfelder", 32, 2.99d, "Wein", setDate.plusDays(5)));
        listForDB.add(new Artikel(6L, "Riesling", 32, 2.99d, "Wein", setDate.plusDays(6)));

        return listForDB;
    }
}

