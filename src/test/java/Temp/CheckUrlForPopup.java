package Temp;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.*;

import org.junit.Test;


public class CheckUrlForPopup {
        @Test
        public static void main(String[] args) {

            Configuration.browser = "chrome";
            System.setProperty("selenide.browser", "chrome");


            String URL = "https://www.motor-talk.de/";
            open(URL);
            //$(By.id("editMode")).shouldBe(visible).click();
            Selenide.atBottom();
            executeJavaScript("window.scrollBy(0,250000)", "");
            executeJavaScript("window.scrollBy(0,250000)", "");
            executeJavaScript("window.scrollBy(0,250000)", "");


            open("http://cardgamesolitaire.com/");
            executeJavaScript("window.scrollBy(0,250000)", "");
            executeJavaScript("window.scrollBy(0,250000)", "");
            executeJavaScript("window.scrollBy(0,250000)", "");

            open("http://cartograf.fr");
            executeJavaScript("window.scrollBy(0,250000)", "");
            executeJavaScript("window.scrollBy(0,250000)", "");
            executeJavaScript("window.scrollBy(0,250000)", "");


        }
    }

