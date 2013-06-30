/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ali.presentation;

import fr.ali.business.boundary.UserManager;
import fr.ali.business.entities.Customer;
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
public class SignUp {
    
    @Inject
    Customer customer;
    
    @Inject
    UserManager userManager;
    
    @Inject
    FacesContext facesContext;
    
    private boolean isEmailNotUsed = false;
    
    //actionListener
    public void verification() {
        Customer cust = userManager.findByEmail(this.customer.getEmail());
        isEmailNotUsed = (null == cust.getId());
    }
    
    public String submit() {
        FacesMessage msg = new FacesMessage();
        if (isEmailNotUsed) {
            userManager.persist(customer);
            customer = new Customer();
            msg.setSummary("sign up effectué");  
        } else {
            msg.setSummary(Customer.ERREUR_EMAIL_USED);
        }
        facesContext.addMessage(null, msg);
        return "signUp.xhtml";
    }  
}
