package fr.ali.jsf;

import fr.ali.cdi.Validateur;
import java.util.Iterator;
import java.util.Set;
import javax.enterprise.inject.Model;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Adouche Ali
 */
@Model
public class Index {
    
    @Inject @Validateur
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

    private boolean isPasswordOrEmailError = false;
    
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
    
    public void listener() {
        Set<ConstraintViolation<Index>> constraintViolations = validator.validate(this);
        System.out.println(email + " " + password);
        System.out.println("**********************************************************");
        
        for (Iterator<ConstraintViolation<Index>> it = constraintViolations.iterator(); it.hasNext();) {
            ConstraintViolation<Index> contraintes = it.next();

            System.out.println("propriete path " + contraintes.getPropertyPath().toString());
            System.out.println("**********************************************************");
            
            if( "email".equals(contraintes.getPropertyPath().toString()) 
                    || "password".equals(contraintes.getPropertyPath().toString()) ) {
                isPasswordOrEmailError = true;
            }
        }
    }
    public String signIn(){
        String goTo;
        if (isPasswordOrEmailError) {
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
