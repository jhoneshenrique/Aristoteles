package com.example.android.aristoteles;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Menu extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private ImageView photoImageView;
    private TextView emailTextView;
    private boolean quizEnabled=true;
    private String emailUser;

    private GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        photoImageView = (ImageView) findViewById(R.id.photoImageView);
        emailTextView = (TextView) findViewById(R.id.emailTextView);

        final Button btAula = (Button) findViewById(R.id.mBtAula);
        btAula.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                Intent secondActivity = new Intent(Menu.this, Aula.class);
                Bundle bundle = new Bundle();
                bundle.putString("email", user.getEmail());
                secondActivity.putExtras(bundle);
                startActivity(secondActivity);
            }
        });


        final Button btProfile = (Button) findViewById(R.id.mProfile);
        btProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                Intent secondActivity = new Intent(Menu.this, Profile.class);
                Bundle bundle = new Bundle();
                bundle.putString("nome", user.getDisplayName());
                bundle.putString("email", user.getEmail());
                secondActivity.putExtras(bundle);
                startActivity(secondActivity);
            }
        });


        final Button btQuiz = (Button) findViewById(R.id.mBtQuiz);
        btQuiz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                emailUser = user.getEmail();
                BancoController crud = new BancoController(getBaseContext());
                Cursor cursor = crud.carregaDadoByEmail("'"+emailUser+"'");

                String codigo;
                cursor.moveToPosition(0);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID));
                cursor = crud.carregaDadoById(Integer.parseInt(codigo));
                int flag = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.STATUSAULA)));

                if(flag==0){
                    Toast.makeText(getApplicationContext(),"Você precisa fazer a aula primeiro!", Toast.LENGTH_SHORT).show();
                }else{
                    Intent secondActivity = new Intent(Menu.this, Quiz.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("email", user.getEmail());
                    secondActivity.putExtras(bundle);
                    startActivity(secondActivity);
                }
            }
        });


        final Button btFechar = (Button) findViewById(R.id.mBtFechar);
        btFechar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                askToClose();
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    setUserData(user);
                } else {
                    goLogInScreen();
                }
            }
        };


    }

    public void setQuizEnabled(boolean quiz){
        quizEnabled = quiz;
    }

    private void setUserData(FirebaseUser user) {
        emailTextView.setText(user.getEmail());
        Glide.with(this).load(user.getPhotoUrl()).into(photoImageView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    private void goLogInScreen() {
        Intent intent = new Intent(this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logOut(View view) {
        firebaseAuth.signOut();

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    goLogInScreen();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.not_close_session, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (firebaseAuthListener != null) {
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }


    private void askToClose(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
        builder.setMessage("Tem certeza que deseja fechar o Aplicativo?");
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
