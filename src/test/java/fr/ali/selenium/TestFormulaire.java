package fr.ali.selenium;

import static org.fest.assertions.fluentlenium.FluentLeniumAssertions.assertThat;
import org.fest.assertions.fluentlenium.custom.FluentListAssert;
import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.adapter.util.SharedDriver;
import org.fluentlenium.core.domain.FluentList;
import org.junit.Test;

@SharedDriver(type = SharedDriver.SharedType.PER_CLASS)
public class TestFormulaire extends FluentTest {
    
    @Override
    public String getDefaultBaseUrl() {
        return "http://localhost:8080/TestSelenium/";
    }
    
    @Test
    public void should_return_3_errors() {
        goTo(getDefaultBaseUrl());
        submit("#formSignIn");
        
        checkSize(3);
    }
    
    @Test
    public void should_return_2_errors() {
        goTo(getDefaultBaseUrl());
        fill("#inputNomSignIn").with("ali");
        submit("#formSignIn");
        
        checkSize(2);
    }
    
    @Test
    public void should_return_password_is_empty_error() {
        goTo(getDefaultBaseUrl());
        fill("#inputNomSignIn").with("ali");
        fill("#inputEmailSignIn").with("adoucheali@yahoo.fr");
        submit("#formSignIn");
        
        checkSize(1);
        checkText("veuillez rentrer un password");
    }
    
    @Test
    public void should_return_password_is_out_of_range_error() {
        goTo(getDefaultBaseUrl());
        fill("#inputNomSignIn").with("ali");
        fill("#inputPasswordSignIn").with("adouche");
        fill("#inputEmailSignIn").with("adoucheali@yahoo.fr");
        submit("#formSignIn");
        
        checkSize(1);
        checkText("veuillez saisir un password entre 10 et 20 characteres");
        
        fill("#inputPasswordSignIn").with("adoucheuuuuuuuuuuuuuuu");
        submit("#formSignIn");
        
        checkText("veuillez saisir un password entre 10 et 20 characteres");
    }
    
    @Test
    public void should_return_email_is_empty_error() {
        goTo(getDefaultBaseUrl());
        fill("#inputNomSignIn").with("ali");
        fill("#inputPasswordSignIn").with("adoucheali");
        submit("#formSignIn");
        
        checkSize(1);
        checkText("veuillez rentrer un email");
    }
    
    @Test
    public void should_return_email_is_invalid_error() {
        goTo(getDefaultBaseUrl());
        fill("#inputNomSignIn").with("ali");
        fill("#inputPasswordSignIn").with("adoucheali");
        fill("#inputEmailSignIn").with("adoucheali");
        submit("#formSignIn");
        
        checkSize(1);
        checkText("veuillez rentrer un email valide");
    }
    
    @Test
    public void should_return_title_is_Sign_Up() {
        goTo(getDefaultBaseUrl());
        click("#btnSignUp").await().until("#wrapFormSignUp").areDisplayed();
        checkText(find("#titreSignUp"), "Sign Up");
    }
//==============================================================================
    private FluentListAssert checkSize(final int size) {
        return assertThat(find("#erreurSaisie").find("li")).hasSize(size);
    }
    
    private void checkText(final String message) {
        checkText(find("#erreurSaisie").find("li"), message);
    }

    private void checkText(final FluentList baliseHTML, final String message) {
        assertThat(baliseHTML).hasText(message);
    }
}
