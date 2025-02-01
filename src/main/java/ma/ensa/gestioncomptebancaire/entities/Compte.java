package ma.ensa.gestioncomptebancaire.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Classe représentant un compte bancaire.
 * Cette classe est une entité JPA qui sera utilisée comme base pour les comptes spécifiques (ex : CompteCourant, CompteEpargne).
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_CPTE", discriminatorType = DiscriminatorType.STRING, length = 2)
public abstract class Compte implements Serializable {

    // Code unique du compte bancaire
    @Id
    private String codeCompte;

    // Date de création du compte
    private Date dateCreation;

    // Solde du compte
    private double solde;

    // Client auquel le compte est associé
    @ManyToOne
    @JoinColumn(name = "CODE_CLI")
    private Client client;

    // Liste des opérations effectuées sur ce compte
    @OneToMany(mappedBy = "compte", fetch = FetchType.LAZY)
    private Collection<Operation> operations;

    // Constructeur principal avec paramètres
    public Compte(String codeCompte, Date dateCreation, double solde, Client client) {
        this.codeCompte = codeCompte;
        this.dateCreation = dateCreation;
        this.solde = solde;
        this.client = client;
    }

    // Constructeur par défaut
    public Compte() {
        super();
    }

    // Méthodes getter et setter générées par Lombok
    public String getCodeCompte() {
        return codeCompte;
    }

    public void setCodeCompte(String codeCompte) {
        this.codeCompte = codeCompte;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Client getClient() {
        return client;
    }

}
