package Autotests;

import au.com.bytecode.opencsv.CSVReader;
import org.junit.Test;
import org.openqa.selenium.By;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PopupToSQLinputConvertFromCsv {
    @Test
    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader(new FileReader("D:\\csv for import\\Popup automation to SQL\\popupPubidSelector.csv"), ',', '"', 1); // Read all publishers and selectors from csv
        List<String[]> allRows = reader.readAll(); //Read all rows at once
//Read CSV line by line and use the string array
        for(String[] row : allRows){
            String pub_id = Arrays.toString(row).replace("[", "").replace("]", "").split(",")[0];
            String PopupSelector = Arrays.toString(row).replace("[", "").replace("]", "").split(", ")[1];
            System.out.println(pub_id);
            System.out.println(PopupSelector+"\n");
//Js code generation (For browser console manual pop-up closing)
                try {
                    String jsForPopupClose =
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


                            /*("setTimeout(() => {\n" +
                                    "    var selector = \"" + PopupSelector + "\";\n" +
                                    "    var el = document.querySelector(selector);\n" +
                                    "    if (el && el.click) {\n" +
                                    "        el.click();\n" +
                                    "    }\n" +
                                    "}, 5000)");*/
                    System.out.println(jsForPopupClose+"\n"); // Вывод в консоль js для закрытия попапа

                    byte[] bytes = jsForPopupClose.getBytes("UTF-8");
                    String textJsForDatabase = Base64.getEncoder().encodeToString(bytes);
// SQL insert into database generation. Manually must be verified such fields: COUNTRY_ID, IS_ENABLED, CHANNEL
                    String sqlinsert = new String("INSERT INTO ADC2_OPS.SCRAPER_DOMAIN_ATTRIBUTES (TYPE, CONTENT, PUBLISHER_ID, COUNTRY_ID, IS_ENABLED, CHANNEL)\n" +
                            "VALUES ('SCRIPT', '"+textJsForDatabase+"', "+ pub_id +", 3, true, 0);");
                    System.out.println(sqlinsert+"\n");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------"+"\n");
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
