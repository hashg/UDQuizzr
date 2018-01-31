package com.example.hgowda.quizzr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hgowda on 1/29/2018.
 */

public class QuizActivity extends AppCompatActivity {
    private ArrayList<Quiz> questionsList = new ArrayList<Quiz>() {{
//        add(new Quiz(R.id.quiz_ques_01, QTYPE mType, int[] mChoices, int[] mAnswer, int imageResourceId ))
//        add(new Quiz(R.string.))
        add(new Quiz(R.string.quiz_ques_01, Quiz.QTYPE.FREEFORM, new int[]{R.string.quiz_choice_01a}, new int[1]));
        add(new Quiz(R.string.quiz_ques_02, Quiz.QTYPE.MANY, new int[]{R.string.quiz_choice_02a,R.string.quiz_choice_02b,R.string.quiz_choice_02c,R.string.quiz_choice_02d}, new int[]{1,3}));
        add(new Quiz(R.string.quiz_ques_03, Quiz.QTYPE.ONE, new int[]{R.string.quiz_choice_03a, R.string.quiz_choice_03b}, new int[]{2}));
        add(new Quiz(R.string.quiz_ques_04, Quiz.QTYPE.ONE, new int[]{R.string.quiz_choice_04a,R.string.quiz_choice_04b}, new int[]{1}));
    }};

    private Quiz currentQuestion;
    static int results;
    private int index = 0;

    TextView quizTextView ;
    View radioGroup ;
    View checkboxGroup ;
    RadioButton[] radioArray ;
    CheckBox[] checkboxArray ;
    EditText editText ;

    Button btnPrev ;
    Button btnNext ;
    Button btnSubmit ;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quizTextView = (TextView) findViewById(R.id.qa_tv_text);

        radioGroup = findViewById(R.id.qa_radio_group);
        checkboxGroup = findViewById(R.id.qa_checbox_group);

        radioArray = new RadioButton[] {findViewById(R.id.qa_radio_01), findViewById(R.id.qa_radio_02)};
        checkboxArray = new CheckBox[] {findViewById(R.id.qa_checkbox_01), findViewById(R.id.qa_checkbox_02), findViewById(R.id.qa_checkbox_03), findViewById(R.id.qa_checkbox_04)};
        editText = findViewById(R.id.qa_edit_text);

        btnPrev = findViewById(R.id.qa_btn_prev);
        btnNext = findViewById(R.id.qa_btn_next);
        btnSubmit = findViewById(R.id.qa_btn_submit);

        displayQuiz();
    }

    public void onBtnSubmit(View view) {
        Intent resultIntent = new Intent(QuizActivity.this, ResultActivity.class);
        startActivity(resultIntent);
    }

    public void onBtnNext(View view) {
        if (index >= questionsList.size()-1) {
            btnPrev.setVisibility(View.GONE);
            btnNext.setVisibility(View.GONE);
            btnSubmit.setVisibility(View.VISIBLE);
            return;
        }
        index += 1;
        displayQuiz();
    }

    public void onBtnPrev(View view) {
        if (index < 0)
            return;
        index -= 1;
        displayQuiz();
    }

    public int getResults() {
        return results;
    }

    public void displayQuiz() {
        currentQuestion = questionsList.get(index);
        quizTextView.setText(currentQuestion.getQuestion());

        radioGroup.setVisibility(View.GONE);
        checkboxGroup.setVisibility(View.GONE);
        editText.setVisibility(View.GONE);

        if (currentQuestion.getType() == Quiz.QTYPE.ONE) {
            radioGroup.setVisibility(View.VISIBLE);

            int idx = 0;
            for (int choice:currentQuestion.getChoices()) {
                radioArray[idx++].setText(choice);
//                idx += idx;
                Log.d("QuizActivity", String.valueOf(choice) + " " + String.valueOf(idx));
            }
        } else if (currentQuestion.getType() == Quiz.QTYPE.MANY) {
            checkboxGroup.setVisibility(View.VISIBLE);

            int idx = 0;
            for (int choice:currentQuestion.getChoices()) {
                checkboxArray[idx++].setText(choice);
//                idx += idx;
                Log.d("QuizActivity", String.valueOf(choice));
            }
        } else /* FREEFORM) */ {
            editText.setVisibility(View.VISIBLE);
        }
    }


}
