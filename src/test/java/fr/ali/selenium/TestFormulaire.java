package fr.ali.selenium;

import fr.ali.jsf.ChampsForm;
import static org.fest.assertions.fluentlenium.FluentLeniumAssertions.assertThat;
import org.fest.assertions.fluentlenium.custom.FluentListAssert;
import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.adapter.util.SharedDriver;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.junit.Before;
import org.junit.Test;

@SharedDriver(type = SharedDriver.SharedType.PER_CLASS)
public class TestFormulaire extends FluentTest {

    @Page
    public IndexPage indexPage;
    
    @Page
    public SignInSuccessPage signInSuccessPage;

    @Before
    public void openBrowser() {
        goTo(indexPage);
    }

    @Test
    public void should_return_is_At_Acceuil() {
        assertThat(indexPage).isAt();
    }

    @Test
    public void should_return_2_errors() {
        indexPage.fillAndSubmitSignInForm();
        checkSize(2);
    }

    @Test
    public void should_return_password_is_empty_error() {
        indexPage.fillAndSubmitSignInForm("adoucheali@yahoo.fr", "");

        checkSize(1);
        checkText(ChampsForm.ERREUR_PASSWORD_EMPTY);
    }

    @Test
    public void should_return_password_is_out_of_range_error() {
        indexPage.fillAndSubmitSignInForm("adoucheali@yahoo.fr", "adouche");

        checkSize(1);
        checkText(ChampsForm.ERREUR_PASSWORD_OUT_OF_RANGE);

        indexPage.fillAndSubmitSignInForm("adoucheali@yahoo.fr", "adoucheuuuuuuuuuuuuuuu");

        checkSize(1);
        checkText(ChampsForm.ERREUR_PASSWORD_OUT_OF_RANGE);
    }

    @Test
    public void should_return_email_is_empty_error() {
        indexPage.fillAndSubmitSignInForm("", "adoucheali");

        checkSize(1);
        checkText(ChampsForm.ERREUR_EMAIL_EMPTY);
    }

    @Test
    public void should_return_email_is_invalid_error() {
        indexPage.fillAndSubmitSignInForm("adoucheali", "adoucheali");

        checkSize(1);
        checkText(ChampsForm.ERREUR_EMAIL_INVALID);
    }

    @Test
    public void should_open_page_signInSuccess() {
        indexPage.fillAndSubmitSignInForm("adoucheali@yahoo.fr", "adoucheali");
        goTo(signInSuccessPage);
        assertThat(signInSuccessPage).isAt();   
    }
    
    @Test
    public void should_print_message_in_signInSuccessPage() {
        indexPage.fillAndSubmitSignInForm("adoucheali@yahoo.fr", "adoucheali");
        goTo(signInSuccessPage);
        assertThat(signInSuccessPage).isAt();
        checkText(find("p"), "email : adoucheali@yahoo.fr, password : adoucheali");
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
