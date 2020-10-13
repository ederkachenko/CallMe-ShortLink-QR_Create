package Trash;


import java.util.HashMap;
import java.util.Map;

public class Learn {
    public static String GAME_SCORE = "2:1";

    public static void main(String[] args) {
        Map<String, String> users = new HashMap<>();
        users.put("Вася", "2:1");
        users.put("Коля", "2:0");
        users.put("Толя", "5:5");

        users.forEach((name, score) -> {
            Map<String, Integer> resultGamerScore = calculateUserScore(name, score);
            System.out.println(resultGamerScore);
        });
    }


    public static Map<String, Integer> calculateUserScore(String name, String score){

        Map<String, Integer> resultMap = new HashMap<>();

        // Сплитим результат игры на две отдельные цифры
        String[] gameScore = Learn.GAME_SCORE.split(":");
        int gameScore1 = Integer.parseInt(gameScore[0]);
        int gameScore2 = Integer.parseInt(gameScore[1]);

        // Сплитим результат игрока на две отдельные цифры
        String[] userScore = score.split(":");
        int userScore1 = Integer.parseInt(userScore[0]);
        int userScore2 = Integer.parseInt(userScore[1]);

        //Если результат точно угадан
        if(gameScore1==userScore1 && gameScore2==userScore2) {
            resultMap.put(name, 2);
        }
        //Победила первая команда и это угадано
        else if (gameScore1>gameScore2 && userScore1>userScore2) {
            resultMap.put(name, 1);
        }
        //Победила вторая команда и это угадано
        else if (gameScore1<gameScore2 && userScore1<userScore2) {
            resultMap.put(name, 1);
        }
        //Ничья и это угадано
        else if (gameScore1==gameScore2 && userScore1==userScore2) {
            resultMap.put(name, 1);
        }
        //Не угадано ничего
        else resultMap.put(name, 0);

        return resultMap;
    }
}
