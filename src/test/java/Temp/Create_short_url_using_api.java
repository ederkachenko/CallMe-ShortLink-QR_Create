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

public class Create_short_url_using_api {

    @Test
    public static void main(String[] args) {
        Configuration.browser = "chrome";
        System.setProperty("selenide.browser", "chrome");
        Configuration.browserCapabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");

/// --------------------------Инструкция!--------------------------
// Загрузить список тейбл айдишек в tid.csv и изменить плейс айди в теле String json

        try {
            File file = new File("D:\\csv for import\\QR\\tid.csv");
            FileReader fileReader = new FileReader(file); // поток который подключается к текстовому файлу
            BufferedReader bufferedReader = new BufferedReader(fileReader); // соединяем FileReader с BufferedReader
            //String urlName = "https://callme.in.ua/index?payload=";
           // String placeName = "GeminiRoastery@Antonovicha172";//Тут поменять название заведения! Формат: Linas@Zhylyanska5/60
            String tableNameFromCsv;




            while ((tableNameFromCsv = bufferedReader.readLine()) != null) {
                System.out.println(tableNameFromCsv); // выводим содержимое файла на экран построчно
//Шифруем номер стола
                HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead
                try {
                    HttpPost request = new HttpPost("https://api.callme.in.ua/short/link/v1/create");

                    String json = "{\n" +
                            "\"pid\":\"20c85db7-1702-4940-8903-31d6ba8a5374\",\n" +
                            "\"tid\":\""+tableNameFromCsv+"\"\n" +
                            "}";

                    StringEntity params =new StringEntity(json);
                    params.setContentType("application/json");

                    request.setEntity(params);
                    HttpResponse response = httpClient.execute(request);



                    System.out.println(response);

                    //handle response here...

                }catch (Exception ex) {

                    //handle exception here

                } finally {
                    //Deprecated
                    //httpClient.getConnectionManager().shutdown();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}