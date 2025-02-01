package ma.ensa.gestioncomptebancaire.metier;

import ma.ensa.gestioncomptebancaire.entities.*;
import org.springframework.data.domain.Page;

/**
 * Interface qui définit les opérations métiers disponibles pour gérer les comptes bancaires.
 * Ces opérations incluent la consultation de compte, les versements, les retraits, les virements et l'affichage des opérations.
 */
public interface IBanqueMetier {

    /**
     * Permet de consulter un compte bancaire à partir de son code.
     *
     * @param codeCpte : le code unique du compte à consulter
     * @return le compte bancaire associé au code
     * @throws RuntimeException si le compte n'est pas trouvé
     */
    public Compte consulterCompte(String codeCpte);

    /**
     * Permet d'effectuer un versement sur un compte bancaire.
     *
     * @param codeCpte : le code du compte sur lequel effectuer le versement
     * @param montant : le montant à verser
     * @throws RuntimeException si le montant est invalide
     */
    public void verser(String codeCpte, double montant);

    /**
     * Permet d'effectuer un retrait d'un compte bancaire.
     *
     * @param codeCpte : le code du compte depuis lequel effectuer le retrait
     * @param montant : le montant à retirer
     * @throws RuntimeException si le solde est insuffisant ou si le montant est invalide
     */
    public void retirer(String codeCpte, double montant);

    /**
     * Permet d'effectuer un virement entre deux comptes bancaires.
     *
     * @param codeCpte1 : le code du premier compte (source)
     * @param codeCpte2 : le code du deuxième compte (destination)
     * @param montant : le montant à virer
     * @throws RuntimeException si les codes de comptes sont identiques ou si le solde est insuffisant
     */
    public void virement(String codeCpte1, String codeCpte2, double montant);

    /**
     * Permet de récupérer une page d'opérations effectuées sur un compte bancaire.
     *
     * @param codeCpte : le code du compte dont on souhaite afficher les opérations
     * @param page : le numéro de la page à récupérer
     * @param pageSize : le nombre d'opérations par page
     * @return une page d'opérations associées au compte
     */
    public Page<Operation> listOperation(String codeCpte, int page, int pageSize);
}
