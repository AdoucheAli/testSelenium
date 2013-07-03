package fr.ali.selenium;

import fr.ali.business.entities.Customer;
import fr.ali.selenium.pages.SignInPage;
import fr.ali.selenium.pages.SignUpPage;
import java.util.concurrent.TimeUnit;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.fest.assertions.fluentlenium.FluentLeniumAssertions.assertThat;
import org.fest.assertions.fluentlenium.custom.FluentListAssert;
import org.fest.util.Arrays;
import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.adapter.util.SharedDriver;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.junit.Before;
import org.junit.Test;

@SharedDriver(type = SharedDriver.SharedType.ONCE)
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
        checkPageTitle(signUpPage);
    }

    @Test
    public void should_go_to_Sign_In_page() {
        click("#btnSignIn").await().untilPage(signInPage).isLoaded();
        
        checkPageTitle(signInPage);
    }
    
    @Test
    public void should_succeed_to_sign_up() {
        //nom, prenom, email, password.Il faut garder le meme ordre que dans la page xhtml.
        champs = Arrays.array("paris", "paris", "paris@yahoo.fr", "parisparis");
        
        signUpPage.fillAndSubmitSignInForm(champs);
        
        checkNumberOfMessages(0);
        checkText(find("#message-success"), "sign up effectué");
    }
    
    @Test
    public void should_return_4_errors() {
        signUpPage.fillAndSubmitSignInForm();
        
        checkNumberOfMessages(4);
    }

    @Test
    public void should_return_password_is_empty_error() {
        champs = Arrays.array("adouche", "ali", "adoucheali@yahoo.fr", "");
        
        signUpPage.fillAndSubmitSignInForm(champs);

        checkNumberOfMessages(1);
        checkErrorMessage(Customer.ERREUR_PASSWORD_EMPTY);
    }

    @Test
    public void should_return_password_is_out_of_range_error() {
        champs = Arrays.array("adouche", "ali", "adoucheali@yahoo.fr", "aaaaa");
        String min =  Integer.toString(Customer.PASSWORD_SIZE_MIN);
        String max =  Integer.toString(Customer.PASSWORD_SIZE_MAX);
        String message = Customer.ERREUR_PASSWORD_OUT_OF_RANGE.replace("{min}", min).replace("{max}", max);
        
        signUpPage.fillAndSubmitSignInForm(champs);

        checkNumberOfMessages(1);
        checkErrorMessage(message);

        champs[3] = "aaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        
        signUpPage.fillAndSubmitSignInForm(champs);

        checkNumberOfMessages(1);
        checkErrorMessage(message);
    }

    @Test
    public void should_return_email_is_empty_error() {
        champs = Arrays.array("adouche", "ali", "", "aaaaaaaaaaa");
        
        signUpPage.fillAndSubmitSignInForm(champs);

        checkNumberOfMessages(1);
        checkErrorMessage(Customer.ERREUR_EMAIL_EMPTY);
    }

    @Test
    public void should_return_email_is_invalid_error() {
        champs = Arrays.array("adouche", "ali", "adoucheali", "aaaaaaaaaaa");
        
        signUpPage.fillAndSubmitSignInForm(champs);

        checkNumberOfMessages(1);
        checkErrorMessage(Customer.ERREUR_EMAIL_INVALID);
    }

    @Test
    public void should_return_nom_is_empty_error() {
        champs = Arrays.array("", "ali", "adoucheali@yahoo.fr", "aaaaaaaaaaa");
        
        
        signUpPage.fillAndSubmitSignInForm(champs);

        checkNumberOfMessages(1);
        checkErrorMessage(Customer.ERREUR_NOM_EMPTY);
    }

    @Test
    public void should_return_prenom_is_empty_error() {
        champs = Arrays.array("adouche", "", "adoucheali@yahoo.fr", "aaaaaaaaaaa");
        
        signUpPage.fillAndSubmitSignInForm(champs);

        checkNumberOfMessages(1);
        checkErrorMessage(Customer.ERREUR_PRENOM_EMPTY);
    }
   
    @Test
    public void should_return_email_is_used_error() {
        champs = Arrays.array("adouche", "farid", "adoucheali@yahoo.fr", "adouchefarid");
        
        signUpPage.fillAndSubmitSignInForm(champs);
        
        checkNumberOfMessages(1);
        checkErrorMessage(Customer.ERREUR_EMAIL_USED);
        
    }
    
//==============================================================================

    private FluentListAssert checkNumberOfMessages(final int size) {
        return assertThat(findBaliseLi()).hasSize(size);
    }

    private void checkErrorMessage(final String message) {
        checkText(findBaliseLi(), message);
    }

    private void checkText(final FluentList baliseHTML, final String message) {
        assertThat(baliseHTML).hasText(message);
    }

    private void checkPageTitle(final FluentPage page) {
        assertThat(page).isAt();
    }
 
    private FluentList findBaliseLi() {
        return find("#message-error").find("li");
    }
}
