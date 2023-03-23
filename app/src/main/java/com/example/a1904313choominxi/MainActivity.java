package com.example.a1904313choominxi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.os.CountDownTimer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int highlightedViewIndex;
    private boolean[] clickedViews;
    private int numClickedViews;
    private View highlightedViews;
    private Handler handler;
    private Runnable runnable;
    private int score;
    private CountDownTimer time;
    private GridLayout Layout;
    private List<View> textViews;
    private int level = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String name2 = intent.getStringExtra("name");
        load();
        Layout = findViewById(R.id.Layout);
        textViews = new ArrayList<>();
        for (int i = 0; i < Layout.getChildCount(); i++) {
            View child = Layout.getChildAt(i);
            if (child instanceof View) {
                View textView = (View) child;
                textView.setOnClickListener(this);
                textViews.add(textView);
            }
        }
        int xx = textViews.size();
        highlight();
        // Set timer for 5 seconds
        time = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the timer display
                getSupportActionBar().setTitle("Time remaining: " + millisUntilFinished / 1000);
            }
            @Override
            public void onFinish() {
                end();
            }
        }.start();
    }
    @Override
    public void onClick(View v) {
        if (v == highlightedViews) {
            score++;
            //scoreTextView.setText("Score: " + score);
            highlightedViews.setBackgroundColor(Color.GRAY);
            highlightedViews.setClickable(false);
            textViews.remove(highlightedViews);
            if (!textViews.isEmpty()) {
                highlight();
            } else {
                end();
            }
        }
    }
    private void load() {
        int levels = getIntent().getIntExtra("level", 1); // default to level 1
        int levelLoad = getResources().getIdentifier("level" + levels, "layout", getPackageName());
        setContentView(levelLoad);
    }
    public int getLevel() {
        // Get the level number from the intent that started the activity
        return getIntent().getIntExtra("level", 1);
    }
    private void highlight() {
        int index = new Random().nextInt(textViews.size());
        highlightedViews = textViews.get(index);
        highlightedViews.setBackgroundColor(Color.YELLOW);
        highlightedViews.setTag("highlighted");
    }
    private int end() {
        if (time != null) {
            time.cancel();
        }
        if (textViews.isEmpty()) {
            String name2= username();
            start(getIntent().getIntExtra("level", 1),name2);
            return getIntent().getIntExtra("level", 1);
        } else {
            int numClickableTextViews = Layout.getChildCount();
            int numClickedTextViews = numClickableTextViews - textViews.size();
            int score = numClickedTextViews;
            int level = getLevel();
            if(level == 2){
                score= score+4;
            }
            else if(level==3){score=score+4+9;}
            else if(level==4){score=score+4+9+16;}
            else if(level==5){score=score+4+9+16+25;}
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Game Over");
            builder.setMessage("You clicked " + numClickedTextViews + " out of " + numClickableTextViews + " TextViews in Level "+getLevel()+". Your score is " + score + ".");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(MainActivity.this, MainMenu.class);
                    startActivity(intent);
                    finish();
                }
            });
            boolean isHighScore = Leaderboard3.isHighScore(this, score);
            if (isHighScore) {
                Intent intent = getIntent();
                String name2 = intent.getStringExtra("name");
                Leaderboard3.addon(MainActivity.this, new Player(name2, score));
            };
            builder.setCancelable(false);
            builder.show();
            return getIntent().getIntExtra("level", 1);
        }
    }

    private void start(int level, String name) {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.putExtra("level", level+1);
        intent.putExtra("name", name);
        startActivity(intent);
        finish();
    }
    public String username(){
        Intent intent = getIntent();
        String Nam1 = intent.getStringExtra("name");
        return Nam1;
    }
}