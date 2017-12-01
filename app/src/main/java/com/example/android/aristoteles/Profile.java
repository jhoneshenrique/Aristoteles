package com.example.android.aristoteles;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {
    private TextView pontos;
    private TextView status;
    private TextView nomeUsuario;
    private ImageView imageProf;
    private View viewBackground;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intentMail = getIntent();
        Bundle bundleMail = intentMail.getExtras();
        String nome = bundleMail.getString("nome");
        String email = bundleMail.getString("email");

        nomeUsuario = (TextView) findViewById(R.id.nome);
        imageProf = (ImageView) findViewById(R.id.imageProf);
        pontos = (TextView) findViewById(R.id.pontos);
        status = (TextView) findViewById(R.id.status);



        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.carregaDadoByEmail("'"+email+"'");

        String codigo;
        cursor.moveToPosition(0);
        codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID));

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        pontos.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.PONTOS)));
        int pt = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.PONTOS)));

        viewBackground = findViewById(R.id.backgroundProfile);

        if(pt>=9){
            status.setText("Golden");
            viewBackground.setBackgroundColor(Color.rgb(255,174,25));
            imageProf.setImageResource(R.drawable.trophyblack);
        }else{
            if(pt>=7&&pt<9){
                status.setText("Prata");
                viewBackground.setBackgroundColor(Color.rgb(117,115,127));
                imageProf.setImageResource(R.drawable.trophyblack);
            }else{
                if(pt>=5&&pt<7){
                    status.setText("Bronze");
                    viewBackground.setBackgroundColor(Color.rgb(205,127,50));
                    imageProf.setImageResource(R.drawable.trophyblack);
                }else{
                    status.setText("Melhorando");
                    viewBackground.setBackgroundColor(Color.rgb(50,205,127));
                    imageProf.setImageResource(R.drawable.account);
                }
            }
        }

        nomeUsuario.setText(nome);
        //emailUsuario.setText(email);



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
        AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
        builder.setMessage("Tem certeza que deseja sair do Profile?");
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
