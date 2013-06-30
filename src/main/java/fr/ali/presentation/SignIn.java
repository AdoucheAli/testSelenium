package fr.ali.presentation;

import fr.ali.business.boundary.UserManager;
import fr.ali.business.entities.Customer;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
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
@SessionScoped
@Getter  //annotation lombok 
@Setter //annotation lombok
public class SignIn implements Serializable {

    @Inject
    Customer customer;
    
    @Inject
    UserManager userManager;
    
    @Inject
    FacesContext facesContext;
    
    private boolean isFind = false;

    //actionListener
    public void verification() {
        isFind = userManager.checkPasswordAndEmail(customer.getEmail(), customer.getPassword());
    }

    public String submit() {
        String view = "signIn.xhtml";
        if (isFind) {
            view = "signInSuccess.xhtml?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(Customer.ERREUR_EMAIL_OR_PASSWORD_WRONG);
            facesContext.addMessage(null, msg);
        }
        return view;
    }
    
    public String logOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "signIn.xhtml?faces-redirect=true";
    }
}
