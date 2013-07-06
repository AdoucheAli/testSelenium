package fr.ali.business.boundary;

import fr.ali.business.entities.Customer;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
/**
 *
 * @author Adouche Ali
 */
@Data
@Named
@SessionScoped
public class CustomerHolder implements Serializable {

    @Inject
    Customer currentCustomer;
    
    private String originalViewId;
}
