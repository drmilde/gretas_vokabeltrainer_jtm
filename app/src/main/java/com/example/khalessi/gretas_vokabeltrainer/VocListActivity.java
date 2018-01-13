package com.example.khalessi.gretas_vokabeltrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khalessi.gretas_vokabeltrainer.database.DatabaseHelper;
import com.example.khalessi.gretas_vokabeltrainer.database.Unit;
import com.example.khalessi.gretas_vokabeltrainer.database.VocabularyItem;
import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

import java.util.ArrayList;

public class VocListActivity extends AppCompatActivity {

    VoclistCustomAdapter voclistCustomAdapter = null;
    ListView listView = null;
    DatabaseHelper db = null;
    ArrayList<VocabularyItem> voclist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voc_list);

        db = AppState.getInstance().getDatabaseHelper();
        db.recreateDatabase();
        db.insertSomeVocs();
        voclist = db.getVocabularyData("livingRoom");
        voclist = db.getVocabularyData();

        voclistCustomAdapter= new VoclistCustomAdapter(this, R.layout.voclist_details, voclist);

        listView = (ListView) findViewById(R.id.lv_vocListView);
        listView.setAdapter(voclistCustomAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "" + voclist.get(position).getForeignLang(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
