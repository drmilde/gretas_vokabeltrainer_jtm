package com.example.khalessi.gretas_vokabeltrainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.khalessi.gretas_vokabeltrainer.database.room.DatabaseHelper;
import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

public class MyWelcomeActivity extends AppCompatActivity {
    private Button btn_startApp;

    //private BasicVocabularyLoader bvl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywelcome);

        // setup the database
        AppState.getInstance().setDatabaseHelper(new DatabaseHelper(this));
        //bvl = new BasicVocabularyLoader();
        //bvl.resetDatabase();

        btn_startApp = (Button) findViewById(R.id.btn_startApp);
        btn_startApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // holt Name aus dem EditText Field
                EditText et_benutzerName = (EditText) findViewById(R.id.et_benutzerName);
                String currentUserName = et_benutzerName.getText().toString();
                AppState.getInstance().setCurrentUserName(currentUserName);


                Intent unitIntent = new Intent(getApplicationContext(), UnitListActivity.class);
                startActivity(unitIntent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);

            }
        });


        // TODO Datenbank für Vokabeln anlegen (Kopie von Unit und anpassen)
        // TODO Eintragen von Vokabeln in die Datenbank
        // TODO Entragen von Unit in die Datenbank
        // TODO Angabe des Benutzernamens
        // TODO Systemweite Speicherung der aktuellen Daten in einem Singleton
        // TODO ListView clickbar machen -> Unit auswählen und dann Vokabeln hinzufügen können

        // TODO Gestalterisch: app theme entwickeln
        // TODO setzten dynamisch Hintergrundbild per Java
        // TODO Vokabeln übersetzen
        // TODO Styles entwickeln
        // TODO Dokumentationskommentare einfügen
        // TODO Impressum Activity entwickeln
        // TODO Übungsmodi generieren
        // TODO Statistiken programmieren (weit weg)
        // TODO Bilder und Übersetzungen anfertigen
        // TODO Entwicklungsnotizen starten (in Google Docs) !!!


    }
}
