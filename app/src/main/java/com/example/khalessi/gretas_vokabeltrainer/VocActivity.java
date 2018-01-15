package com.example.khalessi.gretas_vokabeltrainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.khalessi.gretas_vokabeltrainer.database.Unit;
import com.example.khalessi.gretas_vokabeltrainer.database.VocabularyItem;
import com.example.khalessi.gretas_vokabeltrainer.helper.BasicVocabularyLoader;
import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

import java.util.ArrayList;

public class VocActivity extends AppCompatActivity {

    private VocCustomAdapter voclistCustomAdapter = null;
    private ListView listView = null;
    private ArrayList<VocabularyItem> voclist = null;

    private BasicVocabularyLoader bvl = new BasicVocabularyLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voc_list);


        //Unit unit = bvl.getUnit("livingRoom", true);
        Unit unit = AppState.getInstance().getCurrentUnit();
        voclist = unit.getVoclist();

        // TODO Fehlerbehandlung, wenn voclist leer ist

        //voclist = bvl.getVocabularyData("livingRoom");
        //voclist = bvl.getVocabularyData();

        voclistCustomAdapter= new VocCustomAdapter(this, R.layout.voclist_details, voclist);

        listView = (ListView) findViewById(R.id.lv_vocListView);
        listView.setAdapter(voclistCustomAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // store current Voc in AppState
                VocabularyItem currentVoc = voclist.get(position);
                AppState.getInstance().setCurrentVoc(currentVoc);

                // start VocAddActivity
                Intent intent_VocAdd = new Intent(getApplicationContext(), VocAddActivity.class);
                startActivity(intent_VocAdd);
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                PopupMenu popup = createPopupMenu(view);
                popup.show();//showing popup menu

                //createDeleteDialog(position).show();
                return true;
            }
        });


    }

    @NonNull
    private PopupMenu createPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(getApplicationContext(), view);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.voc_list_popup, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getApplicationContext(),
                        "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return popup;
    }
}
