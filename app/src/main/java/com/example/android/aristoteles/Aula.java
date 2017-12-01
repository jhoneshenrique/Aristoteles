package com.example.android.aristoteles;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class Aula extends AppCompatActivity {

    private ImageView imagemAula;
    private int count = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula);

        imagemAula = (ImageView) findViewById(R.id.imagemAula);

        final Button button = (Button)findViewById(R.id.btProximo);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                count++;

                if(count<=26) {

                    imagemAula.setImageBitmap(getBitmapFromAssets("x"+count+".png"));

                    Intent secondActivity = new Intent(Aula.this, PerguntasAula.class);
                    Bundle bundle = new Bundle();



                    switch (count){
                        case 8:

                            bundle.putInt("int",0);
                            secondActivity.putExtras(bundle);
                            startActivity(secondActivity);
                            break;

                        case 11:
                            bundle.putInt("int",1);
                            secondActivity.putExtras(bundle);
                            startActivity(secondActivity);
                            break;

                        case 14:
                            bundle.putInt("int",2);
                            secondActivity.putExtras(bundle);
                            startActivity(secondActivity);
                            break;

                        case 21:
                            bundle.putInt("int",3);
                            secondActivity.putExtras(bundle);
                            startActivity(secondActivity);
                            break;

                        case 25:
                            bundle.putInt("int",4);
                            secondActivity.putExtras(bundle);
                            startActivity(secondActivity);

                            Intent intentMail = getIntent();
                            Bundle bundleMail = intentMail.getExtras();
                            String email = bundleMail.getString("email");

                            BancoController crud = new BancoController(getBaseContext());
                            Cursor cursor = crud.carregaDadoByEmail("'"+email+"'");

                            String codigo;
                            cursor.moveToPosition(0);
                            codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID));
                            crud.alteraRegistro(Integer.parseInt(codigo),1,0,email);
                            count++;
                            break;
                    }
                }else{
                    Intent secondActivity = new Intent(Aula.this, Menu.class);
                    startActivity(secondActivity);
                }


            }
        });

        final Button buttonVoltar = (Button)findViewById(R.id.btVoltar);
        buttonVoltar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(count>1) {
                    count--;
                    imagemAula.setImageBitmap(getBitmapFromAssets("x" + count + ".png"));
                }
            }
        });
    }

    public void clickExit(View view) {
        askToClose();
    }

    @Override
    public void onBackPressed() {
        askToClose();
    }


    private void askToClose (){
        AlertDialog.Builder builder = new AlertDialog.Builder(Aula.this);
        builder.setMessage("Tem certeza que deseja sair da aula?");
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

    // Custom method to get assets folder image as bitmap
    private Bitmap getBitmapFromAssets(String fileName){
        /*
            AssetManager
                Provides access to an application's raw asset files.
        */

        /*
            public final AssetManager getAssets ()
                Retrieve underlying AssetManager storage for these resources.
        */
        AssetManager am = getAssets();
        InputStream is = null;
        try{
            /*
                public final InputStream open (String fileName)
                    Open an asset using ACCESS_STREAMING mode. This provides access to files that
                    have been bundled with an application as assets -- that is,
                    files placed in to the "assets" directory.

                    Parameters
                        fileName : The name of the asset to open. This name can be hierarchical.
                    Throws
                        IOException
            */
            is = am.open(fileName);
        }catch(IOException e){
            e.printStackTrace();
        }

        /*
            BitmapFactory
                Creates Bitmap objects from various sources, including files, streams, and byte-arrays.
        */

        /*
            public static Bitmap decodeStream (InputStream is)
                Decode an input stream into a bitmap. If the input stream is null, or cannot
                be used to decode a bitmap, the function returns null. The stream's
                position will be where ever it was after the encoded data was read.

                Parameters
                    is : The input stream that holds the raw data to be decoded into a bitmap.
                Returns
                    The decoded bitmap, or null if the image data could not be decoded.
        */
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        return bitmap;
    }
}
