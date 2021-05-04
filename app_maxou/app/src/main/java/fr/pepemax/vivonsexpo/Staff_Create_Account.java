package fr.pepemax.vivonsexpo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearSmoothScroller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
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

public class Staff_Create_Account extends AppCompatActivity {
    String responseStr ;
    OkHttpClient client = new OkHttpClient();

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff__create__account);
        try {
            selectHall();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Button buttonEnvoyerExposant = (Button) findViewById(R.id.buttonInscriptionExposant);
        buttonEnvoyerExposant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Appel de la function authentification
                registerExposant();
            }
        });
    }



    private void selectHall() throws IOException {
        OkHttpClient client = new OkHttpClient();
        ArrayList<String> arrayListNomSalon = new ArrayList<>();

        Request request = new Request.Builder()
                .url(params.URL + "getSalon.php")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseStr = response.body().string();
                //Log.d("Test",responseStr);
                JSONArray jsonArraySalon = null;
                try {
                    jsonArraySalon = new JSONArray(responseStr);

                    for (int i = 0; i < jsonArraySalon.length(); i++) {
                        JSONObject jsonSalon = null;
                        jsonSalon = jsonArraySalon.getJSONObject(i);
                        arrayListNomSalon.add(jsonSalon.getString("codes") + " - " + jsonSalon.getString("libelles"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("Test", arrayListNomSalon.toString());
                Spinner spinnerSalon = findViewById(R.id.spinnerSalon);

                ArrayAdapter<String> arrayAdapterHalls = new ArrayAdapter<String>(Staff_Create_Account.this, android.R.layout.simple_spinner_item, arrayListNomSalon);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        spinnerSalon.setAdapter(arrayAdapterHalls);
                    }
                });
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("Test", "erreur!!! connexion impossible");
            }


        });
    }

    private void registerExposant() {
        final EditText textLogin = findViewById(R.id.inputLogin);
        final EditText textMdp = findViewById(R.id.inputMDP);
        final EditText RaisonSociale = findViewById(R.id.inputRS);
        final EditText Activite_Ent = findViewById(R.id.inputAE);
        final EditText Nom = findViewById(R.id.inputNom);
        final EditText Prenom = findViewById(R.id.inputPrenom);
        final EditText Tel = findViewById(R.id.inputTel);
        final EditText Email = findViewById(R.id.inputEmail);
        final EditText SiteWeb = findViewById(R.id.inputSiteWeb);
        final RadioGroup First_Expo = findViewById(R.id.radioGroup);
        final Spinner Secteur_Salon = findViewById(R.id.spinnerSalon);

        RequestBody formBody = new FormBody.Builder()
                .add("login", textLogin.getText().toString())
                .add("codes", Secteur_Salon.getSelectedItem().toString().substring(0, 2))
                .add("numt", "")
                .add("nums", "")
                .add("codea", "")
                .add("numh","")
                .add("numh_1","")
                .add("raison_sociale", RaisonSociale.getText().toString())
                .add("activite", Activite_Ent.getText().toString())
                .add("nom", Nom.getText().toString())
                .add("prenom",  Prenom.getText().toString())
                .add("tel", Tel.getText().toString())
                .add("mail", Email.getText().toString())
                .add("mdp", textMdp.getText().toString())
                .build();

        Request request = new Request.Builder()
                .url(params.URL + "addUser.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }

            public void onResponse(Call call, Response response) throws IOException {
                responseStr = response.body().string();
                Log.d("DEBUG", responseStr);
                if (responseStr.compareTo("OK") != 0) {
                    Intent intent = new Intent(Staff_Create_Account.this, Staff_Home.class);
                           intent.putExtra("login", textLogin.getText().toString());
                           intent.putExtra("mdp", textMdp.getText().toString());
                           startActivity(intent);
                }
            }
        });
    }}