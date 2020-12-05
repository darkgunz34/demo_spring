package fr.demo.ecopoint.model.service;

import fr.demo.ecopoint.model.entities.User;
import fr.demo.ecopoint.web.dto.entities.UserLoginDto;
import fr.demo.ecopoint.web.dto.entities.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByLogin(String login);

    User findByLoginAndPassword(UserLoginDto login);

    User save(UserRegistrationDto registration);
}