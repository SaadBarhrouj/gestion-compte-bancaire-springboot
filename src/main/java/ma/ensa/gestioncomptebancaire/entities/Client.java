package ma.ensa.gestioncomptebancaire.entities;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Client implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private String nom;
    private String email;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Collection<Compte> comptes;

    public Client(){

    }
    public Client(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }
    public String getNom() {
        return nom;
    }
}
