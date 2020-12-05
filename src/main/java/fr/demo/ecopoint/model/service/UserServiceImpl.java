package fr.demo.ecopoint.model.service;

import fr.demo.ecopoint.model.entities.Role;
import fr.demo.ecopoint.model.entities.User;
import fr.demo.ecopoint.model.repository.UserRepository;
import fr.demo.ecopoint.web.dto.entities.UserLoginDto;
import fr.demo.ecopoint.web.dto.entities.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User findByLoginAndPassword(UserLoginDto login) {
        return userRepository.findByLoginAndPassword(login.getLogin(),passwordEncoder.encode(login.getPassword()));
    }

    public User save(UserRegistrationDto registration) {
        User user = new User();
        user.setLogin(registration.getLogin());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_UTILISATEUR")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(),
            user.getPassword(),
            mapRolesToAuthorities(user.getRoles()));
    }

    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
    }
}