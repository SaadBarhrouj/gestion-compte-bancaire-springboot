package ma.ensa.gestioncomptebancaire.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;

/**
 * Classe représentant un compte d'épargne.
 * Un compte d'épargne permet à un client de déposer de l'argent et de recevoir des intérêts calculés en fonction d'un taux.
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@DiscriminatorValue("CE")  // Spécifie le type de compte pour le distinguo dans la table unique
public class CompteEpargne extends Compte {

    /**
     * Le taux d'intérêt appliqué au solde du compte d'épargne.
     * Ce taux permet de calculer les intérêts que le client recevra sur son solde.
     */
    private double taux;

    /**
     * Constructeur de la classe CompteEpargne.
     *
     * @param codeCompte : Le code unique du compte d'épargne
     * @param date : La date de création du compte
     * @param solde : Le solde initial du compte
     * @param client : Le client associé à ce compte
     * @param taux : Le taux d'intérêt applicable à ce compte
     */
    public CompteEpargne(String codeCompte, Date date, double solde, Client client, double taux) {
        super(codeCompte, date, solde, client);  // Appel du constructeur de la classe parent Compte
        this.taux = taux;
    }

    /**
     * Constructeur par défaut.
     * Nécessaire pour la création d'instances par JPA (Java Persistence API).
     */
    public CompteEpargne() {
        super();
    }

    /**
     * Retourne le taux d'intérêt du compte d'épargne.
     *
     * @return le taux d'intérêt
     */
    public double getTaux() {
        return taux;
    }
}
