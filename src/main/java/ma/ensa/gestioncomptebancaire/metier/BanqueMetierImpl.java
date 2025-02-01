package ma.ensa.gestioncomptebancaire.metier;

import ma.ensa.gestioncomptebancaire.dao.ClientRepository;
import ma.ensa.gestioncomptebancaire.dao.OperationRepository;
import ma.ensa.gestioncomptebancaire.entities.*;

import jakarta.transaction.Transactional;
import ma.ensa.gestioncomptebancaire.dao.CompteRepository;
import ma.ensa.gestioncomptebancaire.entities.Compte;
import ma.ensa.gestioncomptebancaire.entities.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {

    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private ClientRepository clientRepository;

    // Méthode pour consulter un compte par son code
    @Override
    public Compte consulterCompte(String codeCpte) {
        // Recherche le compte dans la base de données, lève une exception si non trouvé
        return compteRepository.findById(codeCpte)
                .orElseThrow(() -> new RuntimeException("Le compte avec le code " + codeCpte + " n'a pas été trouvé dans la base de données."));
    }

    // Méthode pour effectuer un versement sur un compte
    @Override
    public void verser(String codeCpte, double montant) {
        // Récupère le compte
        Compte cp = consulterCompte(codeCpte);
        // Crée un objet Versement
        Versement r = new Versement(new Date(), montant, cp);
        // Sauvegarde l'opération dans le repository
        operationRepository.save(r);
        // Met à jour le solde du compte
        cp.setSolde(cp.getSolde() + montant);
        // Sauvegarde à nouveau le compte avec le solde mis à jour
        compteRepository.save(cp);
    }

    // Méthode pour effectuer un retrait d'un compte
    @Override
    public void retirer(String codeCpte, double montant) {
        // Récupère le compte
        Compte cp = consulterCompte(codeCpte);
        double facilitesCaisse = 0;

        // Si le compte est un compte courant, récupérer la facilité de caisse (découvert autorisé)
        if (cp instanceof CompteCourant)
            facilitesCaisse = ((CompteCourant) cp).getDecouvert();

        // Vérifie si le solde du compte, y compris les facilités de caisse, est suffisant pour le retrait
        if (facilitesCaisse + cp.getSolde() < montant)
            throw new RuntimeException("Le montant demandé pour le retrait (" + montant + ") dépasse le solde disponible.");

        // Crée un objet Retrait
        Retrait v = new Retrait(new Date(), montant, cp);
        // Sauvegarde l'opération dans le repository
        operationRepository.save(v);
        // Met à jour le solde du compte après retrait
        cp.setSolde(cp.getSolde() - montant);
        // Sauvegarde à nouveau le compte avec le solde mis à jour
        compteRepository.save(cp);
    }

    // Méthode pour effectuer un virement entre deux comptes
    @Override
    public void virement(String codeCpte1, String codeCpte2, double montant) {
        // Vérifie que les deux comptes sont différents
        if (codeCpte1.equals(codeCpte2))
            throw new RuntimeException("Un virement ne peut pas être effectué entre deux comptes identiques. Veuillez fournir deux comptes distincts.");

        // Effectue un retrait du premier compte
        retirer(codeCpte1, montant);
        // Effectue un versement sur le second compte
        verser(codeCpte2, montant);
    }

    // Méthode pour lister les opérations effectuées sur un compte, avec pagination
    @Override
    public Page<Operation> listOperation(String codeCpte, int page, int pageSize) {
        // Retourne les opérations paginées en fonction du code de compte
        return operationRepository.listOperation(codeCpte, PageRequest.of(page, pageSize));
    }
}
