package fr.ali.presentation;

import fr.ali.business.boundary.UserManager;
import fr.ali.business.entities.Customer;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
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
    Customer user;
    
    @Inject
    UserManager userManager;
    
    @Inject
    FacesContext facesContext;
    
    private boolean isFind = false;
    
    public void verification() {
        isFind = userManager.checkPasswordAndEmail(user);
        System.out.println(isFind);
        System.out.println("verification");
    }
    
    public String submit() {
        String v = ((HttpServletRequest)facesContext.getExternalContext().getRequest()).getPathInfo();
        String v1 = ((HttpServletRequest)facesContext.getExternalContext().getRequest()).getContextPath();
        String v2 = ((HttpServletRequest)facesContext.getExternalContext().getRequest()).getRequestURI();
        System.out.println(v);
        System.out.println(v1);
        System.out.println(v2);
        String view = "signIn.xhtml";
        if (isFind) {
            view = "signInSuccess.xhtml?faces-redirect=true";
        }else {
            FacesMessage msg = new FacesMessage(Customer.ERREUR_EMAIL_OR_PASSWORD_WRONG); 
            facesContext.addMessage(null,msg);
        }
        
       return  view;
    }
}
