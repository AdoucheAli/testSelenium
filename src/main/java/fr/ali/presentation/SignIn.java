package fr.ali.presentation;

import fr.ali.business.boundary.CustomerHolder;
import fr.ali.business.boundary.CustomerManager;
import fr.ali.business.entities.Customer;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author Adouche Ali
 */
@Named
@RequestScoped
@Getter  //annotation lombok 
@Setter //annotation lombok
public class SignIn implements Serializable {

    @Inject
    CustomerHolder customerHolder;
    
    @Inject
    CustomerManager userManager;
    
    @Inject
    FacesContext facesContext;
    
    private boolean isFind = false;

    //actionListener
    public void verification() {
        Long id = userManager.checkPasswordAndEmail(customerHolder.getCurrentCustomer().getEmail(), 
                customerHolder.getCurrentCustomer().getPassword());
        if ( id.compareTo(0L) > 0 ) {
            customerHolder.getCurrentCustomer().setId(id);
            isFind = true;
        }
    }

    public String submit() {
        String view = "signIn";
        if (isFind) {
            view = checkOriginalViewId();
        } else {
            FacesMessage msg = new FacesMessage(Customer.ERROR_EMAIL_OR_PASSWORD_WRONG);
            facesContext.addMessage(null, msg);
        }
        return view;
    }
    
    public String logOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "signIn?faces-redirect=true";
    }

    private String checkOriginalViewId() {
        StringBuilder view = new StringBuilder();
        String viewId = customerHolder.getOriginalViewId();
        if (viewId != null) {
            view.append(viewId);
            customerHolder.setOriginalViewId(null);
        } else {
            view.append("signInSuccess");
        }
        view.append("?faces-redirect=true");
        return view.toString();
    }
}
