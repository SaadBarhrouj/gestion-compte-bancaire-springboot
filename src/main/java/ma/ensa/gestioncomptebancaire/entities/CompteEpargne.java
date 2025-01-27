package ma.ensa.gestioncomptebancaire.entities;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;

 @AllArgsConstructor @Getter
@Setter @ToString
@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte {
    private double taux;
    public CompteEpargne(String codeCompte, Date date, double solde, Client client, double taux) {
        super(codeCompte, date, solde, client);
        this.taux = taux;
    }
    public CompteEpargne() {

        super();
    }

     public double getTaux() {
         return taux;
     }
 }
