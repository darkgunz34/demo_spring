package fr.demo.ecopoint.controleur;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Ensemble des routes pouvant êre pointer sur l'application.
 */
@Configuration
public class RouteConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //page index accessible en client co et non co
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/test").setViewName("test");
        registry.addViewController("/terme").setViewName("terme");

        //page de connexion
        registry.addViewController("/login").setViewName("login");

        //page pour les utilisateurs uniquement
        registry.addViewController("/utilisateur").setViewName("utilisateur/utilisateur");

        //page pour administrateur
        registry.addViewController("/admin/connecter").setViewName("admin/connecter");
        registry.addViewController("/admin/admin").setViewName("admin/admin");
    }
}