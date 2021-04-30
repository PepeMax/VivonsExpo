package com.jean.vivonsexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class AjoutSecteur extends AppCompatActivity {
    String codeU;
    String libelleU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_secteur);
        codeU = new String(getIntent().getStringExtra("codeU"));
        libelleU = new String(getIntent().getStringExtra("libelleU"));

        final TextView textSecteur = (TextView) findViewById(R.id.textAjoutSecteur);
        String text = textSecteur.getText() + libelleU + " :";
        textSecteur.setText(text);

        final Button buttonAjoutSecteur = (Button)findViewById(R.id.buttonInsertSecteur);
        buttonAjoutSecteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

        final Button buttonQuiterAjoutSecteur = findViewById(R.id.buttonQuitterAjoutSecteur);
        buttonQuiterAjoutSecteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void insert(){
        final EditText editTextCode = (EditText) findViewById(R.id.editTextCodeSecteur);
        final EditText editTextLibelle = (EditText) findViewById(R.id.editTextLibelleSecteur);

        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("codeS", editTextCode.getText().toString())
                .add("libelleS",  editTextLibelle.getText().toString())
                .add("codeU",codeU)
                .build();

        Request request = new Request.Builder()
                .url("http://"+Param.ip+"/vivonsexpo/insertSecteur.php")
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
                    Log.d("Test","Impossible de faire l'ajout du secteur !");
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("Test","erreur!!! connexion impossible");
            }
        });
    }
}