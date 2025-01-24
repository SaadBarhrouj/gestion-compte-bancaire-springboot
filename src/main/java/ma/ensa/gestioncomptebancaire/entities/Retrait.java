package ma.ensa.gestioncomptebancaire.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor @Getter
@Setter
@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation {
    public Retrait(Date dateOperation, double montant, Compte compte ) {
       super(dateOperation, montant, compte);
    }
}
