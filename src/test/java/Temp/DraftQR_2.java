package Temp;

import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;

import java.io.*;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DraftQR_2 {

    @Test
    public static void main(String[] args) {
        Configuration.browser = "chrome";
        System.setProperty("selenide.browser", "chrome");
        Configuration.browserCapabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");

        try {
            File file = new File("D:\\csv for import\\QR.csv");
            FileReader fileReader = new FileReader(file); // поток который подключается к текстовому файлу
            BufferedReader bufferedReader = new BufferedReader(fileReader); // соединяем FileReader с BufferedReader
            String name = "https://callme.in.ua/index?place=Linas@Zhylyanska5/60&table=";
            String idFromCsv;
            open("http://qrcoder.ru/");
            Thread.sleep(10000); // Ждем 10 сек


            while ((idFromCsv = bufferedReader.readLine()) != null) {
                System.out.println(idFromCsv); // выводим содержимое файла на экран построчно
                $(By.xpath("//*[@id=\"qr_s_10\"]")).click();
                $(By.xpath("//*[@id=\"pageContent\"]/div/div[2]/form/div[1]/div[2]/textarea")).setValue(name+idFromCsv);
                $(By.xpath("//*[@id=\"pageContent\"]/div/div[2]/form/div[2]/input")).click();
                String imageurl = $(By.xpath("//*[@id=\"pageContent\"]/div/div[1]/div[2]/div/input")).getValue();


//Копипейст с интернета по Сохранению картинки
                URL url = new URL(imageurl);
                InputStream in = new BufferedInputStream(url.openStream());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int n = 0;
                while (-1!=(n=in.read(buf)))
                {
                    out.write(buf, 0, n);
                }
                out.close();
                in.close();
                byte[] response = out.toByteArray();
//Сохраняем QR код
                FileOutputStream fos = new FileOutputStream("C:\\Users\\evde\\Downloads\\"+idFromCsv+".jpg");
                fos.write(response);
                fos.close();


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}