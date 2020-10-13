package Autotests;
import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.util.Base64;
import java.util.Scanner;

import static com.codeborne.selenide.Selenide.*;
public class PopupToSQLinputConvertManual {
    @Test
    public static void main(String[] args) throws IOException {
        //Configuration.browser = "chrome";
        //System.setProperty("selenide.browser", "chrome");

// Перед запуском скрипта - Ввести сюда следующий айди из базы
        for (int i = 696; i < 10000; i++) {
//Тут Вводим Pub_id
            Scanner pubIdReader = new Scanner(System.in);  // Reading from System.in
            System.out.println("Input Pub Id: ");
            int pub_id = pubIdReader.nextInt();
//Тут Вводим Селектор попапа
            Scanner selectorReader = new Scanner(System.in);  // Reading from System.in
            System.out.println("Input Popup Selector: ");
            String PopupSelector = selectorReader.nextLine(); // Scans the next token of the input as an String.
//Обработка флоу, генерация js кода для закрытия попапа
            try {
                open("http://base64.ru/");
                $(By.xpath("//*[@id=\"bobody\"]/form/p[1]/textarea")).setValue(PopupSelector);
                String base64Text = $(By.xpath("//*[@id=\"bobody\"]/form/p[3]/textarea")).getValue();
                String jsForDatabase =
                ("function BisClosePopup() {\n" +
                        " try {\n" +
                        "   var selector = \"" + PopupSelector + "\";\n" +
                        "   var el = document.querySelector(selector);\n" +
                        "   if (el && el.click) {\n" +
                        "    el.click();\n" +
                        "   }\n" +
                        " } catch (e) {\n" +
                        "   console.log('error 1');\n" +
                        " }\n" +" }\n" +
                        "setTimeout(BisClosePopup, 500);\n" +
                        "setTimeout(BisClosePopup, 1000);\n" +
                        "setTimeout(BisClosePopup, 2000);\n" +
                        "setTimeout(BisClosePopup, 3000);\n" +
                        "setTimeout(BisClosePopup, 4000);");
                System.out.println(jsForDatabase); // Вывод в консоль js для закрытия попапа
                $(By.xpath("//*[@id=\"bobody\"]/form/p[1]/textarea")).setValue(jsForDatabase);
                String textJsForDatabase = $(By.xpath("//*[@id=\"bobody\"]/form/p[3]/textarea")).getValue();
//Сюда надо подставить нужные параметры инсерта в базу: COUNTRY_ID, IS_ENABLED, CHANNEL
                String sqlinsert = new String("INSERT INTO ADC2_OPS.SCRAPER_DOMAIN_ATTRIBUTES (TYPE, CONTENT, PUBLISHER_ID, COUNTRY_ID, IS_ENABLED, CHANNEL)\n" +
                        "VALUES ('SCRIPT', '"+textJsForDatabase+"', "+ pub_id +", 8, true, 1);");
                System.out.println(sqlinsert);
//Пишем инсерт в csv
                try {
                    FileWriter writer = new FileWriter("D:\\csv for import\\SQLInsert.csv", true);
                    BufferedWriter bufferWriter = new BufferedWriter(writer);
                    bufferWriter.write(sqlinsert);
                    bufferWriter.write("\n");
                    bufferWriter.close();
                }
                catch (IOException ie) {
                    System.out.println(ie);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}