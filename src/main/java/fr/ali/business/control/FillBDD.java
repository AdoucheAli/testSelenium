package fr.ali.business.control;

import fr.ali.business.entities.Customer;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author Adouche Ali
 */
@Startup
@Singleton
public class FillBDD {
    
    @PersistenceContext
    EntityManager em;
    
    @PostConstruct
    public void fill() {
        em.persist(Customer.of("adouche", "ali", "adoucheali", "adoucheali@yahoo.fr"));
        em.persist(Customer.of("adouche", "samir", "adouchesamir", "adouchesamir@yahoo.fr"));
        em.persist(Customer.of("adouche", "moussa", "adouchemoussa", "adouchemoussa@yahoo.fr"));
        em.persist(Customer.of("adouche", "karim", "adouchekarim", "adouchekarim@yahoo.fr")); 
    }
}
