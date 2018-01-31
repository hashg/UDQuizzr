package com.example.hgowda.quizzr;


import android.content.res.Resources;

import java.util.Arrays;

/**
 * {@link Quiz} represents a structure to store question, answers and options. Along with verification logic.
 * It contains resource IDs for the Question, answers and optional image file for that question.
 */

/**
 * Sample data
 *  { "Question 1", QTYPE.ONE, ["Answer11","Answer12","Answer13","Answer14"], [2] }
 *  { "Question 2", QTYPE.FREEFORM, ["Answer21"], [0], "a.jpg" }
 *  { "Question 3", QTYPE.MANY, ["Answer31","Answer32","Answer33","Answer34"], [0,3]  }
 *
 */

public class Quiz {

    /** Constant value that represents no image is provided */
    private static final int NO_IMAGE_RESOURCE = -1;

    public enum QTYPE {
        ONE,
        MANY,
        FREEFORM
    }

    /** String resource ID for the Question */
    private int mQuestion;

    /** ENUM information indicating what type of answer is expected for the Question */
    private QTYPE mType;

    /** String resource ID array of answer options */
    private int[] mChoices;

    /** Index locations of the correct answer/s in mChoices array */
    private int[] mAnswer;

    /** Image resource ID for the Question */
    private int mImageResourceId = NO_IMAGE_RESOURCE;

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public QTYPE getType() {
        return mType;
    }

    public void setType(QTYPE mType) {
        this.mType = mType;
    }

    public int[] getChoices() {
        return mChoices;
    }

    public void setChoices(int[] mChoices) {
        this.mChoices = mChoices;
    }

    public int[] getAnswer() {
        return mAnswer;
    }

    public void setAnswer(int[] mAnswer) {
        this.mAnswer = mAnswer;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public void setImageResourceId(int mImageResourceId) {
        this.mImageResourceId = mImageResourceId;
    }

    public Quiz(int mQuestion, QTYPE mType, int[] mChoices, int[] mAnswer ) {
        this.mQuestion = mQuestion;
        this.mType = mType;
        this.mChoices = mChoices;
        this.mAnswer = mAnswer;
    }

    public Quiz(int mQuestion, QTYPE mType, int[] mChoices, int[] mAnswer, int imageResourceId ) {
        this.mQuestion = mQuestion;
        this.mType = mType;
        this.mChoices = mChoices;
        this.mAnswer = mAnswer;
        this.mImageResourceId = imageResourceId;
    }

    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_RESOURCE;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "mQuestion='" + mQuestion + '\'' +
                ", mAnswer='" + mAnswer + '\'' +
                ", mChoices=" + Arrays.toString(mChoices) +
                ", mType=" + mType +
                '}';
    }
}
