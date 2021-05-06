package com.jean.vivonsexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
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

public class SetSecteur extends AppCompatActivity {
    private String codeS;
    private String libelleS;
    private ArrayList<CheckBox> arrayListCheckBoxTravee = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_secteur);

        final Button buttonQuitterParamSecteur = findViewById(R.id.buttonQuitterParamSecteur);
        buttonQuitterParamSecteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String stringS = new String(getIntent().getStringExtra("codeS"));
        Log.d("Test",stringS);
        int firstSpace = stringS.indexOf(" ");
        codeS = stringS.substring(0,firstSpace);
        stringS = stringS.substring(firstSpace + 1);
        Log.d("Test", "codeU = " + codeS);
        int secondSpace = stringS.indexOf(" ");
        stringS = stringS.substring(secondSpace+1);
        int firstTiret = stringS.indexOf("-");
        libelleS = stringS.substring(0,firstTiret);
        Log.d("Test","stringU = " + libelleS);

        final TextView textSecteur = (TextView) findViewById(R.id.TextParamSecteur);
        String text = textSecteur.getText() + " " + libelleS + " :";
        textSecteur.setText(text);

        getTraveeDispo();
    }

    private void getTraveeDispo(){
        OkHttpClient client = new OkHttpClient();
        ArrayList<String> arrayListTravee = new ArrayList<>();

        RequestBody formBody = new FormBody.Builder()
                .add("codeS",codeS)
                .build();

        Request request = new Request.Builder()
                .url("http://"+Param.ip+"/vivonsexpo/getTraveeSecteur.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseStr = response.body().string();
                Log.d("Test",responseStr);
                JSONArray jsonArrayTravee = null;
                try {
                    jsonArrayTravee = new JSONArray(responseStr);

                    for (int i = 0; i < jsonArrayTravee.length(); i++) {
                        JSONObject jsonTravee = null;
                        jsonTravee = jsonArrayTravee.getJSONObject(i);
                        arrayListTravee.add("Travee "+jsonTravee.getString("numt"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("Test", arrayListTravee.toString());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final LinearLayout ll = findViewById(R.id.linearLayoutCheckBoxes);
                        for(String uneTravee : arrayListTravee){
                            CheckBox ch = new CheckBox(getApplicationContext());
                            ch.setText(uneTravee);
                            ll.addView(ch);
                            arrayListCheckBoxTravee.add(ch);
                        }
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