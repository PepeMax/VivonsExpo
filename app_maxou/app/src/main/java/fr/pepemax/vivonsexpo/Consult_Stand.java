package fr.pepemax.vivonsexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

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

public class Consult_Stand extends AppCompatActivity {

    String responseStr ;
    OkHttpClient client = new OkHttpClient();
    String login = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult__stand);

        login = getIntent().getStringExtra("user").substring(0, getIntent().getStringExtra("user").indexOf("&"));
        String mdp = getIntent().getStringExtra("user").substring(getIntent().getStringExtra("user").indexOf("&")+1);

        Log.d("DEBUG", login + " "+ mdp);

        try {
            getInfosStand();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getInfosStand() throws IOException {

        final TextView num_Hall  = findViewById(R.id.textNumH);
        final TextView code_Alle = findViewById(R.id.textCodeA);
        final TextView num_Travee = findViewById(R.id.textNumT);
        final TextView num_Secteur = findViewById(R.id.textNumS);


        RequestBody formBody = new FormBody.Builder()
                .add("login", login)
                .build();

        Request request = new Request.Builder()
                .url(params.URL + "getStand.php")
                .post(formBody)
                .build();
        
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            public  void onResponse(Call call, Response response) throws IOException {
                responseStr = response.body().string();

                if (responseStr.compareTo("false")!=0){

                    Log.d("TEST", responseStr);

                    try {
//                        JSONObject stand = new JSONObject(responseStr);
                        JSONObject stand =  new JSONObject("{\"numh\":\"1\",\"codea\":\"2\",\"numt\":\"5\",\"nums\":\"A\"}");

                        num_Hall.setText(stand.getString("numh"));
                        code_Alle.setText(stand.getString("codea"));
                        num_Travee.setText(stand.getString("numt"));
                        num_Secteur.setText(stand.getString("nums"));

                    }
                    catch(JSONException e){
                        Log.d("TAG", e.toString());
                     }
                } else {
                    Log.d("Test","Login ou mot de  passe non valide !");
                }
            }

            public void onFailure(Call call, IOException e)
            {
                Log.d("Test","erreur!!! connexion impossible");
            }

        });
    }

}