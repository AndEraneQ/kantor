package com.kantor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @Size(min = 6, max = 50, message = "Login must be between {min} and {max} characters long")
    private String login;

    @NotNull()
    private String password;

    @NotNull()
    @Size(min = 2, max = 50, message = "First name must be between {min} and {max} characters long.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$", message = "Password must contain at least one uppercase letter and one digit")
    private String firstName;

    @NotNull()
    @Size(min = 2, max = 50, message = "Last name must be between {min} and {max} characters long.")
    private String lastName;

    @NotNull()
    @Column(unique = true)
    @Email(message = "Invalid email address format.")
    private String email;

    @NotNull()
    @Pattern(regexp = "\\d{9}", message = "\"The phone number must consist of 9 digits.")
    private String phoneNumber;

    @NotNull()
    @Temporal(TemporalType.DATE)
    private String dateOfBirth;
}
