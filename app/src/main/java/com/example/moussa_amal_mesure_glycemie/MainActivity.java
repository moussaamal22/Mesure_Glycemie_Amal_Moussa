package com.example.moussa_amal_mesure_glycemie;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //declaration des variables
    private TextView tvAge,tvReponse;
    private SeekBar sbAge;
    private RadioButton rbtOui,rbtNon;
    private Button btnConsulter;
    private EditText etValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Cette méthode est appelée lorsque l'activité est créée pour la première fois.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Définit le contenu de l'activité à partir du fichier de mise en page XML activity_main.xml.
        init(); //appelle la méthode init().

        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Information", "onProgressChanged "+progress);
                // affichage dans le Log de Android studio pour voir les changements détectés sur le SeekBar (message sur le console)
                // tvAge.setText("Votre âge : "+ progress);
                // Mise à jour du TextView par la valeur : progress à chaque changement
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        }) ;

        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Information", "button cliqué");
                boolean verifAge = false, verifValeur = false;
                if(sbAge.getProgress()!=0)
                    verifAge = true;
                else
                    Toast.makeText(MainActivity.this, "Veuillez saisir votre age !", Toast.LENGTH_SHORT).show();

                if(!etValue.getText().toString().isEmpty())
                    verifValeur = true;
                else
                    Toast.makeText(MainActivity.this, "Veuillez saisir votre valeur mesurée !", Toast.LENGTH_LONG).show();
                if(verifAge && verifValeur)
                {
                    calculer();
                }
        }
    });
}
    public void calculer()
    {
        int age = Integer.valueOf(sbAge.getProgress());
        float valeurMesuree = Float.valueOf(etValue.getText().toString());
        boolean isFasting = rbtOui.isChecked();
        if(isFasting) {
            if (age >= 13) {
                if (valeurMesuree < 5.0)
                    tvReponse.setText("Niveau de glycémie est trop bas");
                else if (valeurMesuree >= 5.0 && valeurMesuree <= 7.2)
                    tvReponse.setText("Niveau de glycémie est normale");
                else
                    tvReponse.setText("Niveau de glycémie est trop élevé");
            } else if (age >= 6 && age <= 12) {
                if (valeurMesuree < 5.0)
                    tvReponse.setText("Niveau de glycémie est trop bas");
                else if (valeurMesuree >= 5.0 && valeurMesuree <= 10.0)
                    tvReponse.setText("Niveau de glycémie est normale");
                else
                    tvReponse.setText("Niveau de glycémie est trop élevé");
            } else if (age < 6) {
                if (valeurMesuree < 5.5)
                    tvReponse.setText("Niveau de glycémie est trop bas");
                else if (valeurMesuree >= 5.5 && valeurMesuree <= 10.0)
                    tvReponse.setText("Niveau de glycémie est normale");

                else
                    tvReponse.setText("Niveau de glycémie est trop élevé");
            }
        } else {
            if (valeurMesuree > 10.5)
                tvReponse.setText("Niveau de glycémie est trop élevé");
            else
                tvReponse.setText("Niveau de glycémie est normale");
        }
    }
    private void init() //Cette méthode initialise les objets de l'interface utilisateur en récupérant
                        // les références des composants définis dans le fichier de mise en page XML.
    {
        tvAge = findViewById(R.id.tvAge);
        tvReponse = findViewById(R.id.tvReponse);
        sbAge = findViewById(R.id.sbAge);
        rbtOui = findViewById(R.id.rbtOui);
        rbtNon = findViewById(R.id.rbtNon);
        btnConsulter = findViewById(R.id.btnConsulter);
        etValue = findViewById(R.id.etValue);
    }

}

