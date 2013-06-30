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
 
    public boolean checkPasswordAndEmail(String email, String password) {
        Customer customer = findByEmail(email);
        return password.equals(customer.getPassword());
    }
    
    public Customer findByEmail(String email){
         Customer customer = new Customer();
         try {
            customer = em.createNamedQuery(Customer.BY_EMAIL, Customer.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException ex) {
        } finally {
            return customer;
        }
    }

    public void persist(Customer user) {
        em.persist(user);
    }
}
