package Temp;

import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;

import java.io.*;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DraftQR_4 {

    @Test
    public static void main(String[] args) {
        Configuration.browser = "chrome";
        System.setProperty("selenide.browser", "chrome");
        Configuration.browserCapabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");

        try {
            File file = new File("D:\\csv for import\\QR\\QR.csv");
            FileReader fileReader = new FileReader(file); // поток который подключается к текстовому файлу
            BufferedReader bufferedReader = new BufferedReader(fileReader); // соединяем FileReader с BufferedReader
            String urlName = "https://callme.in.ua/index?payload=";
            String placeName = "GeminiRoastery@Antonovicha172";//Тут поменять название заведения! Формат: Linas@Zhylyanska5/60
            String tableNameFromCsv;



            while ((tableNameFromCsv = bufferedReader.readLine()) != null) {
                System.out.println(tableNameFromCsv); // выводим содержимое файла на экран построчно
//Шифруем номер стола
                open("https://api.callme.in.ua/short-url/create/"+placeName+"/"+tableNameFromCsv);
                String сodeForQrCode=$(By.cssSelector("body > pre")).getText().split("rl\":\"")[1].split("\"}")[0];
                System.out.println(сodeForQrCode);
//Генерируем QR код с учетом шифрования
                open("http://qrcoder.ru/");
                Thread.sleep(1000);
                $(By.xpath("//*[@id=\"qr_s_10\"]")).click();
                $(By.xpath("//*[@id=\"pageContent\"]/div/div[2]/form/div[1]/div[2]/textarea")).setValue("https://callme.in.ua/short/"+сodeForQrCode);
                $(By.xpath("//*[@id=\"pageContent\"]/div/div[2]/form/div[2]/input")).click();
                String imageurl = $(By.xpath("//*[@id=\"pageContent\"]/div/div[1]/div[2]/div/input")).getValue();
//Сохраняем номера столов и соответствующие им шифры в csv
                try {
                    FileWriter writer = new FileWriter("D:\\csv for import\\QR\\Tables&Codes.csv", true);
                    BufferedWriter bufferWriter = new BufferedWriter(writer);
                    bufferWriter.write(tableNameFromCsv + "," + "https://" + "," + "https://callme.in.ua/short/"+сodeForQrCode);
                    bufferWriter.write("\n");
                    bufferWriter.close();
                }
                catch (IOException ie) {
                    System.out.println(ie);
                }
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
                FileOutputStream fos = new FileOutputStream("C:\\Users\\evde\\Downloads\\"+tableNameFromCsv+".jpg");
                fos.write(response);
                fos.close();


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}