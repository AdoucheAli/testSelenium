/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ali.selenium;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Adouche Ali
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    fr.ali.selenium.TestSignInPage.class,
    fr.ali.selenium.TestSignUpPage.class,
    fr.ali.selenium.TestSignInSuccessPage.class
})
public class TestSuiteFormulaires {
    
}
