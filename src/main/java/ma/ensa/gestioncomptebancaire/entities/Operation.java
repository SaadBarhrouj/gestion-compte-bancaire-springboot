package ma.ensa.gestioncomptebancaire.entities;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor @Getter
@Setter @ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_OP",discriminatorType=DiscriminatorType.STRING, length = 1)
public abstract class Operation implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;
    private Date dateOperation;
    private double  montant;
    @ManyToOne
    @JoinColumn(name = "CODE_CPTE")
    private Compte compte ;

    public Operation() {
    }

    public Operation(Date dateOperation, double montant, Compte compte ) {
        this.dateOperation = dateOperation;
        this.montant = montant;
        this.compte = compte;
    }
}
