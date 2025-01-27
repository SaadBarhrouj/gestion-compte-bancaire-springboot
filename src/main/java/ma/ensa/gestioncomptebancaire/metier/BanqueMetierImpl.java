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

    @Override
    public Compte consulterCompte(String codeCpte) {
        return compteRepository.findById(codeCpte)
                .orElseThrow(() -> new RuntimeException("Compte Introuvable"));
    }

    @Override
    public void verser(String codeCpte, double montant) {
        Compte cp = consulterCompte(codeCpte);
        Versement r = new Versement(new Date(),montant,cp);
        operationRepository.save(r);
        cp.setSolde(cp.getSolde()+montant);
        compteRepository.save(cp);
    }

    @Override
    public void retirer(String codeCpte, double montant) {
        Compte cp = consulterCompte(codeCpte);
        double facilitesCaisse=0;
        if(cp instanceof CompteCourant)
            facilitesCaisse=((CompteCourant) cp).getDecouvert();
        if (facilitesCaisse+cp.getSolde()<montant)
            throw new RuntimeException("solde Insuffisant");
       Retrait v = new  Retrait(new Date(),montant,cp);
        operationRepository.save(v);
        cp.setSolde(cp.getSolde()-montant);
        compteRepository.save(cp);
    }

    @Override
    public void virement(String codeCpte1, String codeCpte2, double montant) {
        retirer(codeCpte1, montant);
        verser(codeCpte2, montant);
    }

    @Override
    public Page<Operation> listOperation(String codeCpte, int page, int pageSize) {

        return operationRepository.listOperation(codeCpte, PageRequest.of(page, pageSize));

    }

}
