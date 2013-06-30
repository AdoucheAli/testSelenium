package fr.ali.business.control;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
/**
 *
 * @author Adouche Ali
 */
public class Producer {
    
    @Produces @RequestScoped
    public FacesContext getFacesContext() {
         return FacesContext.getCurrentInstance();
    }
}
