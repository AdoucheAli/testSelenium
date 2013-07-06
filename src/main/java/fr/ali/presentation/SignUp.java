package fr.ali.presentation;

import fr.ali.business.boundary.CustomerManager;
import fr.ali.business.entities.Customer;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
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
public class SignUp {
    
    @Inject
    Customer customer;
    
    @Inject
    CustomerManager userManager;
    
    @Inject
    FacesContext facesContext;

    private boolean isEmailNotUsed = false;
    
    //actionListener
    public void verification() {
        Customer cust = userManager.findByEmail(this.customer.getEmail());
        isEmailNotUsed = (null == cust.getId());
    }
    
    public String submit() {
        StringBuilder view = new StringBuilder() ;
        view.append("signUp");
        if (isEmailNotUsed) {
            userManager.persist(customer);
            customer = new Customer();
            //le message s'affichera la premiere fois, si rafraichissement de la page le message disparait.
            Flash flash = facesContext.getExternalContext().getFlash();
            flash.put("success","sign up effectué");
            //pour eviter que l'utilisateur ne soumettent le meme formulaire plusieur fois.
            view.append("?faces-redirect=true");

        } else {
            FacesMessage msg = new FacesMessage(Customer.ERROR_EMAIL_USED);
            facesContext.addMessage(null, msg);
        }
       
        return view.toString();
    }  
}
