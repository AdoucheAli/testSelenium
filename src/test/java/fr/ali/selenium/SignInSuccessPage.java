/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ali.selenium;

import org.fluentlenium.core.FluentPage;
import static org.fest.assertions.fluentlenium.FluentLeniumAssertions.assertThat;
import static org.fest.assertions.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

/**
 *
 * @author Adouche Ali
 */
public class SignInSuccessPage extends FluentPage {

    @Override
    public String getUrl() {
        return "http://localhost:8080/TestSelenium/signInSuccess.xhtml";
    }
    
    @Override
    public void isAt() {
        assertThat(title()).isEqualTo("SignIn success");
    }

}
