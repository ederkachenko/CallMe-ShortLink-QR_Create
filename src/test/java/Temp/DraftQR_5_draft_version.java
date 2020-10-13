package Temp;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import org.apache.commons.io.output.AppendableOutputStream;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.BHttpConnectionBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DraftQR_5_draft_version {

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
                HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead
                try {
                    HttpPost request = new HttpPost("https://api.callme.in.ua/short/link/v1/create");

                    String json = "{\n" +
                            "\"pid\":\"6eb21c7e-4943-4136-a803-ee76c879a8ea\",\n" +
                            "\"tid\":\"fb0613e5-b008-4160-9f33-eed8e0c7620e\"\n" +
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