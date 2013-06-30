package fr.ali.selenium.pages;

import fr.ali.selenium.Config;
import static org.fest.assertions.Assertions.assertThat;
import org.fluentlenium.core.FluentPage;
/**
 *
 * @author Adouche Ali
 */
public class SignUpPage extends FluentPage {
    
    @Override
    public String getUrl() {
        return Config.getUrl() +"/signUp.xhtml";
    }
    
    @Override
    public void isAt() {
        assertThat(title()).isEqualTo("Sign Up page");
    }
    
    public void fillAndSubmitSignInForm(String... paramsOrdered) {
        fill("#formSignUp input[type='text'], #formSignUp input[type='password']").with(paramsOrdered);
         click("#submitSignUp");
    }
}
