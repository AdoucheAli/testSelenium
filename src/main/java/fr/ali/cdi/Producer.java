package fr.ali.cdi;

import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;

/**
 *
 * @author Adouche Ali
 */
@Startup
@Singleton
public class Producer {
    
    @Produces @Validateur
    public Validator getValidator() {
        ValidatorFactory factory = Validation.byProvider(HibernateValidator.class).
                configure().buildValidatorFactory();
        return factory.getValidator();
    }
}
