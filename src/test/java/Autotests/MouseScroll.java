package Autotests;

import com.codeborne.selenide.Configuration;
import org.junit.Test;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static com.codeborne.selenide.Selenide.*;

public class MouseScroll {
        @Test

        public static void main(String[] args) throws AWTException {
            Configuration.browser = "chrome";
            System.setProperty("selenide.browser", "chrome");
            Robot robot2 = new Robot();
            try {
                File file = new File("D:\\csv for import\\Links.csv");
                FileReader fileReader = new FileReader(file); // поток который подключается к текстовому файлу
                BufferedReader bufferedReader = new BufferedReader(fileReader); // соединяем FileReader с BufferedReader
                String urlFromCsv;

                while ((urlFromCsv = bufferedReader.readLine()) != null) {
                    System.out.println(urlFromCsv); // выводим содержимое файла на экран построчно, для дебаг режима
                    open(urlFromCsv); // Открываем Линк из BufferedReader
                    for (int i = 0; i <2 ; i++) {
                        robot2.delay(1000);
                        robot2.mouseMove(963, 250);
                        robot2.mouseWheel(150);


                        System.out.println();
                    } //Скроллим страницу, эмуляция мыши
                        Thread.sleep(1000); // Ждем по 1й секунде

                    }
                    executeJavaScript("window.scrollBy(0,999999999)", ""); //Скроллим страницу до конца
                    Thread.sleep(1000); // Ждем 1 секунду


                bufferedReader.close(); // закрываем поток
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }