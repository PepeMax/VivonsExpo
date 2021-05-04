package com.jean.vivonsexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

public class GestionSecteur extends AppCompatActivity {
    String codeU;
    String libelleU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_secteur);

        String stringU = new String(getIntent().getStringExtra("codeU"));
        int firstSpace = stringU.indexOf(" ");
        codeU = stringU.substring(0,firstSpace);
        stringU = stringU.substring(firstSpace + 1);
//        Log.d("Test", "codeU = " + codeU);

        int secondSpace = stringU.indexOf(" ");
        libelleU = stringU.substring(secondSpace + 1);
//        Log.d("Test","stringU = " + libelleU);

        final TextView textSecteur = (TextView) findViewById(R.id.TextSecteur);
        String text = textSecteur.getText() + libelleU + " :";
        textSecteur.setText(text);

        final Button buttonAjoutSecteur = (Button) findViewById(R.id.buttonAjouterSecteur);
        buttonAjoutSecteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestionSecteur.this,AjoutSecteur.class);
                intent.putExtra("codeU",codeU);
                intent.putExtra("libelleU",libelleU);
                startActivity(intent);
            }
        });

        final Button buttonQuitterGestionSecteur = findViewById(R.id.buttonQuitterGestionSecteur);
        buttonQuitterGestionSecteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recupSecteurs(codeU,libelleU);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recupSecteurs(codeU,libelleU);
    }

    private void recupSecteurs(String codeU, String libelleU){
        OkHttpClient client = new OkHttpClient();
        ArrayList<String> arrayListNomSecteurs = new ArrayList<>();

        RequestBody formBody = new FormBody.Builder()
                .add("codeU", codeU)
                .build();

        Request request = new Request.Builder()
                .url("http://"+Param.ip+"/vivonsexpo/getLesSecteurUnivers.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseStr = response.body().string();
//                Log.d("Test",responseStr);
                JSONArray jssonArraySecteurs = null;
                try{
                    jssonArraySecteurs = new JSONArray(responseStr);

                    for(int i = 0 ; i < jssonArraySecteurs.length() ; i++){
                        JSONObject jsonSecteur = null;
                        jsonSecteur = jssonArraySecteurs.getJSONObject(i);
                        arrayListNomSecteurs.add(jsonSecteur.getString("codes") + " - " + jsonSecteur.getString("libelles"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("Test",arrayListNomSecteurs.toString());
                ListView listViewSecteurs = findViewById(R.id.listViewSecteur);

                ArrayAdapter<String> arrayAdapterSecteurs = new ArrayAdapter<>(GestionSecteur.this, android.R.layout.simple_list_item_1,arrayListNomSecteurs);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listViewSecteurs.setAdapter(arrayAdapterSecteurs);
                    }
                });

                listViewSecteurs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Object unUnivers = adapterView.getItemAtPosition(i);

                        Intent intent = new Intent(GestionSecteur.this,ModifUnSecteur.class);
                        intent.putExtra("codeU",codeU);
                        intent.putExtra("libelleU",libelleU);
                        intent.putExtra("stringS",unUnivers.toString());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("Test", "erreur!!! connexion impossible");
            }
        });
    }
}