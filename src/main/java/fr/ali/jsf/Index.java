package fr.ali.jsf;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Adouche Ali
 */
@Named
@SessionScoped
@Getter  //annotation lombok 
@Setter //annotation lombok
public class Index implements Serializable {
    
    @NotEmpty(message = ChampsForm.ERREUR_NOM_EMPTY)
    private String nom;
    
    @NotEmpty(message = ChampsForm.ERREUR_PRENOM_EMPTY)
    private String prenom;
    
    @NotNull(message = ChampsForm.ERREUR_PASSWORD_EMPTY)
    @Size( min = ChampsForm.PASSWORD_SIZE_MIN, max = ChampsForm.PASSWORD_SIZE_MAX, message = ChampsForm.ERREUR_PASSWORD_OUT_OF_RANGE)
    private String password;
    
    @NotEmpty(message = ChampsForm.ERREUR_EMAIL_EMPTY)
    @Email(message = ChampsForm.ERREUR_EMAIL_INVALID)
    private String email;

    public String signIn() {
        return "signInSuccess.xhtml?faces-redirect=true";
    }

    public String signUp() {
        return "SignUpSuccess.xhtml?faces-redirect=true";
    }

}
