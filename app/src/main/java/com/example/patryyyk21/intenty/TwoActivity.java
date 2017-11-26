package com.example.patryyyk21.intenty;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        TextView tvName = (TextView)findViewById(R.id.tvName);
        TextView tvLastName = (TextView)findViewById(R.id.tvLastName);
        TextView tvCity = (TextView)findViewById(R.id.tvCity);
        Button bPrev = (Button)findViewById(R.id.bPrev);

        // Przykłady obrania danych wysłanych z poprzedniej aktywności do obecnej
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        tvName.setText(getString(R.string.name)+ " " + bundle.get("Name"));
        tvLastName.setText("Nazwisko " + intent.getExtras().get("LastName"));
        tvCity.setText("Miasto "+ getIntent().getExtras().get("City"));

        bPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Wymagane aby zwrócić wynik/stan zamykanej aktywności
                Intent backintent = new Intent();
                // Opcjonalnie można przesłać dane do poprzedniej aktywności
                backintent.putExtra("Message", "Przekazana wiadomość spowrotem");
                // Nadanie wyniku/sanu zamykanej aktywności który będzie sprawdzany w MainActivity.java
                setResult(Activity.RESULT_OK, backintent);
                // Zamykanie obecnej aktywności i powrót do poprzedniej aktywności
                finish();
            }
        });
    }
}
