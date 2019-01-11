package com.example.geksor.testgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button
            buttonStart = findViewById(R.id.startGame),
            buttonQuit = findViewById(R.id.quit);

        buttonStart.setOnClickListener(this);
        buttonQuit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        if (v.getId() == R.id.startGame){
            i = new Intent(this, GameActivity.class);
            startActivity(i);
        }else if (v.getId() == R.id.quit){
            onBackPressed();
        }
    }
}
