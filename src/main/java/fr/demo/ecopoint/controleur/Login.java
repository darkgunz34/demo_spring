package fr.demo.ecopoint.controleur;

import fr.demo.ecopoint.model.entities.User;
import fr.demo.ecopoint.model.service.UserService;
import fr.demo.ecopoint.web.dto.entities.UserLoginDto;
import fr.demo.ecopoint.web.dto.entities.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Controlleur pour la creation d'un compte
 */
//@Controller
//@RequestMapping("/login")
public class Login {
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showPageCreateAccount(){
        return "creerCompte";
    }

    @PostMapping
    public String loginAccount(Model model, @ModelAttribute("user") @Valid UserLoginDto userLoginDto, BindingResult result){
        User existing = userService.findByLoginAndPassword(userLoginDto);

        if(result.hasErrors()){
            return "login";
        }

        model.addAttribute("user",existing);
        return "redirect:/index?success";
    }
}
