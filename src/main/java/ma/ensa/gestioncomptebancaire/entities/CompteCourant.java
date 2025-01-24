package ma.ensa.gestioncomptebancaire.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor @Getter
@Setter @ToString
@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {
    private double decouvert;

    public CompteCourant() {

    }

    public CompteCourant(String codeCompte, Date date, double solde , Client client, double decouvert) {
        super(codeCompte, date, solde, client);
        this.decouvert = decouvert;
    }

    public double getDecouvert() {
        return decouvert;
    }
}