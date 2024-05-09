package com.kantor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull()
    @Size(min = 2, max = 50, message = "First name must be between {min} and {max} characters long.")
    private String firstName;
    @NotNull()
    @Size(min = 2, max = 50, message = "Last name must be between {min} and {max} characters long.")
    private String lastName;
    @NotNull()
    @Email(message = "Invalid email address format.")
    private String email;
    @NotNull()
    @Pattern(regexp = "\\d{9}", message = "\"The phone number must consist of 9 digits.")
    private int phoneNumber;
    @NotNull()
    @Pattern(regexp = "\\d{2} \\d{2} \\d{4}", message = "The date of birth must be in the format dd mm yyyy.")
    private String dateOfBirth;
}