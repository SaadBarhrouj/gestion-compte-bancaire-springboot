package ma.ensa.gestioncomptebancaire.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;

/**
 * Classe représentant une opération de retrait sur un compte bancaire.
 * Cette classe hérite de la classe abstraite Operation.
 */

@Getter
@Setter
@Entity
@DiscriminatorValue("R") // Spécifie le type d'opération dans la table de la base de données (ici, 'R' pour Retrait)
public class Retrait extends Operation {

    /**
     * Constructeur spécifique pour la classe Retrait.
     *
     * @param dateOperation : date de l'opération
     * @param montant : montant du retrait
     * @param compte : compte associé à l'opération
     */
    public Retrait(Date dateOperation, double montant, Compte compte) {
        super(dateOperation, montant, compte);
    }

    // Constructeur par défaut
    public Retrait() {
        super();
    }
}
