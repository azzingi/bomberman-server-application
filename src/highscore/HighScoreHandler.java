package highscore;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nathanael on 29.11.2016.
 * Ausgewählte Klasse
 * Clean Code zu bestimmen
 */
public class HighScoreHandler {
    private HighscoreIntf highscore;

    public HighScoreHandler(String dbName) {
        highscore = Highscore.getInsctance(dbName);
    }

    public HighScoreHandler() {
        highscore = Highscore.getInsctance("bomberman");
    }

    /**
     * updates the winner of the game
     * @param playerName player to be set the points
     * @param points points to set to the player
     */
    public void updateWinner(String playerName, int points) {
        try {
            highscore.addScore(playerName,points);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts highscoreList to Map<String, Integer>
     * @return Map<String,Integer>
     */
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
