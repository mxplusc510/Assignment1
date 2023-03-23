package com.example.a1904313choominxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import java.util.List;

public class Leaderboard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);
        ListView Leaderboard = findViewById(R.id.history);
        List<Player> highScores = Leaderboard3.getScores(this);
        Learderboard2 adapter = new Learderboard2(this,  highScores);
        Leaderboard.setAdapter(adapter);
    }
}
