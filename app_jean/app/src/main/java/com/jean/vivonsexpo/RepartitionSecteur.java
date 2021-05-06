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

public class RepartitionSecteur extends AppCompatActivity {
    private String codeU;
    private String libelleU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repartition_secteur);

        final Button buttonQuitterRepartitionSecteur = findViewById(R.id.buttonQuitterRepartitionSecteur);
        buttonQuitterRepartitionSecteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        String stringU = new String(getIntent().getStringExtra("codeU"));
        Log.d("Test",stringU);
        int firstSpace = stringU.indexOf(" ");
        codeU = stringU.substring(0,firstSpace);
        stringU = stringU.substring(firstSpace + 1);
        Log.d("Test", "codeU = " + codeU);
        int secondSpace = stringU.indexOf(" ");
        stringU = stringU.substring(secondSpace+1);
        int firstTiret = stringU.indexOf("-");
        libelleU = stringU.substring(0,firstTiret);
        Log.d("Test","stringU = " + libelleU);

        final TextView textSecteur = (TextView) findViewById(R.id.TextReparSecteur);
        String text = textSecteur.getText() + " " + libelleU + " :";
        textSecteur.setText(text);

        final Button buttonHallRepartitionSecteur = findViewById(R.id.buttonHallRepartitionSecteur);
        buttonHallRepartitionSecteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RepartitionSecteur.this,SetHallUniver.class);
                intent.putExtra("codeU", codeU);
                intent.putExtra("libelleU", libelleU);
                startActivity(intent);
            }
        });

        getSecteurs();
        testHall();
    }

    private void getSecteurs(){
        OkHttpClient client = new OkHttpClient();
        ArrayList<String> arrayListSecteurNbExposant = new ArrayList<>();

        RequestBody formBody = new FormBody.Builder()
                .add("codeU",codeU)
                .build();

        Request request = new Request.Builder()
                .url("http://"+Param.ip+"/vivonsexpo/getNbExpSecteur.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseStr = response.body().string();
                Log.d("Test",responseStr);
                JSONArray jsonArraySecteur = null;
                try {
                    jsonArraySecteur = new JSONArray(responseStr);

                    for (int i = 0; i < jsonArraySecteur.length(); i++) {
                        JSONObject jsonSecteur = null;
                        jsonSecteur = jsonArraySecteur.getJSONObject(i);
                        arrayListSecteurNbExposant.add(jsonSecteur.getString("codes") + " - " + jsonSecteur.getString("libelles") + " - " + jsonSecteur.getString("nbeExposant") + " exposants");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("Test", arrayListSecteurNbExposant.toString());
                ListView listViewRepSecteur = findViewById(R.id.listViewRepSecteur);

                ArrayAdapter<String> arrayAdapterUnivers = new ArrayAdapter<>(RepartitionSecteur.this, android.R.layout.simple_list_item_1, arrayListSecteurNbExposant);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listViewRepSecteur.setAdapter(arrayAdapterUnivers);
                    }
                });

                listViewRepSecteur.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object unUnivers = parent.getItemAtPosition(position);

                        Intent intent = new Intent(RepartitionSecteur.this,SetSecteur.class);
                        intent.putExtra("codeS",unUnivers.toString());
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

    private void testHall(){
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("codeU",codeU)
                .build();

        Request request = new Request.Builder()
                .url("http://"+Param.ip+"/vivonsexpo/testHallUnivers.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseStr = response.body().string();
                Log.d("Test",responseStr);
                try{
                    JSONObject json= new JSONObject(responseStr);
                    if(json.getString("numH").compareTo("null") == 0){
                        Intent intent = new Intent(RepartitionSecteur.this,SetHallUniver.class);
                        intent.putExtra("codeU", codeU);
                        intent.putExtra("libelleU", libelleU);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("Test","erreur!!! connexion impossible");
            }
        });
    }
}