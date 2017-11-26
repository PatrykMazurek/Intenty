package com.example.patryyyk21.intenty;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etName = (EditText)findViewById(R.id.etName);
        final EditText etLastName = (EditText)findViewById(R.id.etLastName);
        final EditText etCity = (EditText)findViewById(R.id.etCity);
        Button bSave = (Button)findViewById(R.id.bSave);
        Button bClear = (Button)findViewById(R.id.bClearData);
        final CheckBox chSaveData = (CheckBox)findViewById(R.id.checkBox);

        // Inicjalizacja SharedPreferences
        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sPref.edit();

        // Wstawienie do kontrolek EditText danych z SharedPreferences, jeżeli danych niema wstawiane jest 'NULL'
        etName.setText(sPref.getString("Name", null));
        etLastName.setText(sPref.getString("LastName", null));
        etCity.setText((sPref.getString("City", null)));

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(chSaveData.isChecked()){
                    // Zapisywanie do SharedPreferences
                    editor.putString("Name", etName.getText().toString());
                    editor.putString("LastName", etLastName.getText().toString());
                    editor.putString("City", etCity.getText().toString());
                    editor.apply();
                }
                // Tworzenie odwołania do nowej aktywności
                // Wyjaśnienie konstruktora Intent(obezna aktywność , do której aktywności chcemy przejść)
                Intent intent = new Intent(MainActivity.this, TwoActivity.class);
                // Zapisanie danych do wysłania do nowej aktywności
                // Wyjaśnienie intent.putExtra(Klucz, Wartość) w taki sposób wysyłaned są dane
                intent.putExtra("Name", etName.getText().toString());
                intent.putExtra("LastName", etLastName.getText().toString());
                intent.putExtra("City", etCity.getText().toString());
                // Otwieranie nowej aktywności bez sprawdzania wyniku/stanu aktywności
                //startActivity(intent);
                // Otwieranie nowej Aktywnosci ze sprwadzaniem wyniku/stanu aktywności
                startActivityForResult(intent, 1);
            }
        });

        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Czyszczenie zapisanych danych w SharedPreferences i w kontrolkach EditText
                editor.clear();
                editor.apply();
                etCity.setText(null);
                etLastName.setText(null);
                etName.setText(null);
            }
        });
    }

    // Metoda która sprawdza wynik/stan zamkniętej aktywności i pobiera dane jeżeli zostaly wysłane z zamkniętej aktywności
    @Override
    protected void onActivityResult(int requestCode, int resultsCode, Intent data){
        if(requestCode ==1)
        {
            if(resultsCode == Activity.RESULT_OK){
                Toast.makeText(getApplicationContext(), "Powruconi przez przycisk ' Powrót ' '", Toast.LENGTH_SHORT).show();
                // Wyświetlenie danych które zostały wysłane z zamkniętej aktywności
                Toast.makeText(getApplicationContext(), "Wiadomość z poprzedniej aktywności: " + data.getExtras().get("Message"),Toast.LENGTH_LONG).show();
            }
            if(resultsCode == Activity.RESULT_CANCELED){
                Toast.makeText(getApplicationContext(), "Anulowano ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
