package ma.ensa.gestioncomptebancaire.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;

/**
 * Classe représentant un compte courant.
 * Un compte courant est un type de compte bancaire qui permet des découverts (crédit autorisé).
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@DiscriminatorValue("CC")  // Spécifie le type de compte pour le distinguo dans la table unique
public class CompteCourant extends Compte {

    /**
     * Le montant de découvert autorisé pour ce compte courant.
     * Cela représente la limite de crédit que le client peut utiliser.
     */
    private double decouvert;

    /**
     * Constructeur de la classe CompteCourant.
     *
     * @param codeCompte : Le code unique du compte courant
     * @param date : La date de création du compte
     * @param solde : Le solde initial du compte
     * @param client : Le client associé à ce compte
     * @param decouvert : Le montant du découvert autorisé pour ce compte
     */
    public CompteCourant(String codeCompte, Date date, double solde, Client client, double decouvert) {
        super(codeCompte, date, solde, client);  // Appel du constructeur de la classe parent Compte
        this.decouvert = decouvert;
    }

    /**
     * Retourne le montant du découvert autorisé pour ce compte.
     *
     * @return le montant du découvert
     */
    public double getDecouvert() {
        return decouvert;
    }
    public CompteCourant(){
        super();
    }

}
