package Autotests;

import Settings.ChromeDriverClass;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

import static com.codeborne.selenide.Selenide.*;

public class PageDownForChromeExtensionTesting {

    @Test
    public static void main(String[] args) {
        Configuration.startMaximized=true; // open Chrome as Fullscreen
        Configuration.reportsFolder = "D:\\SelenideScreenshots"; // Folders for screenshots
        //Configuration.proxyEnabled=true;
        //Configuration.proxyHost = "209.126.103.245";
        //Configuration.proxyPort = 30119;

        try {
            File file = new File("D:\\csv for import\\Links.csv"); // List of websites to test
            FileReader fileReader = new FileReader(file); // Stream to connect to csv file
            BufferedReader bufferedReader = new BufferedReader(fileReader); //  FileReader connect to BufferedReader
            String urlFromCsv;
            open("https://www.google.com/");
            Thread.sleep(40000); // Time for manually open Chrome extension

            while ((urlFromCsv = bufferedReader.readLine()) != null) {
                System.out.println(urlFromCsv); // Print URLs to console, for create log
                open(urlFromCsv); // Open next url from file
                //String screenshotName =  Arrays.toString(new String[]{urlFromCsv}).replace("http://", "").replace("[", "").replace("]", ""); // Remove from screen name "http://"
                //screenshot(""+screenshotName+".png"); // Save screenshot

                for (int i = 0; i <5 ; i++) {
                Thread.sleep(8000); // Wait 10sec
                executeJavaScript("window.scrollBy(0,700)", ""); //Scroll down for 700 pixels
                }
                executeJavaScript("window.scrollBy(0,999999999)", ""); //Scroll down to the end of tha page
                Thread.sleep(8000); // Wait 10sec
                executeJavaScript("window.scrollBy(0,-9999999999)", ""); //Scroll up to the begin of tha page
                Thread.sleep(8000); // Wait 10sec
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}