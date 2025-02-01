package ma.ensa.gestioncomptebancaire.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

// Annotation pour indiquer que cette classe est une entité JPA
 @Getter @Setter
@Entity
// Annotation pour spécifier la valeur du discriminant dans la table héritée (indique que c'est un Versement)
@DiscriminatorValue("V")
public class Versement extends Operation {

    // Constructeur pour créer un versement avec une date, un montant et un compte
    public Versement(Date dateOperation, double montant, Compte compte) {
        super(dateOperation, montant, compte); // Appel du constructeur de la classe parente 'Operation'
    }

    // Constructeur par défaut
    public Versement(){
        super(); // Appel du constructeur par défaut de la classe parente 'Operation'
    }
}
