package Temp;

import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.By;

import java.io.*;
import java.net.URL;

import static com.codeborne.selenide.Selenide.*;

public class DraftQR {

    @Test
    public static void main(String[] args) {
        Configuration.browser = "chrome";
        System.setProperty("selenide.browser", "chrome");

        try {
            File file = new File("D:\\csv for import\\QR.csv");
            FileReader fileReader = new FileReader(file); // поток который подключается к текстовому файлу
            BufferedReader bufferedReader = new BufferedReader(fileReader); // соединяем FileReader с BufferedReader
            String name = "https://callme.in.ua/index?place=Evrasia@ShotaRustaveli14&table=";
            String idFromCsv;
            open("https://www.the-qrcode-generator.com/");
            int i = 6;
            int k=10;
            int j=5;

            while ((idFromCsv = bufferedReader.readLine()) != null) {
                System.out.println(idFromCsv); // выводим содержимое файла на экран построчно
                $(By.xpath("//*[@id=\"input_5\"]")).setValue(name+idFromCsv);
                Thread.sleep(500);
                $(By.xpath("/html/body/div/md-content/ng-view/div/div[1]/div/div/div[2]/canvas")).click();
                Thread.sleep(500);
                $(By.xpath("/html/body/div/md-content/ng-view/div/div[1]/div/md-toolbar/div/button[1]/span")).click();
                $(By.xpath("//*[@id=\"input_"+i+"\"]")).setValue(idFromCsv).pressEnter();
                $(By.xpath("//*[@id=\"dialog_"+k+"\"]/div/button[2]/span")).click();
                i=i+j;
                k=k+j;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}