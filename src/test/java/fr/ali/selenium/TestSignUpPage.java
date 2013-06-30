package fr.ali.selenium;

import fr.ali.business.entities.Customer;
import fr.ali.selenium.pages.SignInPage;
import fr.ali.selenium.pages.SignUpPage;
import java.util.concurrent.TimeUnit;
import static org.fest.assertions.fluentlenium.FluentLeniumAssertions.assertThat;
import org.fest.assertions.fluentlenium.custom.FluentListAssert;
import org.fest.util.Arrays;
import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.adapter.util.SharedDriver;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.junit.Before;
import org.junit.Test;

@SharedDriver(type = SharedDriver.SharedType.PER_CLASS)
public class TestSignUpPage extends FluentTest {

    @Page
    private SignUpPage signUpPage;
    @Page
    private SignInPage signInPage;
    private String[] champs = new String[4];

    @Before
    public void openBrowser() {
        goTo(signUpPage);
        withDefaultPageWait(2, TimeUnit.SECONDS);
        withDefaultSearchWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void should_return_is_At_SignUp() {
        assertThat(signUpPage).isAt();
    }

    @Test
    public void should_go_to_Sign_In_page() {
        click("#btnSignIn").await().untilPage(signInPage).isLoaded();
        assertThat(signInPage).isAt();
    }
    
    @Test
    public void should_succeed_to_sign_up() {
        champs = Arrays.array("paris", "paris", "paris@yahoo.fr", "parisparis");
        signUpPage.fillAndSubmitSignInForm(champs);
        checkNumberOfMessages(1);
        checkErrorText("sign up effectué");
    }
    
    @Test
    public void should_return_4_errors() {
        signUpPage.fillAndSubmitSignInForm();
        checkNumberOfMessages(4);
    }

    @Test
    public void should_return_password_is_empty_error() {
        //nom, prenom, email, password.Il faut garder le meme ordre que dans la page xhtml.
        champs = Arrays.array("adouche", "ali", "adoucheali@yahoo.fr", "");
        signUpPage.fillAndSubmitSignInForm(champs);

        checkNumberOfMessages(1);
        checkErrorText(Customer.ERREUR_PASSWORD_EMPTY);
    }

    @Test
    public void should_return_password_is_out_of_range_error() {
        champs = Arrays.array("adouche", "ali", "adoucheali@yahoo.fr", "aaaaa");
        signUpPage.fillAndSubmitSignInForm(champs);

        checkNumberOfMessages(1);
        checkErrorText(Customer.ERREUR_PASSWORD_OUT_OF_RANGE);

        champs[3] = "aaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        signUpPage.fillAndSubmitSignInForm(champs);

        checkNumberOfMessages(1);
        checkErrorText(Customer.ERREUR_PASSWORD_OUT_OF_RANGE);
    }

    @Test
    public void should_return_email_is_empty_error() {
        champs = Arrays.array("adouche", "ali", "", "aaaaaaaaaaa");
        signUpPage.fillAndSubmitSignInForm(champs);

        checkNumberOfMessages(1);
        checkErrorText(Customer.ERREUR_EMAIL_EMPTY);
    }

    @Test
    public void should_return_email_is_invalid_error() {
        champs = Arrays.array("adouche", "ali", "adoucheali", "aaaaaaaaaaa");
        signUpPage.fillAndSubmitSignInForm(champs);

        checkNumberOfMessages(1);
        checkErrorText(Customer.ERREUR_EMAIL_INVALID);
    }

    @Test
    public void should_return_nom_is_empty_error() {
        champs = Arrays.array("", "ali", "adoucheali@yahoo.fr", "aaaaaaaaaaa");
        signUpPage.fillAndSubmitSignInForm(champs);

        checkNumberOfMessages(1);
        checkErrorText(Customer.ERREUR_NOM_EMPTY);
    }

    @Test
    public void should_return_prenom_is_empty_error() {
        champs = Arrays.array("adouche", "", "adoucheali@yahoo.fr", "aaaaaaaaaaa");
        signUpPage.fillAndSubmitSignInForm(champs);

        checkNumberOfMessages(1);
        checkErrorText(Customer.ERREUR_PRENOM_EMPTY);
    }
   
    @Test
    public void should_return_email_is_used_error() {
        champs = Arrays.array("adouche", "farid", "adoucheali@yahoo.fr", "adouchefarid");
        signUpPage.fillAndSubmitSignInForm(champs);
        
        checkNumberOfMessages(1);
        checkErrorText(Customer.ERREUR_EMAIL_USED);
        
    }
    
//==============================================================================

    private FluentListAssert checkNumberOfMessages(final int size) {
        return assertThat(find("#messageSaisieSignUp").find("li")).hasSize(size);
    }

    private void checkErrorText(final String message) {
        checkErrorText(find("#messageSaisieSignUp").find("li"), message);
    }

    private void checkErrorText(final FluentList baliseHTML, final String message) {
        assertThat(baliseHTML).hasText(message);
    }
}
