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

import com.example.khalessi.gretas_vokabeltrainer.database.room.Unit;
import com.example.khalessi.gretas_vokabeltrainer.database.room.VocabularyItem;
import com.example.khalessi.gretas_vokabeltrainer.helper.BasicVocabularyLoader;
import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

import java.util.List;

public class VocListActivity extends AppCompatActivity {

    private VocListCustomAdapter voclistCustomAdapter = null;
    private ListView listView = null;
    private List<VocabularyItem> voclist = null;

    private BasicVocabularyLoader bvl = new BasicVocabularyLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_voc);


        //Unit unit = bvl.getUnit("livingRoom", true);
        Unit unit = AppState.getInstance().getCurrentUnit();
        voclist = unit.getVoclist();

        // TODO Fehlerbehandlung, wenn voclist leer ist

        //voclist = bvl.getVocabularyData("livingRoom");
        //voclist = bvl.getVocabularyData();

        voclistCustomAdapter= new VocListCustomAdapter(this, R.layout.details_list_voc, voclist);

        listView = (ListView) findViewById(R.id.lv_vocListView);
        listView.setAdapter(voclistCustomAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // store current Voc in AppState
                if (voclist.size() > 0 ) { // checken, ob nur die leermeldung angezeigt wird
                    VocabularyItem currentVoc = voclist.get(position);
                    AppState.getInstance().setCurrentVoc(currentVoc);

                    // start VocEditActivity
                    Intent intent_VocEdit = new Intent(getApplicationContext(), VocEditActivity.class);
                    startActivity(intent_VocEdit);
                } else {
                    // ist leer, also füge neuen eintrag hinzu
                    AppState.getInstance().setCurrentVoc(null); // kein aktueller vocItem eintrag

                    // start VocAddActivity
                    Intent intent_VocAdd = new Intent(getApplicationContext(), VocAddActivity.class);
                    startActivity(intent_VocAdd);
                }
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
        popup.getMenuInflater().inflate(R.menu.list_voc_popup, popup.getMenu());

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


    // Updating the list after restart

    private void updateListView() {
        // update the current unit in AppState
        Unit unit = AppState.getInstance().getCurrentUnit();
        unit = AppState.getInstance().getDatabaseHelper().getUnit(unit.getUnitId(), true);
        AppState.getInstance().setCurrentUnit(unit);

        // get the voclist and redraw listview
        voclist = unit.getVoclist();
        voclistCustomAdapter.setVoclist(voclist); // daten in unitlistCustomAdapter setzen
        listView.invalidateViews(); // listView neu zeichnen
    }

    @Override
    protected void onResume() {
        super.onResume();
        // if database has changed, we need to update the ListView
        updateListView();
    }

}
