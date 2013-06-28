/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ali.selenium;

import static org.fest.assertions.Assertions.assertThat;
import org.fluentlenium.core.FluentPage;

/**
 *
 * @author Adouche Ali
 */
public class IndexPage extends FluentPage {

    @Override
    public String getUrl() {
        return "http://localhost:8080/TestSelenium/";
    }

    @Override
    public void isAt() {
        assertThat(title()).isEqualTo("Acceuil");
    }

    public void fillAndSubmitSignInForm(String... paramsOrdered) {
        fill("#formSignIn input[type='text'], #formSignIn input[type='password']").with(paramsOrdered);
        submit("#formSignIn");
    }
}
