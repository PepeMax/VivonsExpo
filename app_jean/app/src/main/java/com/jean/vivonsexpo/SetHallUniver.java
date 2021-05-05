package com.jean.vivonsexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

public class SetHallUniver extends AppCompatActivity {
    private String codeU;
    private String libelleU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_hall_univer);

        codeU = getIntent().getStringExtra("codeU");
        libelleU = getIntent().getStringExtra("libelleU");

        final Button buttonQuitterHallUniver = findViewById(R.id.buttonQuitterHallUniver);
        buttonQuitterHallUniver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final Button buttonValiderUniverHall = findViewById(R.id.buttonValiderUniverHall);
        buttonValiderUniverHall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateHall();
            }
        });

        final TextView TextHallUnivers = findViewById(R.id.TextHallUnivers);
        String tmp = TextHallUnivers.getText() + " " + libelleU + " :";
        TextHallUnivers.setText(tmp);

        selectHall();
    }

    private void selectHall(){
        OkHttpClient client = new OkHttpClient();
        ArrayList<String> arrayListNumHall = new ArrayList<>();

        Request request = new Request.Builder()
                .url("http://"+Param.ip+"/vivonsexpo/getLesHalls.php")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseStr = response.body().string();
                Log.d("Test",responseStr);
                JSONArray jsonArrayHall = null;
                try {
                    jsonArrayHall = new JSONArray(responseStr);
                    for (int i = 0; i < jsonArrayHall.length(); i++) {
                        JSONObject jsonHall = null;
                        jsonHall = jsonArrayHall.getJSONObject(i);
                        arrayListNumHall.add(jsonHall.getString("numh"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("Test", arrayListNumHall.toString());
                Spinner spinnerUnivers = findViewById(R.id.spinnerHall);

                ArrayAdapter<String> arrayAdapterHalls = new ArrayAdapter<String>(SetHallUniver.this, android.R.layout.simple_spinner_item, arrayListNumHall);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        spinnerUnivers.setAdapter(arrayAdapterHalls);
                    }
                });
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("Test", "erreur!!! connexion impossible");
            }
        });
    }

    private void updateHall(){
        OkHttpClient client = new OkHttpClient();
        ArrayList<String> arrayListNumHall = new ArrayList<>();
        final Spinner spinnerHall = findViewById(R.id.spinnerHall);

        RequestBody formBody = new FormBody.Builder()
                .add("codeU",codeU)
                .add("numH",spinnerHall.getSelectedItem().toString())
                .build();

        Request request = new Request.Builder()
                .url("http://"+Param.ip+"/vivonsexpo/updateHallUnivers.php")
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
                Log.d("Test", "erreur!!! connexion impossible");
            }
        });
    }
}