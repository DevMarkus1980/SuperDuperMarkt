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


    public List<Artikel> dumbSetter() {

        LocalDateTime setDate = LocalDateTime.now();
        List<Artikel> listForDB = new ArrayList<>();
        listForDB.add(new Artikel(1l, "Leerdamer", 32, 2.99d, "Käse", setDate.plusDays(1)));
        listForDB.add(new Artikel(1l, "Edamer", 32, 2.99d, "Käse", setDate.plusDays(1)));
        listForDB.add(new Artikel(1l, "Pizza", 32, 2.99d, "Allgemein", setDate.plusDays(1)));
        listForDB.add(new Artikel(1l, "Brot", 32, 2.99d, "Allgemein", setDate.plusDays(1)));
        listForDB.add(new Artikel(1l, "Dornfelder", 32, 2.99d, "Wein", setDate.plusDays(1)));
        listForDB.add(new Artikel(1l, "Riesling", 32, 2.99d, "Wein", setDate.plusDays(1)));

        return listForDB;
    }
}

