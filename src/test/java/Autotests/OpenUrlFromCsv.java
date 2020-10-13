package Autotests;

import bsh.Capabilities;
import com.codeborne.selenide.*;
import com.codeborne.selenide.testng.ScreenShooter;
import org.junit.Test;
import org.openqa.selenium.remote.CapabilityType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;

public class OpenUrlFromCsv {

    @Test
    public static void main(String[] args) {
        Configuration.browser = "chrome";
        System.setProperty("selenide.browser", "chrome");
        try {
            File file = new File("D:\\csv for import\\Links.csv");
            FileReader fileReader = new FileReader(file); // поток который подключается к текстовому файлу
            BufferedReader bufferedReader = new BufferedReader(fileReader); // соединяем FileReader с BufferedReader
            String urlFromCsv;

            while ((urlFromCsv = bufferedReader.readLine()) != null) {          System.out.println(urlFromCsv); open(urlFromCsv);               executeJavaScript("window.scrollBy(0,99999999999)", "");                Thread.sleep(1000); executeJavaScript("window.scrollBy(0,-99999999999)", "");; //Скроллим страницу до конца
            }
    //
            bufferedReader.close(); // закрываем поток
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}