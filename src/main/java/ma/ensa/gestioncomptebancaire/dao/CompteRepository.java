package ma.ensa.gestioncomptebancaire.dao;

import ma.ensa.gestioncomptebancaire.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, String> {
}
