package com.example.android.aristoteles;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PerguntasAula extends AppCompatActivity {

    private Button resposta1, resposta2, resposta3, resposta4;

    private TextView question;

    private String respCorreta;
    private int flag = 0;
    private int count = 0;
    private String dica;

    PerguntasData perguntas = new PerguntasData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_aula);

        resposta1 = (Button) findViewById(R.id.btQuestion1);
        resposta2 = (Button) findViewById(R.id.btQuestion2);
        resposta3 = (Button) findViewById(R.id.btQuestion3);
        resposta4 = (Button) findViewById(R.id.btQuestion4);


        question = (TextView) findViewById(R.id.question);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        flag = bundle.getInt("int");

        this.updateQuestion(flag);

        resposta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(resposta1.getText() == respCorreta){
                    Toast toast = Toast.makeText(getApplicationContext(), perguntas.dicaRespCorreta(flag),Toast.LENGTH_LONG);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            finish();
                        }
                    }, 4000);

                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), perguntas.getHint1(flag),Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        resposta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(resposta2.getText() == respCorreta){
                    Toast toast = Toast.makeText(getApplicationContext(), perguntas.dicaRespCorreta(flag),Toast.LENGTH_LONG);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            finish();
                        }
                    }, 4000);

                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), perguntas.getHint2(flag),Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        resposta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(resposta3.getText() == respCorreta){
                    Toast toast = Toast.makeText(getApplicationContext(), perguntas.dicaRespCorreta(flag),Toast.LENGTH_LONG);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            finish();
                        }
                    }, 4000);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), perguntas.getHint3(flag),Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        resposta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(resposta4.getText() == respCorreta){
                    Toast toast = Toast.makeText(getApplicationContext(), perguntas.dicaRespCorreta(flag),Toast.LENGTH_LONG);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            finish();
                        }
                    }, 4000);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), perguntas.getHint4(flag),Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    public void updateQuestion(int num){
        question.setText(perguntas.getQuestion(num));
        resposta1.setText(perguntas.getChoice1(num));
        resposta2.setText(perguntas.getChoice2(num));
        resposta3.setText(perguntas.getChoice3(num));
        resposta4.setText(perguntas.getChoice4(num));
        respCorreta = perguntas.resposta(num);
    }

    public void setFlag(int num){
        flag = num;
    }
}
