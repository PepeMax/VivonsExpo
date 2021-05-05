package com.jean.vivonsexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuStaff extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_staff);

        final Button buttonGestionUnivers = (Button)findViewById(R.id.buttonGestionSecteur);
        buttonGestionUnivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuStaff.this, GestionUnivers.class);
                startActivity(intent);
            }
        });

        final Button buttonRepartitionSecteur = findViewById(R.id.buttonRepartitionSecteur);
        buttonRepartitionSecteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuStaff.this,RepartitionsUnivers.class);
                startActivity(intent);
            }
        });
    }
}