package fr.ali.selenium;

import fr.ali.business.entities.Customer;
import fr.ali.selenium.pages.SignInPage;
import fr.ali.selenium.pages.SignInSuccessPage;
import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;
import static org.fest.assertions.fluentlenium.FluentLeniumAssertions.assertThat;
import org.fest.assertions.fluentlenium.custom.FluentListAssert;
import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.adapter.util.SharedDriver;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.junit.Before;
import org.junit.Test;

@SharedDriver(type = SharedDriver.SharedType.PER_CLASS)
public class TestFormulaire extends FluentTest {

    @Page
    public SignInPage signInPage;
    
    @Page
    public SignInSuccessPage signInSuccessPage;

    @Before
    public void openBrowser() {
        goTo(signInPage);
        withDefaultPageWait(10, TimeUnit.SECONDS);
        withDefaultSearchWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void should_return_is_At_SignIn() {
        assertThat(signInPage).isAt();
    }

    @Test
    public void should_return_2_errors() {
        signInPage.fillAndSubmitSignInForm();
        checkSize(2);
    }

    @Test
    public void should_return_password_is_empty_error() {
        signInPage.fillAndSubmitSignInForm("adoucheali@yahoo.fr", "");

        checkSize(1);
        checkText(Customer.ERREUR_PASSWORD_EMPTY);
    }

    @Test
    public void should_return_password_is_out_of_range_error() {
        signInPage.fillAndSubmitSignInForm("adoucheali@yahoo.fr", "adouche");

        checkSize(1);
        checkText(Customer.ERREUR_PASSWORD_OUT_OF_RANGE);

        signInPage.fillAndSubmitSignInForm("adoucheali@yahoo.fr", "adoucheuuuuuuuuuuuuuuu");

        checkSize(1);
        checkText(Customer.ERREUR_PASSWORD_OUT_OF_RANGE);
    }

    @Test
    public void should_return_email_is_empty_error() {
        signInPage.fillAndSubmitSignInForm("", "adoucheali");

        checkSize(1);
        checkText(Customer.ERREUR_EMAIL_EMPTY);
    }
    
    @Test
    public void should_return_email_or_password_wrong() {
        signInPage.fillAndSubmitSignInForm("adouche@yahoo.fr", "adouchealii");
        checkSize(1);
        checkText(Customer.ERREUR_EMAIL_OR_PASSWORD_WRONG);
        
        signInPage.fillAndSubmitSignInForm("adoucheali@yahoo.fr", "adouchealii");

        checkSize(1);
        checkText(Customer.ERREUR_EMAIL_OR_PASSWORD_WRONG);
    }


    @Test
    public void should_return_email_is_invalid_error() {
        signInPage.fillAndSubmitSignInForm("adoucheali", "adoucheali");
        
        checkSize(1);
        checkText(Customer.ERREUR_EMAIL_INVALID);
    }

    @Test
    public void should_open_page_signInSuccess() {
        signInPage.fillAndSubmitSignInForm("adoucheali@yahoo.fr", "adoucheali");
        goTo(signInSuccessPage);
        assertThat(signInSuccessPage).isAt();   
    }
   
    @Test
    public void should_print_message_in_signInSuccessPage() {
        String[] champs = {"adoucheali@yahoo.fr", "adoucheali"};
        signInPage.fillAndSubmitSignInForm(champs);
        goTo(signInSuccessPage);
        assertThat(signInSuccessPage).isAt();
        String expected = MessageFormat.format("email : {0}, password : {1}", champs[0], champs[1]);
        checkText(find("p"), expected);
    }

    @Test
    public void should_return_titleForm_is_Sign_Up() {
        click("#btnSignUp").await().until("#contentSignUp").areDisplayed();
        checkText(find("#titreSignUp"), "Sign Up");
    }
//==============================================================================

    private FluentListAssert checkSize(final int size) {
        return assertThat(find("#erreurSaisieSignIn").find("li")).hasSize(size);
    }

    private void checkText(final String message) {
        checkText(find("#erreurSaisieSignIn").find("li"), message);
    }

    private void checkText(final FluentList baliseHTML, final String message) {
        assertThat(baliseHTML).hasText(message);
    }
}
