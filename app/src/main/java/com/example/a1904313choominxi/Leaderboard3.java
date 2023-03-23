package com.example.a1904313choominxi;
import android.content.Context;
import android.content.SharedPreferences;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Leaderboard3 {

    private static final String PREFS_NAME = "HighScoreList";
    private static final String PREF_KEY = "HighScores";
    private static final Gson gson = new GsonBuilder().create();
    private static final int Leaderboard_max = 25;
    public static boolean isHighScore(Context context, int score) {
        List<Player> highScores = getScores(context);
        if (highScores.size() < Leaderboard_max) {
            return true;
        }
        for (Player highScore : highScores) {
            if (score > highScore.getScore()) {
                return true;
            }
        }
        return false;
    }
    public static void addon(Context context, Player highScore) {
        List<Player> highScores = getScores(context);
        highScores.add(highScore);
        Sort(highScores);
        remove(highScores);
        save(context, highScores);
    }
    public static List<Player> getScores(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String highScoresJson = prefs.getString(PREF_KEY, "");
        if (!highScoresJson.isEmpty()) {
            Type type = new TypeToken<List<Player>>(){}.getType();
            return gson.fromJson(highScoresJson, type);
        }
        return new ArrayList<>();
    }
    private static void Sort(List<Player> highScores) {
        Collections.sort(highScores, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return Integer.compare(o2.getScore(), o1.getScore());
            }
        });
    }
    private static void remove(List<Player> highScores) {
        if (highScores.size() > Leaderboard_max) {
            highScores.subList(Leaderboard_max, highScores.size()).clear();
        }
    }
    private static void save(Context context, List<Player> highScores) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREF_KEY, gson.toJson(highScores));
        editor.apply();
    }
}

