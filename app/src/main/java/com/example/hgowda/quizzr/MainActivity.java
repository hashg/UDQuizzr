package com.example.hgowda.quizzr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public int index = 0;
    public ArrayList<View> list = new ArrayList<View>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        if (savedInstanceState != null) {
            scoreTeamIndia = savedInstanceState.getInt("scoreTeamIndia");
            ballsTeamIndia = savedInstanceState.getInt("ballsTeamIndia");
            wicketsTeamIndia = savedInstanceState.getInt("wicketsTeamIndia");

            scoreTeamSA = savedInstanceState.getInt("scoreTeamSA");
            ballsTeamSA = savedInstanceState.getInt("ballsTeamSA");
            wicketsTeamSA = savedInstanceState.getInt("wicketsTeamSA");
        }
        */
    }

    public void onBtnStart(View view) {
        Intent quizIntent = new Intent(MainActivity.this,QuizActivity.class);
        startActivity(quizIntent);
    }

    /*
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.

        savedInstanceState.putInt("scoreTeamIndia", scoreTeamIndia);
        savedInstanceState.putInt("ballsTeamIndia", ballsTeamIndia);
        savedInstanceState.putInt("wicketsTeamIndia", wicketsTeamIndia);

        savedInstanceState.putInt("scoreTeamSA", scoreTeamSA);
        savedInstanceState.putInt("ballsTeamSA", ballsTeamSA);
        savedInstanceState.putInt("wicketsTeamSA", wicketsTeamSA);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        scoreTeamIndia = savedInstanceState.getInt("scoreTeamIndia");
        ballsTeamIndia = savedInstanceState.getInt("ballsTeamIndia");
        wicketsTeamIndia = savedInstanceState.getInt("wicketsTeamIndia");

        scoreTeamSA = savedInstanceState.getInt("scoreTeamSA");
        ballsTeamSA = savedInstanceState.getInt("ballsTeamSA");
        wicketsTeamSA = savedInstanceState.getInt("wicketsTeamSA");
    }
    */

}
