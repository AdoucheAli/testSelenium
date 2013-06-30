package fr.ali.selenium;

import fr.ali.business.entities.Customer;
import fr.ali.selenium.pages.SignInPage;
import fr.ali.selenium.pages.SignInSuccessPage;
import fr.ali.selenium.pages.SignUpPage;
import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;
import static org.fest.assertions.fluentlenium.FluentLeniumAssertions.assertThat;
import org.fest.util.Arrays;
import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.adapter.util.SharedDriver;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.junit.Before;
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
    public void should_return_is_At_SignInSuucess() {
        goTo(signInSuccessPage);
        assertThat(signInSuccessPage).isAt();
    }
    
    @Test
    public void should_logOut() {
        goTo(signInPage);
        String[] champs = {"adoucheali@yahoo.fr", "adoucheali"};
        signInPage.fillAndSubmitSignInForm(champs);
        await().untilPage(signInSuccessPage).isLoaded();
        
        assertThat(signInSuccessPage).isAt();
        String expected = MessageFormat.format("email : {0}, password : {1}", champs[0], champs[1]);
        checkText(find("p"), expected);
        
        click("#btnLogout");
        await().untilPage(signInPage).isLoaded();
        
        goTo(signInSuccessPage);
        await().untilPage(signInSuccessPage).isLoaded();
        
        assertThat(signInSuccessPage).isAt();
        expected = "email : , password :";
        checkText(find("p"), expected);
        
    }
 //=============================================================================================   
    
    private void checkText(final FluentList baliseHTML, final String message) {
        assertThat(baliseHTML).hasText(message);
    }

}
