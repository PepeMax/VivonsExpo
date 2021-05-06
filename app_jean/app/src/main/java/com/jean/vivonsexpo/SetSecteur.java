package com.jean.vivonsexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SetSecteur extends AppCompatActivity {
    private String codeS;
    private String libelleS;
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
    }
}