package highscore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nathanael on 29.11.2016.
 */
public class HighScoreHandler {
    private HighscoreIntf highscore;

    public HighScoreHandler() {
        highscore = Highscore.getInstance("bomberman");
    }

    public void updateWinner(String playerName, int points) {
        highscore.addScore(playerName,points);
    }

    public Map<String,Integer> getHighscoreList() {
        Map<String, Integer> highscoreList = new HashMap<>();

        for (Player p : highscore.getTopTenPlayer()) {
            highscoreList.putIfAbsent(p.getName, p.getScore);
        }

        return highscoreList;
    }
}
