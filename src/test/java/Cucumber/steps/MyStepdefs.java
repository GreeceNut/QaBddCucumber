package Cucumber.steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import data.DataHelper;
import io.cucumber.java.Before;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.chrome.ChromeOptions;
import page.DashboardPage;
import page.LoginPage;
import page.VerificationPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.*;
import static data.DataHelper.getCardSecond;
import static org.junit.Assert.assertEquals;

public class MyStepdefs {

    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;
    int firstCardBalance;
    int secondCardBalance;

    @Before
    public void prepareData() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        Configuration.browserCapabilities = options;
    }


    @Пусть("открыта страница с формой авторизации {string}")
    public void openAuthPage(String url) {
        loginPage = Selenide.open(url, LoginPage.class);

    }

    @Когда("пользователь пытается авторизоваться с именем {string} и паролем {string}")
    public void loginWithNameAndPassword(String login, String password) {
        verificationPage = loginPage.validLogin(login, password);
    }

    @И("пользователь вводит проверочный код 'из смс' {string}")
    public void setValidCode(String code) {
        dashboardPage = verificationPage.validVerify(code);

    }

    @Тогда("происходит успешная авторизация и пользователь попадает на страницу 'Личный кабинет'")
    public void verifyDashboardPage() {
        dashboardPage.verifyIsDashboardPage();
    }

    @Когда("пользователь переводит {int} рублей с карты с номером {string} на свою {int} карту с главной страницы,")
    public void transfer(int amount, String secondCardInfo, int firstCardInfo ) {


        firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
        var transferPage = dashboardPage.selectCardToTransfer(firstCardInfo);
        firstCardBalance = firstCardBalance + amount;
        secondCardBalance = secondCardBalance - amount;
        transferPage.validTransfer(String.valueOf(amount), secondCardInfo);

    }

    @Тогда("баланс его {int} карты из списка на главной странице должен стать {int} рублей.")
    public void checkBalanceCard(int firstCardInfo, int sum) {

       assertEquals(sum,dashboardPage.getCardBalance(firstCardInfo));


    }
}
