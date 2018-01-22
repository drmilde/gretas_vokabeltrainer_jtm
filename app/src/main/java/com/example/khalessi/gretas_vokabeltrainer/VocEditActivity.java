package com.example.khalessi.gretas_vokabeltrainer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khalessi.gretas_vokabeltrainer.database.Unit;
import com.example.khalessi.gretas_vokabeltrainer.database.VocabularyItem;
import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

public class VocEditActivity extends AppCompatActivity {

    private VocabularyItem currentVoc = null;

    // views in form
    private EditText et_entry_add_vokabelMuttersprache;
    private EditText et_entry_add_vokabelFremdsprache;
    private EditText et_entry_add_vokabelZusatzinfo;
    private EditText et_entry_add_lektionstitel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_voc);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get the views of the form
        et_entry_add_vokabelMuttersprache = (EditText) findViewById(R.id.et_entry_add_vokabelMuttersprache);
        et_entry_add_vokabelFremdsprache = (EditText) findViewById(R.id.et_entry_add_vokabelFremdsprache);
        et_entry_add_vokabelZusatzinfo = (EditText) findViewById(R.id.et_entry_add_vokabelZusatzinfo);
        et_entry_add_lektionstitel = (EditText) findViewById(R.id.et_entry_add_lektionstitel);

        // this
        showCurrentUnitTitle();

        //TODO fill data with currentVoc
        currentVoc = AppState.getInstance().getCurrentVoc();
        if (currentVoc != null) {
            et_entry_add_vokabelMuttersprache.setText(currentVoc.getNativeLang());
            et_entry_add_vokabelFremdsprache.setText(currentVoc.getForeignLang());
            et_entry_add_vokabelZusatzinfo.setText(currentVoc.getDescription());
            et_entry_add_lektionstitel.setText(currentVoc.getUnitId());
        }

        // processing clicks
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "TODO: Add a new vocab to the table", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                // TODO reset EditText fields to standard values
            }
        });

        // clicks auf button addEntry verarbeiten
        Button btn_addEntry = (Button) findViewById(R.id.btn_addEntrySubmit);
        btn_addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // VocItem erzeugen und mit update/insert eintragen

                String foreignLang = et_entry_add_vokabelFremdsprache.getText().toString();
                String nativeLang = et_entry_add_vokabelMuttersprache.getText().toString();
                String description = et_entry_add_vokabelZusatzinfo.getText().toString();
                String unitId = et_entry_add_lektionstitel.getText().toString();

                // TODO hier muss noch ein check rein


                // sind die Daten vollständig
                if (checkForm(foreignLang, nativeLang, description, unitId)) {
                    VocabularyItem vocItem = new VocabularyItem(
                            unitId,
                            foreignLang,
                            nativeLang,
                            description
                    );

                    boolean updated = AppState.getInstance().getDatabaseHelper().updateVocabulary(vocItem);

                    if (!updated) {
                        // changed the foreign language entry
                        // insert a new entry
                        boolean inserted = AppState.getInstance().getDatabaseHelper().insertVocabulary(vocItem);
                    }
                }
            }
        });

        // clicks auf Button cancel verarbeiten
        Button btn_cancelAddEntry = (Button) findViewById(R.id.btn_addEntryCancel);
        btn_cancelAddEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Tests, ob die Formdaten vollständig und nicht leer sind
     *
     * @param foreignLang
     * @param nativeLang
     * @param description
     * @param unitId
     * @return true, falls alle Werte nichtleer sind
     */
    private boolean checkForm(String foreignLang, String nativeLang, String description, String unitId) {
        boolean dataComplete;
        dataComplete = !(
                (foreignLang.trim().equalsIgnoreCase("")) ||
                        (description.trim().equalsIgnoreCase("")) ||
                        (nativeLang.trim().equalsIgnoreCase("")) ||
                        (unitId.trim().equalsIgnoreCase(""))
        );
        return dataComplete;
    }


    private void showCurrentUnitTitle() {
        Unit currentUnit = AppState.getInstance().getCurrentUnit();
        if (currentUnit != null) {
            // get the title of the current unit and set it in the VocAddActivity
            et_entry_add_lektionstitel.setText(AppState.getInstance().getCurrentUnit().getTitle());

        }
    }
}
