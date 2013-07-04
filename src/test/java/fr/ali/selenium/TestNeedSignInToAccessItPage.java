package fr.ali.selenium;

import fr.ali.selenium.pages.NeedSignInToAccessItPage;
import fr.ali.selenium.pages.SignInPage;
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
public class TestNeedSignInToAccessItPage extends FluentTest {
  
    @Page
    private NeedSignInToAccessItPage needSignInToAccessItPage;

    @Page
    private SignInPage signInPage;

    @Test
    public void should_return_is_At_SignIn() {
        goTo(needSignInToAccessItPage);
        checkPageTitle(signInPage);//obligation de se logguer pour acceder à la page signInSuccess
        checkText(find("#erreurSaisieSignIn").find("li"), "veuillez vous authentifier pour acceder à la page needSignInToAccessIt.xhtml");
    }
    
    @Test
    public void should_return_is_At_needSignInToAccessIt() {
        goTo(needSignInToAccessItPage);
        checkPageTitle(signInPage);
        String[] champs = {"adoucheali@yahoo.fr", "adoucheali"};
        signInPage.fillAndSubmitSignInForm(champs);
        await().untilPage(needSignInToAccessItPage).isLoaded();
        
        checkPageTitle(needSignInToAccessItPage);
    }
//=============================================================================
    
    private void checkText(final FluentList baliseHTML, final String message) {
        assertThat(baliseHTML).hasText(message);
    }

    private void checkPageTitle(final FluentPage page) {
        assertThat(page).isAt();
    }
}
