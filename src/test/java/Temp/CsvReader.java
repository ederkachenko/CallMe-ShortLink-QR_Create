package Temp;

import java.io.*;
import java.util.ArrayList;

public class CsvReader {

    public static void main(String[] args) {
        try {
            File file = new File("D:\\csv for import\\LinksGGPR.csv");
            FileReader fileReader = new FileReader(file); // поток который подключается к текстовому файлу
            BufferedReader bufferedReader = new BufferedReader(fileReader); // соединяем FileReader с BufferedReader

            String line;
            String[] urlArrayImported = new String[10000]; // Массив пока что на 10тыщ
            int i =1;
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line); // выводим содержимое файла на экран построчно

                urlArrayImported[i] = line; //Записываем линк в массив
                i++;
                System.out.println(urlArrayImported[i-1]); // Выводим значение из массива для дебага
            }

            bufferedReader.close(); // закрываем поток
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}