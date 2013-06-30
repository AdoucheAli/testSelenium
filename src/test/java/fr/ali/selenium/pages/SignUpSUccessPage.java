package fr.ali.selenium.pages;

import fr.ali.selenium.Config;
import static org.fest.assertions.Assertions.assertThat;
import org.fluentlenium.core.FluentPage;
/**
 *
 * @author Adouche Ali
 */
public class SignUpSUccessPage extends FluentPage {
    
    @Override
    public String getUrl() {
        return Config.getUrl() + "/signUpSuccess.xhtml";
    }
    
    @Override
    public void isAt() {
        assertThat(title()).isEqualTo("SignUp success");
    }
}
