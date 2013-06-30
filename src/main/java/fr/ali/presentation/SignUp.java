/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ali.presentation;

import fr.ali.business.entities.Customer;
import javax.enterprise.context.RequestScoped;
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
    Customer user;
     
    public String submit() {
        return "SignUpSuccess.xhtml?faces-redirect=true";
    }

    
}
