package ma.ensa.gestioncomptebancaire.web;

import ma.ensa.gestioncomptebancaire.dao.OperationRepository;
import ma.ensa.gestioncomptebancaire.entities.Compte;
import ma.ensa.gestioncomptebancaire.entities.Operation;
import ma.ensa.gestioncomptebancaire.metier.IBanqueMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BanqueController {

    // Injection des dépendances pour l'accès aux services métier et repository
    @Autowired
    private IBanqueMetier iBanqueMetier;
    @Autowired
    private OperationRepository operationRepository;

    // Méthode pour afficher la page des comptes bancaires
    @GetMapping("/operations")
    public String index() {
        return "comptes"; // Retourne la vue des comptes
    }

    // Méthode pour consulter un compte bancaire et ses opérations associées
    @GetMapping("/consulterCompte")
    public String consulterCompte(Model model,
                                  @RequestParam(name = "codeCompte") String codeCompte,
                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "4") int size) {
        try {
            // Récupération du compte bancaire par son code
            model.addAttribute("codeCompte", codeCompte);
            Compte cp = iBanqueMetier.consulterCompte(codeCompte);
            model.addAttribute("compte", cp); // Ajout du compte dans le modèle
            model.addAttribute("typeCompte", cp.getClass().getSimpleName()); // Type de compte (classe simple)

            // Récupération des opérations effectuées sur ce compte avec pagination
            Page<Operation> pageOperation = iBanqueMetier.listOperation(codeCompte, page, size);
            model.addAttribute("listOperation", pageOperation.getContent()); // Liste des opérations
            model.addAttribute("pages", new int[pageOperation.getTotalPages()]); // Pagination
            model.addAttribute("currentPage", page); // Page actuelle
            model.addAttribute("totalPages", pageOperation.getTotalPages() - 1); // Nombre total de pages (decrementé de 1 pour l'indice)
        } catch (Exception e) {
            // En cas d'erreur, l'exception est ajoutée au modèle pour affichage
            model.addAttribute("exception", e.getMessage());
        }
        return "comptes"; // Retourne la vue des comptes
    }

    // Méthode pour enregistrer une opération bancaire (versement, retrait, virement)
    @RequestMapping(value = "/saveOperation", method = RequestMethod.POST)
    public String saveOperation(Model model,
                                @RequestParam(name = "typeOperation") String typeOperation,
                                @RequestParam(name = "montant",defaultValue = "0") double montant,
                                @RequestParam(name = "codeCompte") String codeCompte1,
                                @RequestParam(name = "codeCompte2") String codeCompte2,
                                RedirectAttributes redirectAttributes) {
        try {
            // Traitement des différents types d'opérations bancaires
            if (typeOperation.equals("VERS")) {
                // Versement sur le compte
                iBanqueMetier.verser(codeCompte1, montant);
            } else if (typeOperation.equals("RETR")) {
                // Retrait du compte
                iBanqueMetier.retirer(codeCompte1, montant);
            } else {
                // Virement entre deux comptes
                iBanqueMetier.virement(codeCompte1, codeCompte2, montant);
            }
        } catch (Exception e) {
            // En cas d'erreur, redirection avec message d'erreur
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/consulterCompte?codeCompte=" + codeCompte1;
        }

        // Après l'opération, redirection vers la page du compte consulté
        return "redirect:/consulterCompte?codeCompte=" + codeCompte1;
    }

}
