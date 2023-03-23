package com.example.a1904313choominxi;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainMenu extends AppCompatActivity {
    private Button StartButton;
    private Button Scorebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        StartButton = findViewById(R.id.StartButtons);
        Scorebutton = findViewById(R.id.Scorebutton);
        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = getUserName();
                String nam1e = getUserName();
                start(1, name);
            }
        });
        Scorebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the high scores
                String name = getUserName();
                Intent intent = new Intent(MainMenu.this, Leaderboard.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void start(int level, String name) {
        Intent intent = new Intent(MainMenu.this, MainActivity.class);
        intent.putExtra("level", level);
        intent.putExtra("name", name);
        startActivity(intent);
        finish();
    }
    public String getUserName() {
        EditText editText = findViewById(R.id.Name);
        Intent intent = new Intent(this, MainActivity.class);
        String name = editText.getText().toString();
        intent.putExtra("name", name);
        return name;
    }
}