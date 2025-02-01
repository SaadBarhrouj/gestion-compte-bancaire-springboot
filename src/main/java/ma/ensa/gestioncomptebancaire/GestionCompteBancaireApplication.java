package ma.ensa.gestioncomptebancaire;

import ma.ensa.gestioncomptebancaire.dao.ClientRepository;
import ma.ensa.gestioncomptebancaire.dao.CompteRepository;
import ma.ensa.gestioncomptebancaire.entities.Client;
import ma.ensa.gestioncomptebancaire.entities.Compte;
import ma.ensa.gestioncomptebancaire.entities.CompteCourant;
import ma.ensa.gestioncomptebancaire.entities.CompteEpargne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class GestionCompteBancaireApplication implements CommandLineRunner {

    // Injection des repositories pour accéder aux entités
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CompteRepository compteRepository;

    // Méthode main pour démarrer l'application
    public static void main(String[] args) {
        SpringApplication.run(GestionCompteBancaireApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Création de clients et de comptes au démarrage de l'application

        // Client 1
        Client c1 = clientRepository.save(new Client("saad", "saad.barhrouj1@gmail.com"));
        System.out.println("Client 1 enregistré : " + c1);

        // Client 2
        Client c2 = clientRepository.save(new Client("Mohamed", "saad.barhrouj2@gmail.com"));
        System.out.println("Client 2 enregistré : " + c2);

        // Compte Courant pour Client 1
        Compte cpte1 = compteRepository.save(new CompteCourant("c1", new Date(), 900.00, c1, 6000));
        System.out.println("Compte Courant 1 enregistré : " + cpte1);

        // Compte Épargne pour Client 2
        Compte cpte2 = compteRepository.save(new CompteEpargne("c2", new Date(), 900.00, c2, 6000.00));
        System.out.println("Compte Épargne 2 enregistré : " + cpte2);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
