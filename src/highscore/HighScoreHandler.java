package highscore;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nathanael on 29.11.2016.
 */
public class HighScoreHandler {
    private HighscoreIntf highscore;

    public HighScoreHandler() {
        highscore = Highscore.getInsctance("bomberman");
    }

    public void updateWinner(String playerName, int points) {
        try {
            highscore.addScore(playerName,points);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String,Integer> getHighscoreList() {
        Map<String, Integer> highscoreList = new HashMap<>();

        try {
            for (Player p : highscore.getTopTenPlayer()) {
                highscoreList.putIfAbsent(p.getName(), p.getScore());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return highscoreList;
    }
}
