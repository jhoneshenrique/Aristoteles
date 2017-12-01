package com.example.android.aristoteles;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Resultados extends AppCompatActivity {

    private TextView mGrade, mFinalScore;
    private Button mRetryButton;
    private Button mSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        mGrade = (TextView)findViewById(R.id.grade);
        mFinalScore = (TextView)findViewById(R.id.outOf);
        mRetryButton = (Button)findViewById(R.id.retry);
        mSair = (Button) findViewById(R.id.fecharResultado);

        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("finalScore");

        Intent intentMail = getIntent();
        Bundle bundleMail = intentMail.getExtras();
        final String email = bundleMail.getString("emailUserRes");

        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.carregaDadoByEmail("'"+email+"'");

        String codigo;
        cursor.moveToPosition(0);
        codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID));
        crud.alteraRegistro(Integer.parseInt(codigo),1,score,email);

        mFinalScore.setText("Você acertou " + score + " de " + QuizData.questions.length);


        mRetryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Resultados.this, Quiz.class));
                Resultados.this.finish();
            }
        });

        mSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Resultados.this, Menu.class));
                Resultados.this.finish();
            }
        });
    }
}
