package fr.demo.ecopoint.web.dto.entities;

import fr.demo.ecopoint.web.dto.validation.FieldMatch;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "Les mots de passes doivent être identique")
})
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegistrationDto {

    @Getter
    @Setter
    @NotEmpty
    String login;

    @Getter
    @Setter
    @NotEmpty
    String password;

    @Getter
    @Setter
    @NotEmpty
    String confirmPassword;

    @Getter
    @Setter
    @AssertTrue
    Boolean terms;
}