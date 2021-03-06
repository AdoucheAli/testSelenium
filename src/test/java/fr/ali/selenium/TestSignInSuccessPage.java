package fr.ali.selenium;

import fr.ali.selenium.pages.SignInPage;
import fr.ali.selenium.pages.SignInSuccessPage;
import java.text.MessageFormat;
import static org.fest.assertions.fluentlenium.FluentLeniumAssertions.assertThat;
import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.adapter.util.SharedDriver;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.junit.Test;
/**
 *
 * @author Adouche Ali
 */
@SharedDriver(type = SharedDriver.SharedType.ONCE)
public class TestSignInSuccessPage extends FluentTest {

    @Page
    private SignInPage signInPage;

    @Page
    private SignInSuccessPage signInSuccessPage;

    @Test
    public void should_return_is_At_SignIn() {
        goTo(signInSuccessPage);
        checkPageTitle(signInPage);//obligation de se logguer pour acceder à la page signInSuccess
        checkText(find("#erreurSaisieSignIn").find("li"), "veuillez vous authentifier pour acceder à la page signInSuccess.xhtml");
    }
    
    @Test
    public void should_return_is_At_SignInSuccess() {
        goTo(signInSuccessPage);
        checkPageTitle(signInPage);
        String[] champs = {"adoucheali@yahoo.fr", "adoucheali"};
        signInPage.fillAndSubmitSignInForm(champs);
        await().untilPage(signInSuccessPage).isLoaded();
        
        checkPageTitle(signInSuccessPage);
    }
    
    @Test
    public void should_logOut() {
        goTo(signInPage);
        String[] champs = {"adoucheali@yahoo.fr", "adoucheali"};
        signInPage.fillAndSubmitSignInForm(champs);
        await().untilPage(signInSuccessPage).isLoaded();
        
        checkPageTitle(signInSuccessPage);
        
        String expected = MessageFormat.format("email : {0}, password : {1}", champs[0], champs[1]);
        checkText(find("p"), expected);
        
        click("#btnLogout");
        await().untilPage(signInPage).isLoaded();
        
        checkPageTitle(signInPage);   
    }
 //=============================================================================================   
    
    private void checkText(final FluentList baliseHTML, final String message) {
        assertThat(baliseHTML).hasText(message);
    }

    private void checkPageTitle(final FluentPage page) {
        assertThat(page).isAt();
    }

}
