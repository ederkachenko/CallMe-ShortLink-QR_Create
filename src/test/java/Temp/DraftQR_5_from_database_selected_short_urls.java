package Temp;

import com.codeborne.selenide.Configuration;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;

import java.io.*;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DraftQR_5_from_database_selected_short_urls {

    @Test
    public static void main(String[] args) {
        Configuration.browser = "chrome";
        System.setProperty("selenide.browser", "chrome");
        Configuration.browserCapabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");

//        --------------------------Инструкция!--------------------------
// Выгрузить шортурлы - загрузить в short_urls.csv и выгрузить номера столов - загрузить в table_name.csv таким образом, чтоб строчки со столом в одном файле = строчке с соответсвующим кодом в другом
        try {
            File table_name = new File("D:\\csv for import\\QR\\short_urls_from_db\\table_name.csv");
            File short_urls = new File("D:\\csv for import\\QR\\short_urls_from_db\\short_urls.csv");
            FileReader fileReader = new FileReader(table_name); // поток который подключается к текстовому файлу
            BufferedReader bufferedReader = new BufferedReader(fileReader); // соединяем FileReader с BufferedReader
            FileReader fileReader1 = new FileReader(short_urls); // поток который подключается к текстовому файлу
            BufferedReader bufferedReader1 = new BufferedReader(fileReader1); // соединяем FileReader с BufferedReader
            String tableNameFromCsv;
            String tableName;


            while ((tableNameFromCsv = bufferedReader1.readLine()) != null) {
//Генерируем QR код с учетом шифрования
                open("http://qrcoder.ru/");
                Thread.sleep(1000);
                $(By.xpath("//*[@id=\"qr_s_10\"]")).click();
                $(By.xpath("//*[@id=\"pageContent\"]/div/div[2]/form/div[1]/div[2]/textarea")).setValue(tableNameFromCsv+"?read=QR");
                $(By.xpath("//*[@id=\"qr_s_10\"]")).click();
                $(By.xpath("//*[@id=\"pageContent\"]/div/div[2]/form/div[2]/input")).click();
                String imageurl = $(By.xpath("//*[@id=\"pageContent\"]/div/div[1]/div[2]/div/input")).getValue();

//Сохраняем картинку
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
                tableName = bufferedReader.readLine();
                FileOutputStream fos = new FileOutputStream("C:\\Users\\evde\\Downloads\\"+tableName+".jpg");
                fos.write(response);
                fos.close();
//Сохраняем номера столов и соответствующие им шифры в csv
                try {
                    FileWriter writer = new FileWriter("D:\\csv for import\\QR\\Tables&Codes.csv", true);
                    BufferedWriter bufferWriter = new BufferedWriter(writer);
                    bufferWriter.write(tableName + "," + tableNameFromCsv);
                    bufferWriter.write("\n");
                    bufferWriter.close();
                }
                catch (IOException ie) {
                    System.out.println(ie);
                }
                System.out.println(tableName); // выводим содержимое файла на экран построчно
                System.out.println(tableNameFromCsv); // выводим содержимое файла на экран построчно
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}