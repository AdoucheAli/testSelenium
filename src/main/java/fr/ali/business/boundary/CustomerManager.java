package fr.ali.business.boundary;

import fr.ali.business.entities.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author Adouche Ali
 */
@Stateless
public class CustomerManager {

    @PersistenceContext
    EntityManager em;
 
    public Long checkPasswordAndEmail(String email, String password) {
        Customer customer = findByEmail(email);
        return ( password.equals(customer.getPassword()) )? customer.getId(): 0L;
    }
    
    public Customer findByEmail(String email){
        Customer customer = null;
        try {
            customer = em.createNamedQuery(Customer.BY_EMAIL, Customer.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } finally {
            return (customer != null) ? customer
                                      : new Customer();
        }
    }

    public void persist(Customer user) {
        em.persist(user);
    }
}
