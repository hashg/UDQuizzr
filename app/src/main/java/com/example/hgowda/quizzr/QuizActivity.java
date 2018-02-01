package com.example.hgowda.quizzr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hgowda on 1/29/2018.
 */

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    public ArrayList<Quiz> questionsList = new ArrayList<Quiz>() {{
//        add(new Quiz(R.id.quiz_ques_01, QTYPE mType, int[] mChoices, int[] mAnswer, int imageResourceId ))
        add(new Quiz(R.string.quiz_ques_08, Quiz.QTYPE.ONE, new int[]{R.string.quiz_choice_08a, R.string.quiz_choice_08b}, new int[]{2}, R.drawable.andromeda));
        add(new Quiz(R.string.quiz_ques_05, Quiz.QTYPE.MANY, new int[]{R.string.quiz_choice_05a, R.string.quiz_choice_05b, R.string.quiz_choice_05c, R.string.quiz_choice_05d}, new int[]{1, 2}));
        add(new Quiz(R.string.quiz_ques_04, Quiz.QTYPE.ONE, new int[]{R.string.quiz_choice_04a, R.string.quiz_choice_04b}, new int[]{1}));
        add(new Quiz(R.string.quiz_ques_01, Quiz.QTYPE.FREEFORM, new int[]{R.string.quiz_choice_01a}, new int[1]));
        add(new Quiz(R.string.quiz_ques_03, Quiz.QTYPE.ONE, new int[]{R.string.quiz_choice_03a, R.string.quiz_choice_03b}, new int[]{2}));
        add(new Quiz(R.string.quiz_ques_02, Quiz.QTYPE.MANY, new int[]{R.string.quiz_choice_02a, R.string.quiz_choice_02b, R.string.quiz_choice_02c, R.string.quiz_choice_02d}, new int[]{1, 3}));
        add(new Quiz(R.string.quiz_ques_06, Quiz.QTYPE.ONE, new int[]{R.string.quiz_choice_06a, R.string.quiz_choice_06b}, new int[]{1}));
        add(new Quiz(R.string.quiz_ques_07, Quiz.QTYPE.ONE, new int[]{R.string.quiz_choice_07a, R.string.quiz_choice_07b}, new int[]{2}));

    }};
    View quizContainer, submitContainer, resultContainer, checkboxGroup;
    ImageView imageView;
    TextView quizTextView, submitTextView, resultTextView;
    RadioGroup radioGroup;
    RadioButton[] radioArray ;
    CheckBox[] checkboxArray ;
    EditText editText ;
    Button btnNext, btnSubmit, btnExit;
    private Quiz currentQuestion;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quizContainer = findViewById(R.id.qa_quiz_view);
        submitContainer = findViewById(R.id.qa_submit_view);
        resultContainer = findViewById(R.id.qa_result_view);

        imageView = findViewById(R.id.qa_image_view);
        quizTextView = findViewById(R.id.qa_tv_text);
        submitTextView = findViewById(R.id.qa_submit_text);
        resultTextView = findViewById(R.id.qa_result_text);

        radioGroup = findViewById(R.id.qa_radio_group);
        checkboxGroup = findViewById(R.id.qa_checbox_group);

        radioArray = new RadioButton[] {findViewById(R.id.qa_radio_01), findViewById(R.id.qa_radio_02)};
        checkboxArray = new CheckBox[] {findViewById(R.id.qa_checkbox_01), findViewById(R.id.qa_checkbox_02), findViewById(R.id.qa_checkbox_03), findViewById(R.id.qa_checkbox_04)};
        editText = findViewById(R.id.qa_edit_text);

        btnNext = findViewById(R.id.qa_btn_next);
        btnNext.setOnClickListener(this);

        btnSubmit = findViewById(R.id.qa_btn_submit);
        btnSubmit.setOnClickListener(this);

        btnExit = findViewById(R.id.qa_btn_exit);
        btnExit.setOnClickListener(this);

        displayQuiz();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*All buttons*/
            case R.id.qa_btn_next:
                checkAnswer();
                if (index >= questionsList.size() - 1) {
                    quizContainer.setVisibility(View.GONE);
                    submitContainer.setVisibility(View.VISIBLE);
                    return;
                }
                index += 1;

                displayQuiz();
                break;

            case R.id.qa_btn_submit:
                submitContainer.setVisibility(View.GONE);
                resultContainer.setVisibility(View.VISIBLE);
                displayResults();
                break;

            case R.id.qa_btn_exit:
                Intent intent = new Intent(QuizActivity.this, MainActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    public void checkAnswer() {
        currentQuestion = questionsList.get(index);
        int[] ans = currentQuestion.getAnswer();
        int[] userChoices = new int[]{0, 0, 0, 0};

        if (currentQuestion.getType() == Quiz.QTYPE.ONE) {
            for (int i = 0; i < radioArray.length; i++) {
                if (radioArray[i].isChecked()) {
                    currentQuestion.setUserChoices(new int[]{i + 1});
                    if (i + 1 == ans[0]) {
                        currentQuestion.setResult(true);
                        Log.d("QuizActCheckAnswerRadio", String.valueOf(i) + ":" + String.valueOf(ans[0] - 1));
                    }
                }
            }
        } else if (currentQuestion.getType() == Quiz.QTYPE.MANY) {
            for (int i = 0, j = 0; i < checkboxArray.length && j < ans.length; i++) {
                if (checkboxArray[i].isChecked()) {
                    userChoices[i] = i + 1;
                    currentQuestion.setResult(true);
                    if (i + 1 != ans[j]) {
                        currentQuestion.setResult(false);
                    }
                    Log.d("QuizActCheckAnswerCheck", String.valueOf(i) + ":" + String.valueOf(ans[j] - 1));
                    j++;
                }
            }
            currentQuestion.setUserChoices(userChoices);
        } else /* FREEFORM) */ {
            if (editText.getText().toString().compareToIgnoreCase(getString(currentQuestion.getChoices()[0])) == 0)
                currentQuestion.setResult(true);
            Log.v("QuizActCheckAnswerCheck", editText.getText() + ":" + getString(currentQuestion.getChoices()[0]));
        }
    }

    public void resetAnswer() {
        editText.setText("");

        for (CheckBox checkbox : checkboxArray) {
            checkbox.setChecked(false);
        }

        radioGroup.clearCheck();
    }

    public void displayQuiz() {
        resetAnswer();

        imageView.setVisibility(View.GONE);
        radioGroup.setVisibility(View.GONE);
        checkboxGroup.setVisibility(View.GONE);
        editText.setVisibility(View.GONE);

        currentQuestion = questionsList.get(index);
        quizTextView.setText(currentQuestion.getQuestion());

        if (currentQuestion.hasImage()) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(currentQuestion.getImageResourceId());
        }

        if (currentQuestion.getType() == Quiz.QTYPE.ONE) {
            radioGroup.setVisibility(View.VISIBLE);

            int idx = 0;
            for (int choice:currentQuestion.getChoices()) {
                radioArray[idx++].setText(choice);
            }

        } else if (currentQuestion.getType() == Quiz.QTYPE.MANY) {
            checkboxGroup.setVisibility(View.VISIBLE);

            int idx = 0;
            for (int choice:currentQuestion.getChoices()) {
                checkboxArray[idx++].setText(choice);
            }

        } else /* FREEFORM) */ {
            editText.setVisibility(View.VISIBLE);
            editText.requestFocus();
        }
    }

    public void displayResults() {

        int count = 0;
        for (int i = 0; i < questionsList.size(); i++) {
            Log.d("QuizActivity::Results", questionsList.get(i).toString());
            if (questionsList.get(i).getResult()) {
                count++;
            }
        }

        resultTextView.setText(count + "/" + questionsList.size());
    }


}
