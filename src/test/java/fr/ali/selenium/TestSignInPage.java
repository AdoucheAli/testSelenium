package fr.ali.selenium;

import fr.ali.business.entities.Customer;
import fr.ali.selenium.pages.SignInPage;
import fr.ali.selenium.pages.SignInSuccessPage;
import fr.ali.selenium.pages.SignUpPage;
import java.text.MessageFormat;
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

@SharedDriver(type = SharedDriver.SharedType.ONCE)
public class TestSignInPage extends FluentTest {

    @Page
    private SignInPage signInPage;
    
    @Page
    private SignUpPage signUpPage;
    
    @Page
    private SignInSuccessPage signInSuccessPage;
    
    private String[] champs = new String[2];

    @Before
    public void openBrowser() {
        goTo(signInPage);
        withDefaultPageWait(2, TimeUnit.SECONDS);
        withDefaultSearchWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void should_return_is_At_SignIn() {
        assertThat(signInPage).isAt();
    }

    @Test
    public void should_open_page_signInSuccess() {
        champs = Arrays.array("adoucheali@yahoo.fr", "adoucheali");
        signInPage.fillAndSubmitSignInForm(champs);
        await().untilPage(signInSuccessPage).isLoaded();
        
        assertThat(signInSuccessPage).isAt();   
    }
   
    @Test
    public void should_print_message_in_signInSuccessPage() {
        champs = Arrays.array("adoucheali@yahoo.fr", "adoucheali");
        signInPage.fillAndSubmitSignInForm(champs);
        await().untilPage(signInSuccessPage).isLoaded();

        assertThat(signInSuccessPage).isAt();
        String expected = MessageFormat.format("email : {0}, password : {1}", champs[0], champs[1]);
        checkText(find("p"), expected);
    }

    @Test
    public void should_should_go_to_Sign_Up_page() {
        click("#btnSignUp").await().untilPage(signUpPage).isLoaded();
        assertThat(signUpPage).isAt(); 
    }
    
    @Test
    public void should_return_2_errors() {
        signInPage.fillAndSubmitSignInForm();
        checkNumberOfErrors(2);
    }

    @Test
    public void should_return_password_is_empty_error() {
        champs = Arrays.array("adoucheali@yahoo.fr", "");
        signInPage.fillAndSubmitSignInForm(champs);

        checkNumberOfErrors(1);
        checkMessageText(Customer.ERREUR_PASSWORD_EMPTY);
    }

    @Test
    public void should_return_password_is_out_of_range_error() {
        champs = Arrays.array("adoucheali@yahoo.fr", "adouche");
        signInPage.fillAndSubmitSignInForm(champs);

        checkNumberOfErrors(1);
        checkMessageText(Customer.ERREUR_PASSWORD_OUT_OF_RANGE);

        champs[1] = "adoucheuuuuuuuuuuuuuuu";

        checkNumberOfErrors(1);
        checkMessageText(Customer.ERREUR_PASSWORD_OUT_OF_RANGE);
    }

    @Test
    public void should_return_email_is_empty_error() {
        champs = Arrays.array("", "adoucheali");
        signInPage.fillAndSubmitSignInForm(champs);

        checkNumberOfErrors(1);
        checkMessageText(Customer.ERREUR_EMAIL_EMPTY);
    }
    
    @Test
    public void should_return_email_or_password_wrong() {
        champs = Arrays.array("adouche@yahoo.fr", "adouchealii");
        signInPage.fillAndSubmitSignInForm(champs);
        checkNumberOfErrors(1);
        checkMessageText(Customer.ERREUR_EMAIL_OR_PASSWORD_WRONG);
        
        champs[1] = "adouchealii";

        checkNumberOfErrors(1);
        checkMessageText(Customer.ERREUR_EMAIL_OR_PASSWORD_WRONG);
    }

    @Test
    public void should_return_email_is_invalid_error() {
        champs = Arrays.array("adoucheali", "adoucheali");
        signInPage.fillAndSubmitSignInForm(champs);
        
        checkNumberOfErrors(1);
        checkMessageText(Customer.ERREUR_EMAIL_INVALID);
    } 
//==============================================================================

    private FluentListAssert checkNumberOfErrors(final int size) {
        return assertThat(find("#erreurSaisieSignIn").find("li")).hasSize(size);
    }

    private void checkMessageText(final String message) {
        checkText(find("#erreurSaisieSignIn").find("li"), message);
    }

    private void checkText(final FluentList baliseHTML, final String message) {
        assertThat(baliseHTML).hasText(message);
    }
}
