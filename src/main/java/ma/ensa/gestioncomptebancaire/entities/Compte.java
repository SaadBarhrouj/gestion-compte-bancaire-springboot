package ma.ensa.gestioncomptebancaire.entities;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@NoArgsConstructor @AllArgsConstructor @Getter
@Setter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_CPTE",discriminatorType=DiscriminatorType.STRING, length = 2)
public abstract class Compte implements Serializable {
@Id
    private String codeCompte ;
    private Date dateCreation ;
    private double solde;
    @ManyToOne
    @JoinColumn(name ="CODE_CLI")
    private Client client;
    @OneToMany(mappedBy = "compte", fetch = FetchType.LAZY)
    private Collection<Operation> opertions ;


    public Compte(String codeCompte, Date dateCreation, double solde, Client client) {
        this.codeCompte = codeCompte;
        this.dateCreation = dateCreation;
        this.solde = solde;
        this.client = client;

    }

    public Compte() {
        super();
    }

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


}
