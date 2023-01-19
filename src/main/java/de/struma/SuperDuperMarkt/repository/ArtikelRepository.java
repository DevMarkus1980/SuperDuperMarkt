package de.struma.SuperDuperMarkt.repository;

import de.struma.SuperDuperMarkt.model.Artikel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtikelRepository extends JpaRepository<Artikel, Long> {

}
