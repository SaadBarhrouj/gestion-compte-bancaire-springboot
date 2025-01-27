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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BanqueController {
    @Autowired
    private IBanqueMetier iBanqueMetier;
    @Autowired
    private OperationRepository operationRepository;

    @GetMapping("/operations")
    public String index() {
        return "comptes";
    }


    @GetMapping("/consulterCompte")
    public String consulterCompte(Model model,
                                    @RequestParam(name = "codeCompte") String codeCompte,
                                    @RequestParam (name = "page",defaultValue = "0")int page,
                                    @RequestParam(name = "size", defaultValue = "2")int size )
                             {
        try {
            // Récupération du compte
            model.addAttribute("codeCompte", codeCompte);

            Compte cp = iBanqueMetier.consulterCompte(codeCompte);
            model.addAttribute("compte", cp);
            model.addAttribute("typeCompte", cp.getClass().getSimpleName());

            // Récupération des opérations avec pagination
            Page<Operation> pageOperation = iBanqueMetier.listOperation(codeCompte,page,size);
            model.addAttribute("listOperation", pageOperation.getContent());
            model.addAttribute("pages", new int [pageOperation.getTotalPages()]);
            model.addAttribute("currentPage", page);
            model.addAttribute("codeCompte", codeCompte);
    model.addAttribute("totalPages",pageOperation.getTotalPages()-1 );
        } catch (Exception e) {
            model.addAttribute("exception", e.getMessage());
        }
        return "comptes";
    }






}