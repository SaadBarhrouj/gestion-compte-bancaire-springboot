package ma.ensa.gestioncomptebancaire.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Classe représentant une opération bancaire (ex : versement, retrait).
 * Cette classe est abstraite et servira de base pour des types spécifiques d'opérations.
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_OP", discriminatorType = DiscriminatorType.STRING, length = 1)
public abstract class Operation implements Serializable {

    // Numéro unique de l'opération
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    // Date de l'opération
    private Date dateOperation;

    // Montant de l'opération
    private double montant;

    // Compte associé à l'opération
    @ManyToOne
    @JoinColumn(name = "CODE_CPTE")
    private Compte compte;

    // Constructeur par défaut
    public Operation() {
    }

    /**
     * Constructeur de l'opération avec les informations essentielles.
     *
     * @param dateOperation : date de l'opération
     * @param montant : montant de l'opération
     * @param compte : compte associé à l'opération
     */
    public Operation(Date dateOperation, double montant, Compte compte) {
        this.dateOperation = dateOperation;
        this.montant = montant;
        this.compte = compte;
    }

    // Méthodes getter et setter générées par Lombok (pas besoin de les redéfinir ici)

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    /**
     * Récupère le type d'opération en fonction de la sous-classe de l'opération.
     *
     * @return le nom de la sous-classe de l'opération (ex : "Versement", "Retrait")
     */
    public String getTypeOperation() {
        return this.getClass().getSimpleName();
    }
}
