package ma.ensa.gestioncomptebancaire.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;
 // Lombok génère automatiquement un constructeur sans arguments
@AllArgsConstructor // Lombok génère automatiquement un constructeur avec tous les arguments
@Getter             // Lombok génère automatiquement les getters pour tous les champs
@Setter             // Lombok génère automatiquement les setters pour tous les champs
@ToString           // Lombok génère automatiquement la méthode toString pour afficher les objets

@Entity            // Cette classe est une entité JPA, elle sera mappée à une table en base de données
public class Client implements Serializable {

    @Id // La clé primaire de l'entité
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La clé primaire est générée automatiquement par la base de données
    private Long code;  // Identifiant unique du client

    private String nom;  // Nom du client
    private String email; // Email du client

    //Getters and Setters
    public Long getCode() {
        return code;
    }
    public void setCode(Long code) {
        this.code = code;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    // Relation OneToMany avec la classe Compte
    // 'mappedBy' fait référence à l'attribut dans la classe Compte qui gère la relation inverse
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Collection<Compte> comptes; // Liste des comptes associés au client

    public Client(){}

    public Client(String nom, String email) { this.nom = nom; this.email = email; }

}
