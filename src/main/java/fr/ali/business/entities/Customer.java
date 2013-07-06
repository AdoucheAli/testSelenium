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
    
    public final static String ERROR_EMAIL_OR_PASSWORD_WRONG = "Vérifiez votre email ou/et votre pasword";
    public final static String ERROR_EMAIL_USED = "Cet email est déjà utilisé";

    public final static String PREFIX = "fr.ali.business.entities.";
    public final static String BY_EMAIL = PREFIX + "Customer.findByEmail"; 

    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "{customer.lastName.empty}" )
    @NonNull //Lombok annotation
    private String lastName;
    
    @NotNull(message = "{customer.firstName.empty}")
    @NonNull
    private String firstName;
    
    @NotNull(message = "{customer.password.empty}")
    @Size( min = PASSWORD_SIZE_MIN, 
           max = PASSWORD_SIZE_MAX, 
           message = "{customer.password.outOfRange}"
    )
    @NonNull
    private String password;
    
    @NotNull(message = "{customer.email.empty}")
    @Email(message = "{customer.email.invalid}")
    @NonNull
    private String email;
}
