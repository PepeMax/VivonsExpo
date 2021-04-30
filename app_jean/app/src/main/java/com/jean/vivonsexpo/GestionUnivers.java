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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GestionUnivers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_univers);

        final Button buttonAjouter = (Button) findViewById(R.id.buttonAjouterUniver);
        buttonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestionUnivers.this,AjoutUniver.class);
                startActivity(intent);
            }
        });

        final Button buttonQuitterUniver = findViewById(R.id.buttonQuitterUniver);
        buttonQuitterUniver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        try {
            listeUnivers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            listeUnivers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listeUnivers() throws IOException {

        OkHttpClient client = new OkHttpClient();
        ArrayList<String> arrayListNomUnivers = new ArrayList<>();

        Request request = new Request.Builder()
                .url("http://"+Param.ip+"/vivonsexpo/getLesUnivers.php")
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body().string();
//                Log.d("Test",responseStr);
                JSONArray jsonArrayUnivers = null;
                try {
                    jsonArrayUnivers = new JSONArray(responseStr);

                    for (int i = 0; i < jsonArrayUnivers.length(); i++) {
                        JSONObject jsonUniver = null;
                        jsonUniver = jsonArrayUnivers.getJSONObject(i);
                        arrayListNomUnivers.add(jsonUniver.getString("codeu") + " - " + jsonUniver.getString("libelleu"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("Test", arrayListNomUnivers.toString());
                ListView listViewUnivers = findViewById(R.id.listViewUnivers);

                ArrayAdapter<String> arrayAdapterUnivers = new ArrayAdapter<String>(GestionUnivers.this, android.R.layout.simple_list_item_1, arrayListNomUnivers);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listViewUnivers.setAdapter(arrayAdapterUnivers);
                    }
                });
                listViewUnivers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Object unUnivers = adapterView.getItemAtPosition(i);

                        Intent intent = new Intent(GestionUnivers.this,GestionSecteur.class);
                        intent.putExtra("codeU",unUnivers.toString());
                        startActivity(intent);
                    }
                });

            }

            public void onFailure(Call call, IOException e) {
                Log.d("Test", "erreur!!! connexion impossible");
            }

        });
    }
}