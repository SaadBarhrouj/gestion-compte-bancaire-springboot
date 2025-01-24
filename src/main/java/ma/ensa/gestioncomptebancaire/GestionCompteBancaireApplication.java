package ma.ensa.gestioncomptebancaire;

import ma.ensa.gestioncomptebancaire.dao.ClientRepository;
import ma.ensa.gestioncomptebancaire.dao.CompteRepository;
import ma.ensa.gestioncomptebancaire.dao.OperationRepository;
import ma.ensa.gestioncomptebancaire.entities.*;
import ma.ensa.gestioncomptebancaire.metier.BanqueMetierImpl;
import ma.ensa.gestioncomptebancaire.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Date;

@SpringBootApplication
public class GestionCompteBancaireApplication implements CommandLineRunner {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
   private IBanqueMetier ibanqueMetier;
    @Autowired
    private BanqueMetierImpl banqueMetierImpl;

    public static void main(String[] args)  {
      /*  methode 1 pour demarer l'application :
         1- creer un objet de type ApplicationContext
       ApplicationContext ctx= SpringApplication.run(GestionCompteBancaireApplication.class, args);
        1- demander a spring  de charger un objet de type  ClientRepository
        ClientRepository clientRepository = ctx.getBean(ClientRepository.class);
        clientRepository.save(.....)
        */


      //  methode 2:...: implementer CommandLine , declarer objet de type ClientRepository , redefinir la ,ethode run()
        SpringApplication.run(GestionCompteBancaireApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        Client c1 = clientRepository.save(new Client("saad1", "saad.barhrouj1@gmail.com"));
        System.out.println("Client 1 enregistré : " + c1);

        Client c2 = clientRepository.save(new Client("saad2", "saad.barhrouj2@gmail.com"));
        System.out.println("Client 2 enregistré : " + c2);

        Compte cpte1 = compteRepository.save(new CompteCourant("c1", new Date(), 900.00,c1, 6000));
        System.out.println("Compte Courant 1 enregistré : " + cpte1);

        Compte cpte2 = compteRepository.save(new CompteEpargne("c2", new Date(), 900.00, c2, 6000.00));
        System.out.println("Compte Épargne 2 enregistré : " + cpte2);

        operationRepository.save(new Versement(new Date(), 800, cpte1));
        operationRepository.save(new Versement(new Date(), 900, cpte1));
        operationRepository.save(new Versement(new Date(), 800, cpte1));
        operationRepository.save(new Retrait(new Date(), 1000, cpte1));
        System.out.println("Opérations pour Compte 1 enregistrées");

        operationRepository.save(new Versement(new Date(), 800, cpte2));
        operationRepository.save(new Versement(new Date(), 900, cpte2));
        operationRepository.save(new Versement(new Date(), 800, cpte2));
        operationRepository.save(new Retrait(new Date(), 1000, cpte2));
        System.out.println("Opérations pour Compte 2 enregistrées");
        banqueMetierImpl.verser("c1",100.00);

    }
}
