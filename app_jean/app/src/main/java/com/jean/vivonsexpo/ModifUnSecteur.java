package com.jean.vivonsexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ModifUnSecteur extends AppCompatActivity {
    private String codeS;
    private String codeU;
    private String libelleS;
    private EditText editTextCodeModifSecteur;
    private EditText editTextLibelleModifSecteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_un_secteur);
        codeS = getIntent().getStringExtra("codeU");

        String stringS = getIntent().getStringExtra("stringS");
        int firstSpace = stringS.indexOf(" ");
        codeS = stringS.substring(0,firstSpace);
        stringS = stringS.substring(firstSpace + 1);
        Log.d("Test", "codeS = " + codeS);

        int secondSpace = stringS.indexOf(" ");
        libelleS = stringS.substring(secondSpace + 1);
        Log.d("Test","stringS = " + libelleS);

        final TextView textSecteur = (TextView) findViewById(R.id.textModifSecteur);
        String text = textSecteur.getText() + libelleS + " :";
        textSecteur.setText(text);

        editTextCodeModifSecteur = findViewById(R.id.editTextCodeModifSecteur);
        editTextCodeModifSecteur.setText(codeS);

        editTextLibelleModifSecteur = findViewById(R.id.editTextLibelleModifSecteur);
        editTextLibelleModifSecteur.setText(libelleS);

        final Button buttonQuitterModifSecteur = findViewById(R.id.buttonQuitterModifSecteur);
        buttonQuitterModifSecteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final Button buttonModifSecteur = findViewById(R.id.buttonModifSecteur);
        buttonModifSecteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        final Button buttonSupprimerSecteur = findViewById(R.id.buttonSupprimerSecteur);
        buttonSupprimerSecteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

    }

    private void update(){
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("codeS", editTextCodeModifSecteur.getText().toString())
                .add("libelleS",  editTextLibelleModifSecteur.getText().toString())
                .build();

        Request request = new Request.Builder()
                .url("http://"+Param.ip+"/vivonsexpo/updateSecteur.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseStr = response.body().string();
                Log.d("Test",responseStr);
                if (responseStr.compareTo("0")!=0){
                    finish();
                } else {
//                    Toast.makeText(MainActivity.this, "message !", Toast.LENGTH_SHORT).show();
                    Log.d("Test","Impossible de modifier ce secteur !");
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("Test","erreur!!! connexion impossible");
            }
        });
    }

    private void delete(){
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("codeS", editTextCodeModifSecteur.getText().toString())
                .build();

        Request request = new Request.Builder()
                .url("http://"+Param.ip+"/vivonsexpo/deleteSecteur.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseStr = response.body().string();
                Log.d("Test",responseStr);
                if (responseStr.compareTo("0")!=0){
                    finish();
                } else {
//                    Toast.makeText(MainActivity.this, "message !", Toast.LENGTH_SHORT).show();
                    Log.d("Test","Impossible de supprimer ce secteur !");
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("Test","erreur!!! connexion impossible");
            }
        });
    }
}