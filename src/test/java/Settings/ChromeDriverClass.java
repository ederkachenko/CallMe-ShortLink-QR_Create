package Settings;

import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


public class ChromeDriverClass {

    @Test
    public static void main(String args) {
//Если нужен не инкогнито
        Configuration.browser = "chrome";
        System.setProperty("selenide.browser", "chrome");

//Если нужен инкогнито
        WebDriver chromedriver;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-incognito");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        chromedriver = new ChromeDriver(capabilities);
        chromedriver.get("https://www.google.com/");
    }
}