package fr.demo.ecopoint.controleur;

import fr.demo.ecopoint.model.entities.User;
import fr.demo.ecopoint.model.service.UserService;
import fr.demo.ecopoint.web.dto.entities.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.*;

/**
 * Controller pour la creation d'un compte
 */
@Controller
@RequestMapping("/creerCompte")
public class CreerCompte {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showPageCreateAccount(Model model,HttpServletRequest req) throws Exception {
        String test = req.getHeader("user-agent");
        throw new Exception(test);
    }

    @PostMapping
    public String registerAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result){
        User existing = userService.findByLogin(userDto.getLogin());

        //ajout d'une erreur depuis le code java
        if (existing != null) {
            result.rejectValue("login", null, "Ce login est déjà utiliser");
        }

        // redirection vers la création d'un compte avec les résultats pris en compte dans l'affichage
        if(result.hasErrors()){
            return "creerCompte";
        }

        //redirection vers la page d'accueil en cas de succès
        userService.save(userDto);
        return "redirect:/index?success";
    }
}
