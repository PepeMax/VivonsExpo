package fr.pepemax.vivonsexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class Accueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        final Button buttonStaff = (Button) findViewById(R.id.buttonStaff);
        buttonStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("DEBUG : ", "Staff: ");
//                try {
//                    Intent intent = new Intent(MainActivity.this, MenuEtudiantActivity.class);
//                    startActivity(intent);
//                }catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
        final Button buttonExposant = (Button) findViewById(R.id.buttonExposant);
        buttonExposant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("DEBUG : ", "Exposant: ");
                Intent intent = new Intent(Accueil.this, Staff_Home.class);
                startActivity(intent);
            }
        });
        final Button buttonVisiteur = (Button) findViewById(R.id.buttonVisiteur);
        buttonVisiteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("DEBUG : ", "Visiteur: ");
//                try {
//                    Intent intent = new Intent(MainActivity.this, MenuEtudiantActivity.class);
//                    startActivity(intent);
//                }catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }
}