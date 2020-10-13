package Trash;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static String GAME_SCORE = "1:1";

    public static void main(String[] args) {
        Map<String, String> playerScore = new HashMap<>();
        playerScore.put("Вася", "2:0");
        playerScore.put("Петя", "1:1");
        playerScore.put("Саша", "2:1");

        Map<String, Integer> calculateUsersScore = Main.calculateUsersScore(playerScore);
        System.out.println("Players score = " + calculateUsersScore);
        System.out.println("Game score = " + GAME_SCORE);
    }

    public static Map<String, Integer> calculateUsersScore(Map<String, String> playerScore) {
        Map<String, Integer> userScores = new HashMap<>();
        playerScore.forEach((name, score) -> {
            int calcScore = Main.calculateSingleScore(score);
            userScores.put(name, calcScore);
        });

        return userScores;
    }

    public static int calculateSingleScore(String playerScore){
        String gameScoreParts[] = GAME_SCORE.split(":");
        int gamescore1 = Integer.parseInt(gameScoreParts[0]);
        int gamescore2 = Integer.parseInt(gameScoreParts[1]);

        String playSoreParts[] = playerScore.split(":");
        int score1 = Integer.parseInt(playSoreParts[0]);
        int score2 = Integer.parseInt(playSoreParts[1]);
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