package fr.ali.business.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Adouche Ali
 */
@NoArgsConstructor //annotation lombok
@RequiredArgsConstructor(staticName = "of") //annotation lombok
@Data //annotation lombok, generation getter, setter, hashcode, equals et toString
@Entity
@NamedQueries({@NamedQuery(name = Customer.BY_EMAIL,query = "select c from Customer c where c.email = :email")})
public class Customer implements Serializable {
    
    public final static int PASSWORD_SIZE_MIN = 10;
    public final static int PASSWORD_SIZE_MAX = 20;
    
    public final static String ERREUR_NOM_EMPTY = "veuillez rentrer un nom";
    public final static String ERREUR_PRENOM_EMPTY = "veuillez rentrer un prenom";
    public final static String ERREUR_PASSWORD_EMPTY = "veuillez rentrer un password";
    public final static String ERREUR_PASSWORD_OUT_OF_RANGE = "veuillez rentrer un password compris entre " + PASSWORD_SIZE_MIN + " et " + PASSWORD_SIZE_MAX +" caractères";
    public final static String ERREUR_EMAIL_EMPTY = "veuillez rentrer un email";
    public final static String ERREUR_EMAIL_INVALID = "veuillez rentrer un email valide";
    public final static String ERREUR_EMAIL_OR_PASSWORD_WRONG = "Vérifiez votre email ou/et votre pasword";

    public final static String PREFIX = "fr.ali.business.entities.";
    public final static String BY_EMAIL = PREFIX + "Customer.findByEmail"; 
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = ERREUR_NOM_EMPTY)
    @NonNull
    private String nom;
    
    @NotNull(message = ERREUR_PRENOM_EMPTY)
    @NonNull
    private String prenom;
    
    @NotNull(message = ERREUR_PASSWORD_EMPTY)
    @Size( min = PASSWORD_SIZE_MIN, 
           max = PASSWORD_SIZE_MAX, 
           message = ERREUR_PASSWORD_OUT_OF_RANGE
    )
    @NonNull
    private String password;
    
    @NotNull(message = ERREUR_EMAIL_EMPTY)
    @Email(message = ERREUR_EMAIL_INVALID)
    @NonNull
    private String email;


}
