package com.jean.vivonsexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AjoutUniver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_univer);


        final Button buttonAjouter = (Button)findViewById(R.id.buttonValiderUniver);
        buttonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Test","Je viens de cliquer sur le bouton pour ajouter un univers");
                insert();
            }
        });

        final Button buttonQuitterAjoutUniver = findViewById(R.id.buttonQuitterAjoutUniver);
        buttonQuitterAjoutUniver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void insert(){
        final EditText editTextCode = (EditText) findViewById(R.id.editTextCodeUniver);
        final EditText editTextLibelle = (EditText) findViewById(R.id.editTextLibelleUniver);

        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("codeU", editTextCode.getText().toString())
                .add("libelleU",  editTextLibelle.getText().toString())
                .build();

        Request request = new Request.Builder()
                .url("http://"+Param.ip+"/vivonsexpo/insertUniver.php")
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
                    Log.d("Test","Impossible de faire l'ajout de l'univers !");
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("Test","erreur!!! connexion impossible");
            }
        });
    }
}