package ma.ensa.gestioncomptebancaire.web;

import ma.ensa.gestioncomptebancaire.GestionCompteBancaireApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class WebInitializer extends SpringBootServletInitializer {
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GestionCompteBancaireApplication.class);
    }

}
