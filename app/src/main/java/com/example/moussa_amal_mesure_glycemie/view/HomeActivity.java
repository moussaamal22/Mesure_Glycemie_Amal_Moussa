package com.example.moussa_amal_mesure_glycemie.view;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moussa_amal_mesure_glycemie.R;

public class HomeActivity extends AppCompatActivity {

    private Button btnConsultation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        btnConsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); //destruction
            }
        });
    }
    private void init(){
        btnConsultation = findViewById(R.id.btnConsultation);
    }
}
