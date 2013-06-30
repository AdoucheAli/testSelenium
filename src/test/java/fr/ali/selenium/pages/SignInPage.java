/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ali.selenium.pages;

import fr.ali.selenium.Config;
import static org.fest.assertions.Assertions.assertThat;
import org.fluentlenium.core.FluentPage;

/**
 *
 * @author Adouche Ali
 */
public class SignInPage extends FluentPage {

    @Override
    public String getUrl() {
        return  Config.getUrl() + "/signIn.xhtml";
    }

    @Override
    public void isAt() {
        assertThat(title()).isEqualTo("Sign In page");
    }

    public void fillAndSubmitSignInForm(String... paramsOrdered) {
        fill("#formSignIn input[type='text'], #formSignIn input[type='password']").with(paramsOrdered);
        click("#submitSignIn");
    }
}
