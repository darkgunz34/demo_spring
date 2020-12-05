package fr.demo.ecopoint.web.dto.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLoginDto {

    @Getter
    @Setter
    @NotEmpty
    String login;

    @Getter
    @Setter
    @NotEmpty
    String password;
}