package com.example.android.aristoteles;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Quiz extends AppCompatActivity {

    private TextView mScoreView, mQuestion;
    private ImageView mImageView;
    private Button mTrueButton, mFalseButton;

    private boolean mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mScoreView = (TextView)findViewById(R.id.points);
        mImageView = (ImageView)findViewById(R.id.imageView);
        mQuestion = (TextView)findViewById(R.id.question);
        mTrueButton = (Button)findViewById(R.id.trueButton);
        mFalseButton = (Button)findViewById(R.id.falseButton);

        Intent intentMail = getIntent();
        Bundle bundleMail = intentMail.getExtras();
        final String email = bundleMail.getString("email");


        updateQuestion();


        //Logic for true button
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mAnswer == true) {
                    mScore++;
                    updateScore(mScore);


                    if (mQuestionNumber == QuizData.questions.length) {
                        Intent i = new Intent(Quiz.this, Resultados.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", mScore);
                        bundle.putString("emailUserRes",email);
                        i.putExtras(bundle);
                        Quiz.this.finish();
                        startActivity(i);
                    } else {
                        updateQuestion();
                    }
                }
                else {
                    if (mQuestionNumber == QuizData.questions.length) {
                        Intent i = new Intent(Quiz.this, Resultados.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", mScore);
                        bundle.putString("emailUserRes",email);
                        i.putExtras(bundle);
                        Quiz.this.finish();
                        startActivity(i);
                    } else {
                        updateQuestion();
                    }
                }
            }
        });


        //Logic for false button
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mAnswer == false) {
                    mScore++;
                    updateScore(mScore);

                    //perform check before you update the question
                    if (mQuestionNumber == QuizData.questions.length) {
                        Intent i = new Intent(Quiz.this, Resultados.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", mScore);
                        bundle.putString("emailUserRes",email);
                        i.putExtras(bundle);
                        Quiz.this.finish();
                        startActivity(i);
                    } else {
                        updateQuestion();
                    }
                }
                else {
                    if (mQuestionNumber == QuizData.questions.length) {
                        Intent i = new Intent(Quiz.this, Resultados.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", mScore);
                        bundle.putString("emailUserRes",email);
                        i.putExtras(bundle);
                        Quiz.this.finish();
                        startActivity(i);
                    } else {
                        updateQuestion();
                    }
                }
            }
        });




    }

    //Atualiza a questão na tela do usuário
    private void updateQuestion() {
        //mImageView.setImageResource(QuizData.images[mQuestionNumber]);
        mQuestion.setText(QuizData.questions[mQuestionNumber]);
        mAnswer = QuizData.answers[mQuestionNumber];
        mQuestionNumber++;
    }

    //Atualiza o Score
    private void updateScore(int point) {
        mScoreView.setText("" + mScore);
    }


    //Fecha o Quiz
    public void clickExit(View view) {
        askToClose();
    }

    @Override
    public void onBackPressed() {
        askToClose();
    }


    private void askToClose (){
        AlertDialog.Builder builder = new AlertDialog.Builder(Quiz.this);
        builder.setMessage("Tem certeza que deseja sair do Quiz?");
        builder.setCancelable(true);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
