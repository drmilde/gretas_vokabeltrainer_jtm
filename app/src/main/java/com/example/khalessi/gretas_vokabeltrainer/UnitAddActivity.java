package com.example.khalessi.gretas_vokabeltrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

public class UnitAddActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_unit);


        Button btn_speichern = (Button) findViewById(R.id.btn_addEntrySubmit);
        btn_speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO neuen Eintrag in der Unit-Datenbank erzeugen
                //TODO Intent muss LektionsID erhalten
                Intent intent_addEntry = new Intent(getApplicationContext(), EntryAddActivity.class);
                startActivity(intent_addEntry);
            }
        });

        Button btn_cancel = (Button) findViewById(R.id.btn_addEntryCancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        EditText et_lektionsUsername = (EditText) findViewById(R.id.et_lektionsUsername);
        et_lektionsUsername.setText(AppState.getInstance().getCurrentUserName());

    }



}
