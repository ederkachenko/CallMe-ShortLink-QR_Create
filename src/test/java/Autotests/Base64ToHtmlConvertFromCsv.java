package Autotests;

import au.com.bytecode.opencsv.CSVReader;
import com.codeborne.selenide.Selenide;
import io.netty.handler.codec.base64.Base64Decoder;
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

public class Base64ToHtmlConvertFromCsv {
    @Test
    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader(new FileReader("D:\\csv for import\\base64tohtml.csv"), ',', '"', 1); // Read all publishers and selectors from csv
        List<String[]> allRows = reader.readAll(); //Read all rows at once
//Read CSV line by line and use the string array
        for(String[] row : allRows){
            //byte[] asd = Arrays.toString(row);
            //String value = Base64.getDecoder().decode();
            //System.out.println(value);
           // String value1 = Base64.getEncoder().encodeToString(value);
            //System.out.println(value1);
            open("https://htmledit.squarefree.com/");

          //  $(By.cssSelector("body > form > textarea")).setValue(String.valueOf(value1));


        }
    }
}
