package fr.pepemax.vivonsexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class Staff_Home extends AppCompatActivity {
    String responseStr ;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff__home);

        final EditText textLogin = findViewById(R.id.editTextLogin);
        final EditText textMdp = findViewById(R.id.editTextMdp);

        String login = getIntent().getStringExtra("login");
        String mdp = getIntent().getStringExtra("mdp");

        if (login == null && mdp == null) {
            login = "";
            mdp = "";
        }

        textLogin.setText(login);
        textMdp.setText(mdp);

        final Button buttonValiderAuthentification = (Button) findViewById(R.id.buttonConnexion);
        buttonValiderAuthentification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Appel de la function authentification
                try {
                    authentification();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        final Button buttonCreateAccount = (Button)findViewById(R.id.buttonCreateAccount);
        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Staff_Home.this, Staff_Create_Account.class);
                startActivity(intent);
            }
        });
    }


    public void authentification() throws IOException {
        final EditText textLogin = findViewById(R.id.editTextLogin);
        final EditText textMdp = findViewById(R.id.editTextMdp);

        RequestBody formBody = new FormBody.Builder()
                .add("login", textLogin.getText().toString())
                .add("mdp",  textMdp.getText().toString())
                .build();

        Request request = new Request.Builder()
                .url(params.URL + "authentification.php")
                .post(formBody)
                .build();

        Log.d("Test", textLogin.getText().toString() + " " + textMdp.getText().toString() );

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            public  void onResponse(Call call, Response response) throws IOException {
                responseStr = response.body().string();
                Log.d("test", responseStr);


                if (responseStr.compareTo("false")!=0){

                    Log.d("test", responseStr);


                    try {
                        JSONObject user = new JSONObject(responseStr);

                        if(user.getString("statut").compareTo("staff")==0) {
                            Log.d("Test", "Staff");
//                            Intent intent = new Intent(MainActivity.this, MenuEtudiantActivity.class);
//                            intent.putExtra("etudiant", etudiant.toString());
//                            startActivity(intent);
                        }
                        else if (user.getString("statut").compareTo("exposant")==0){
                            Log.d("Test", "Exposant");
                            Intent intent = new Intent(Staff_Home.this, Consult_Stand.class);
                            intent.putExtra("user", textLogin.getText().toString() + "&" + textMdp.getText().toString());
                            startActivity(intent);
                        }
                    }
                    catch(JSONException e){
                        Log.d("TAG", e.toString());
                        // Toast.makeText(MainActivity.this, "Erreur de connexion !!!! !", Toast.LENGTH_SHORT).show();
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
