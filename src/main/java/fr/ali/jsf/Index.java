package fr.ali.jsf;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Adouche Ali
 */
@Model
public class Index {
    
    @Inject
    Validator validator;
   
    @NotEmpty(message = "veuillez rentrer un nom")
    private String nom;
    
    @NotEmpty(message = "veuillez rentrer un nom")
    private String prenom;
    
    @NotNull(message = "veuillez rentrer un password")
    @Size(message = "veuillez saisir un password entre {min} et {max} characteres", min = 10, max = 20)
    private String password;
    
    @NotEmpty(message = "veuillez rentrer un email")
    @Email(message = "veuillez rentrer un email valide")
    private String email;

    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String signIn(){

        List<String> passwordAndEmail = Arrays.asList(password, email);
        Set<ConstraintViolation<List<String>>> constraintViolations = validator.validate(passwordAndEmail);
        String goTo;
        if (constraintViolations.size() > 0) {
            goTo = "formulaire.xhtml";
        } else {
            goTo = "bienvenue.xhtml";
        }
        return goTo;
        
        
    }
    
    public String signUp(){
        return "hello.xhtml";
    }
    
}
