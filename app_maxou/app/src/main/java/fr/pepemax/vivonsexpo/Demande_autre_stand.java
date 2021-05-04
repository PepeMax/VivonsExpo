package fr.pepemax.vivonsexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Demande_autre_stand extends AppCompatActivity {

    String responseStr ;
    OkHttpClient client = new OkHttpClient();
    String login = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_autre_stand);

        login = getIntent().getStringExtra("login");

        getNbDemande();

        final Button buttonEnvoyerDemande = (Button) findViewById(R.id.buttonEnvoyerDemande);
        buttonEnvoyerDemande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demandeAutreStand();
            }
        });

    }

    public void getNbDemande() {
        final Button buttonEnvoyerDemande = (Button) findViewById(R.id.buttonEnvoyerDemande);

        RequestBody formBody = new FormBody.Builder()
                .add("login", login)
                .build();

        Request request = new Request.Builder()
                .url(params.URL + "getNbDemande.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            public  void onResponse(Call call, Response response) throws IOException {
                responseStr = response.body().string();

                if (responseStr.compareTo("false")!=0){

                    try {
                        JSONObject count = new JSONObject(responseStr);

                        if(new Integer(count.getString("COUNT(login)")) >= 3 ) {
                            Log.d("Test", "Plus de 3");
                            buttonEnvoyerDemande.setClickable(false);
                            buttonEnvoyerDemande.setBackgroundColor(Color.RED);
                        }
                    }
                    catch(JSONException e){
                        Log.d("TAG", e.toString());
                    }
                }
            }

            public void onFailure(Call call, IOException e)
            {
                Log.d("TAG", e.toString());
            }

        });

    }

    public void demandeAutreStand() {
        final EditText motif = findViewById(R.id.editTextMotif);

        RequestBody formBody = new FormBody.Builder()
                .add("login", login)
                .add("motif", motif.getText().toString())
                .build();

        Request request = new Request.Builder()
                .url(params.URL + "demandeAutreStand.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            public  void onResponse(Call call, Response response) throws IOException {
                responseStr = response.body().string();

                if (new Integer(responseStr) == 1){
                     Intent intent = new Intent(Demande_autre_stand.this, Consult_Stand.class);
                     intent.putExtra("login", login);
                     startActivity(intent);
                }
            }

            public void onFailure(Call call, IOException e)
            {
                Log.d("TAG", e.toString());
            }

        });
    }

}