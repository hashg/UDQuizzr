package com.example.hgowda.quizzr;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;
/**
 * Created by hgowda on 1/29/2018.
 */

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        Log.v("ResultActivity", String.valueOf(QuizActivity.results));
    }

    public void onBtnExit(View view) {
        Intent mainIntent = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }
}
