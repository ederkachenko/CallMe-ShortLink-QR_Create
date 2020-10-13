package Trash;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Score {
    public static String GAME_SCORE = "2:0";

    public static void main(String[] args) {

        Map<String, String> playerScore = new HashMap<>();
        playerScore.put("Вася", "0:2");
        playerScore.put("Петя", "2:2");
        playerScore.put("Саша", "3:5");

        playerScore.forEach((name, score) -> {
            int userScore = calculateScore(name, score);
            System.out.println("User name is " + name + "User score = " +userScore);
        });

        System.out.println("Счет игры = " + GAME_SCORE);
    }

    public static int calculateScore(String playerName, String playerScore){
        String gameScoreParts[] = GAME_SCORE.split(":");
        int gamescore1 = Integer.parseInt(gameScoreParts[0]);
        int gamescore2 = Integer.parseInt(gameScoreParts[1]);

        String playSoreParts[] = playerScore.split(":");
        int score1 = Integer.parseInt(playSoreParts[0]);
        int score2 = Integer.parseInt(playSoreParts[1]);

        if (gamescore1==score1 && gamescore2==score2) {
            return 2;
        }

        if (gamescore1>gamescore2 && score1>score2) {
            return 1;
        }

        if (gamescore1<gamescore2 && score1<score2) {
            return 1;
        }

        return 0;
    }
}
