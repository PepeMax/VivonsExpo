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

import org.jetbrains.annotations.NotNull;
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

public class RepartitionsUnivers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repartitions_univers);

        final Button buttonQuitterRepartition = findViewById(R.id.buttonQuitterRepartition);
        buttonQuitterRepartition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        countUnivers();
    }

    private void countUnivers(){
        OkHttpClient client = new OkHttpClient();
        ArrayList<String> arrayListUniNbExposant = new ArrayList<>();

        Request request = new Request.Builder()
                .url("http://"+Param.ip+"/vivonsexpo/getNbExpUniver.php")
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseStr = response.body().string();
                Log.d("Test",responseStr);
                JSONArray jsonArrayUnivers = null;
                try {
                    jsonArrayUnivers = new JSONArray(responseStr);

                    for (int i = 0; i < jsonArrayUnivers.length(); i++) {
                        JSONObject jsonUniver = null;
                        jsonUniver = jsonArrayUnivers.getJSONObject(i);
                        arrayListUniNbExposant.add(jsonUniver.getString("codeu") + " - " + jsonUniver.getString("libelleu") + " - " + jsonUniver.getString("nbeExposant") + " exposants");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Log.d("Test", arrayListUniNbExposant.toString());
                ListView listViewUnivers = findViewById(R.id.listViewRepUnivers);

                ArrayAdapter<String> arrayAdapterUnivers = new ArrayAdapter<>(RepartitionsUnivers.this, android.R.layout.simple_list_item_1, arrayListUniNbExposant);

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

                        Intent intent = new Intent(RepartitionsUnivers.this,RepartitionSecteur.class);
                        intent.putExtra("codeU",unUnivers.toString());
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