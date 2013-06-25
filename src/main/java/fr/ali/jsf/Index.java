package fr.ali.jsf;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Adouche Ali
 */
@Named
@SessionScoped
public class Index implements Serializable {
    
    @NotEmpty(message = "veuillez rentrer un nom")
    private String nom;
    
    @NotEmpty(message = "veuillez rentrer un prenom")
    private String prenom;
    
    @NotNull(message = "veuillez rentrer un password")
    @Size(message = "veuillez saisir un password entre {min} et {max} characteres", min = 10, max = 20)
    private String password;
    
    @NotEmpty(message = "veuillez rentrer un email")
    @Email(message = "veuillez rentrer un email valide")
    private String email;

    private boolean isSubmittedSignIn = false;
    private boolean isSubmittedSignUp = false;
    
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

    public boolean isIsSubmittedSignIn() {
        return isSubmittedSignIn;
    }

    public void setIsSubmittedSignIn(boolean isSubmittedSignIn) {
        this.isSubmittedSignIn = isSubmittedSignIn;
    }

    public boolean isIsSubmittedSignUp() {
        return isSubmittedSignUp;
    }

    public void setIsSubmittedSignUp(boolean isSubmittedSignUp) {
        this.isSubmittedSignUp = isSubmittedSignUp;
    }
    
    public void listenerSignIn() {
        isSubmittedSignIn = true;
        isSubmittedSignUp = false;
    }
    public void listenerSignUp() {
        isSubmittedSignUp = true;
        isSubmittedSignIn = false;
    }
    public String signIn(){
        return "signInSuccess.xhtml";   
    }
    
    public String signUp(){
        return "SignUpSuccess.xhtml";
    } 
}
