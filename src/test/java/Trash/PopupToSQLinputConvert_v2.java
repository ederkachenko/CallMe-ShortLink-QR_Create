package Trash;

import au.com.bytecode.opencsv.CSVReader;
import org.junit.Test;
import org.openqa.selenium.By;

import java.io.*;
import java.util.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class PopupToSQLinputConvert_v2 {
    @Test
    public static void main(String[] args) throws IOException {
// Read all publishers and selectors from csv
        CSVReader reader = new CSVReader(new FileReader("D:\\csv for import\\Popup automation to SQL\\popupPubidSelector.csv"), ',', '"', 1);
//Read all rows at once
        List<String[]> allRows = reader.readAll();
//Read CSV line by line and use the string array
        for(String[] row : allRows){
            String pub_id = Arrays.toString(row).replace("[", "").replace("]", "").split(",")[0];
            String PopupSelector = Arrays.toString(row).replace("[", "").replace("]", "").split(", ")[1];

            System.out.println(pub_id);
            System.out.println(PopupSelector);

//Js code generation (For pop-up closing)
                try {
                    open("http://base64.ru/");
                    $(By.xpath("//*[@id=\"bobody\"]/form/p[1]/textarea")).setValue(PopupSelector);
                    String base64Text = $(By.xpath("//*[@id=\"bobody\"]/form/p[3]/textarea")).getValue();
                    String jsForDatabase =
                            ("setTimeout(() => {\n" +
                                    "    var selector = \"" + PopupSelector + "\";\n" +
                                    "    var el = document.querySelector(selector);\n" +
                                    "    if (el && el.click) {\n" +
                                    "        el.click();\n" +
                                    "    }\n" +
                                    "}, 1000)");
                    System.out.println(jsForDatabase); // Вывод в консоль js для закрытия попапа
                    $(By.xpath("//*[@id=\"bobody\"]/form/p[1]/textarea")).setValue(jsForDatabase);
                    String textJsForDatabase = $(By.xpath("//*[@id=\"bobody\"]/form/p[3]/textarea")).getValue();
// SQL insert into database generation. Manually must be verified such fields: COUNTRY_ID, IS_ENABLED, CHANNEL
                    String sqlinsert = new String("INSERT INTO ADC2_OPS.SCRAPER_DOMAIN_ATTRIBUTES (TYPE, CONTENT, PUBLISHER_ID, COUNTRY_ID, IS_ENABLED, CHANNEL)\n" +
                            "VALUES ('SCRIPT', '"+textJsForDatabase+"', "+ pub_id +", 8, true, 1);");
                    System.out.println(sqlinsert);
//Write sql into csv
                    try {
                        FileWriter writer = new FileWriter("D:\\csv for import\\Popup automation to SQL\\SQLInsert.csv", true);
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
