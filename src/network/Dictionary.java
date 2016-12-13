package network;

import java.util.HashMap;
import java.util.Map;

/**
 * dictionary to store name of player with connectionId of player
 * Created by Nathanael on 22.11.2016.
 */
public class Dictionary {
    private static Dictionary dictionary = new Dictionary();
    private Map<String, String> dict = new HashMap<>();

    private Dictionary() {}

    public static Dictionary getInstance() {
        return dictionary;
    }

    public void add(String playerName, String connectionId) {
        dict.putIfAbsent(playerName, connectionId);
    }

    public String get(String playerName) {
        return dict.get(playerName);
    }
}
