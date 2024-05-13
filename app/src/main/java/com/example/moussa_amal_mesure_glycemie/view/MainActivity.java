package com.example.moussa_amal_mesure_glycemie.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moussa_amal_mesure_glycemie.R;
import com.example.moussa_amal_mesure_glycemie.controller.Controller;

public class MainActivity extends AppCompatActivity {
    //declaration des variables
    private TextView tvAge; //tvReponse -> Consult
    private SeekBar sbAge;
    private RadioButton rbtOui,rbtNon;
    private Button btnConsulter;
    private EditText etValue;

    private Controller controller;

    private final String RESPONSE_KEY = "reponse"; //constante pour sauvegarder la clé 
    private final int REQUEST_CODE=1; //code de l'activité ConsultActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Cette méthode est appelée lorsque l'activité est créée pour la première fois.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Définit le contenu de l'activité à partir du fichier de mise en page XML activity_main.xml.
        init(); //appelle la méthode init().

        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvAge.setText("Votre age: "+ progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        }) ;

        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age;
                float valeur;
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
                    age = sbAge.getProgress();
                    valeur=Float.valueOf(etValue.getText().toString());
                    //uer view cont
                    controller.createPatient(age, valeur, rbtOui.isChecked());
                    //Flèche "Notify" Conttoller-->View
                    //tvReponse.setText(controller.getResult());

                    Intent intent = new Intent(MainActivity.this,ConsultActivity.class);
                    intent.putExtra(RESPONSE_KEY,controller.getResult());
                    startActivityForResult(intent,REQUEST_CODE);
                }
        }
    });
}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //suivi de retour
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE)
            if(resultCode==RESULT_CANCELED)
                Toast.makeText(getApplicationContext(),"Erreur ConsultActivity : RESULT_CANCELED", Toast.LENGTH_LONG).show();
    }
    private void init() //Cette méthode initialise les objets de l'interface utilisateur en récupérant
                        // les références des composants définis dans le fichier de mise en page XML.
    {
        controller = Controller.getInstance();

        tvAge = findViewById(R.id.tvAge);

        sbAge = findViewById(R.id.sbAge);
        rbtOui = findViewById(R.id.rbtOui);
        rbtNon = findViewById(R.id.rbtNon);
        btnConsulter = findViewById(R.id.btnConsulter);
        etValue = findViewById(R.id.etValue);
    }
}

