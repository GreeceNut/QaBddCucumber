package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.val;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement header = $("[data-test-id=dashboard]");

    public void verifyIsDashboardPage() {
        header.shouldBe(visible);
    }

    //поиск по номеру карты
//    public int getCardBalance(DataHelper.CardInfo cardInfo) {
//        var text = cards.findBy(text(cardInfo.getCardNumber().substring(15))).getText();
//        return extractBalance(text);
//    }

    // метод нажимающий на кнопку. поиск по атрибуту
//        public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
//        cards.findBy(attribute("data-test-id", cardInfo.getId())).$("button").click();
//        return new TransferPage();
    //}
    //Метод возвращающий баланс по индексу
    public int getCardBalance(int index) {
        var text = cards.get(index-1).getText();
        return extractBalance(text);
    }

    public TransferPage selectCardToTransfer(int index) {
        cards.get(index-1)
                .$("button").click();
        return new TransferPage();
    }


        private int extractBalance (String text){
            var start = text.indexOf(balanceStart);
            var finish = text.indexOf(balanceFinish);
            var value = text.substring(start + balanceStart.length(), finish);
            return Integer.parseInt(value);
        }


    }
