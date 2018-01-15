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
import com.example.khalessi.gretas_vokabeltrainer.helper.BasicVocabularyLoader;
import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

import java.util.ArrayList;

public class VocListActivity extends AppCompatActivity {

    private VoclistCustomAdapter voclistCustomAdapter = null;
    private ListView listView = null;
    private ArrayList<VocabularyItem> voclist = null;

    private BasicVocabularyLoader bvl = new BasicVocabularyLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voc_list);

        Unit unit = bvl.getUnit("livingRoom", true);
        voclist = unit.getVoclist();

        // TODO Fehlerbehandlung, wenn voclist leer ist

        //voclist = bvl.getVocabularyData("livingRoom");
        //voclist = bvl.getVocabularyData();

        voclistCustomAdapter= new VoclistCustomAdapter(this, R.layout.voclist_details, voclist);

        listView = (ListView) findViewById(R.id.lv_vocListView);
        listView.setAdapter(voclistCustomAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String foreignLang = voclist.get(position).getForeignLang();
                int fid = bvl.getVocabularyId(foreignLang);

                Toast.makeText(getApplicationContext(),
                        "" + fid+"", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
