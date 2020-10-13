package Autotests;

import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.remote.CapabilityType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

import static com.codeborne.selenide.Selenide.*;

public class OpenUrl_Screenshots {

    @Test
    public static void main(String[] args) {
        Configuration.browser = "chrome";
        System.setProperty("selenide.browser", "chrome");
        Configuration.reportsFolder = "D:\\SelenideScreenshots";

        try {
            File file = new File("D:\\csv for import\\Links.csv");
            FileReader fileReader = new FileReader(file); // поток который подключается к текстовому файлу
            BufferedReader bufferedReader = new BufferedReader(fileReader); // соединяем FileReader с BufferedReader
            String urlFromCsv;

            while ((urlFromCsv = bufferedReader.readLine()) != null) {
                Configuration.startMaximized=true;
                Configuration.browserCapabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none"); // не ждем догрузки страницы
                Configuration.proxyEnabled=true;
                Configuration.proxyHost = "209.126.103.245";
                Configuration.proxyPort = 30119;
                System.out.println(urlFromCsv);
                open(urlFromCsv);
                Thread.sleep(60000);
                String screenshotName =  Arrays.toString(new String[]{urlFromCsv}).replace("http://", "").replace("[", "").replace("]", ""); // убираем "http://"
                screenshot(""+screenshotName+".png"); //Сохраняем скрин
                close();

            }
    //
            bufferedReader.close(); // закрываем поток
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}