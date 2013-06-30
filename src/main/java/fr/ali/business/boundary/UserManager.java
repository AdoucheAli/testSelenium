package fr.ali.business.boundary;

import fr.ali.business.entities.Customer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Adouche Ali
 */
@Stateless
public class UserManager {

    @PersistenceContext
    EntityManager em;
    private static final Logger logger =
            Logger.getLogger("UserManager");

    public boolean checkPasswordAndEmail(Customer customerToCheck) {

        boolean isFind = false;
        try {
            Customer user = em.createNamedQuery(Customer.BY_EMAIL, Customer.class)
                    .setParameter("email", customerToCheck.getEmail())
                    .getSingleResult();
            isFind = user.getPassword().equals(customerToCheck.getPassword());
        } catch (NoResultException ex) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String ip = httpServletRequest.getRemoteAddr();
            logger.log(Level.INFO, " email et password invalid, adresse ip : {0}", ip);
        } finally {
            return isFind;
        }
    }
}
