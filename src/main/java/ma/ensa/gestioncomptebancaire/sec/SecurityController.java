package ma.ensa.gestioncomptebancaire.sec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController
{
    @RequestMapping(value="/login")
    public String login() {
        return "loginPage";
    }
    @RequestMapping(value="/")
    public String home() {
        return "redirect:/operations";
    }

    @RequestMapping("/403")
    public String error403() {
        return "403";
    }
}
