package ma.ensa.gestioncomptebancaire.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor @Getter
@Setter
@Entity
@DiscriminatorValue("V")
public class Versement extends Operation {
    public Versement(Date dateOperation, double montant, Compte compte ) {
        super(dateOperation, montant, compte);
    }
}
