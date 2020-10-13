package Trash;

import java.util.HashMap;
import java.util.Map;

public class Score2 {
    public static String GAME_SCORE = "2:0";

    public static void main(String[] args) {
        //Хешмапа с именами и счетом
        Map<String, String> playerScore = new HashMap<>();
        playerScore.put("Вася", "2:0");
        playerScore.put("Петя", "2:2");
        playerScore.put("Саша", "1:0");

        Map<String, Integer> scores= new HashMap<>();

        //Перебираем мапу и вычисляем результат по каждому игроку. Сразу же прописываем имя и результат в scores
        playerScore.forEach((name, score)-> {
            scores.put(name, calculateScore(score));
        });

        System.out.println(scores);
        System.out.println("Game result is " + GAME_SCORE);
    }

    public static int calculateScore(String playerScore){
        String gameScoreParts[] = GAME_SCORE.split(":");

        int gamescore1 = Integer.parseInt(gameScoreParts[0]);
        int gamescore2 = Integer.parseInt(gameScoreParts[1]);

        String playScoreParts[] = playerScore.split(":");
        int score1 = Integer.parseInt(playScoreParts[0]);
        int score2 = Integer.parseInt(playScoreParts[1]);

        //Счет угадан точно
        if (gamescore1==score1 && gamescore2==score2) {
            return 2;
        }
        //Победила первая команда и это угадано
        if (gamescore1>gamescore2 && score1>score2) {
            return 1;
        }
        //Победила вторая команда и это угадано
        if (gamescore1<gamescore2 && score1<score2) {
            return 1;
        }
        //Ничья и это угадано
        if (gamescore1==gamescore2 && score1==score2) {
            return 1;
        }
        //Не угадано ничего
        return 0;
    }
}
