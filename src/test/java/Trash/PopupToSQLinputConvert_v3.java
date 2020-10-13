package Trash;

import au.com.bytecode.opencsv.CSVReader;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class PopupToSQLinputConvert_v3 {
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
                            ("var bisTimerIDs = [];\n" +
                                    "function BisClearTimers() {\n" +
                                    "   if (bisTimerIDs && bisTimerIDs.length) {\n" +
                                    "     for (var i = 0; i < bisTimerIDs.length; i++) {\n" +
                                    "\t   clearTimeout(bisTimerIDs[i]);\n" +
                                    "     }\n" +
                                    "\t bisTimerIDs = [];\n" +
                                    "   }\n" +
                                    "}\n" +
                                    "function BisClosePopup() {     \n" +
                                    "\ttry {\t  \t \n" +
                                    "\t   var selector = \"" + PopupSelector + "\";\n" +
                                    "\t   var el = document.querySelector(selector);\n" +
                                    "\t   if (el && el.click) {\n" +
                                    "\t\t el.click();\n" +
                                   /* "\t\t BisClearTimers();\n" +*/
                                    "\t   }\n" +
                                    "\t } catch (e) {\n" +
                                    "\t   console.log('error 1');\n" +
                                    "\t } \n" +
                                    "}\n" +
                                    "for (var i = 0; i < 10; i++) {\n" +
                                    "  var timeout = 1000 + i * 500;\n" +
                                    "  bisTimerIDs.push(setTimeout(BisClosePopup, timeout));\n" +
                                    "}");

                    System.out.println(jsForPopupClose+"\n"); // Вывод в консоль js для закрытия попапа

                    byte[] bytes = jsForPopupClose.getBytes("UTF-8");
                    String textJsForDatabase = Base64.getEncoder().encodeToString(bytes);
// SQL insert into database generation. Manually must be verified such fields: COUNTRY_ID, IS_ENABLED, CHANNEL
                    String sqlinsert = new String("INSERT INTO ADC2_OPS.SCRAPER_DOMAIN_ATTRIBUTES (TYPE, CONTENT, PUBLISHER_ID, COUNTRY_ID, IS_ENABLED, CHANNEL)\n" +
                            "VALUES ('SCRIPT', '"+textJsForDatabase+"', "+ pub_id +", 3, true, 2);");
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
