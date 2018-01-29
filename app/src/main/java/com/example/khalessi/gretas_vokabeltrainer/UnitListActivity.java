package com.example.khalessi.gretas_vokabeltrainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.example.khalessi.gretas_vokabeltrainer.database.room.Unit;
import com.example.khalessi.gretas_vokabeltrainer.helper.BasicVocabularyLoader;
import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

import java.util.List;

public class UnitListActivity extends AppCompatActivity {

    private UnitListCustomAdapter unitlistCustomAdapter = null;
    private ListView listView = null;
    private List<Unit> units = null;

    private BasicVocabularyLoader bvl = new BasicVocabularyLoader();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_unit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get the data from database and create UnitListCustomAdapter
        units = AppState.getInstance().getDatabaseHelper().getUnitsData(true);


        unitlistCustomAdapter = new UnitListCustomAdapter(this, R.layout.details_list_unit, units);

        // connect listView and Adapter
        listView = (ListView) findViewById(R.id.lv_unitListView);
        listView.setAdapter(unitlistCustomAdapter);


        // react to click
        // setting the current unit to the selected one
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // save current unit and start UnitAddActivity
                AppState.getInstance().setCurrentUnit(units.get(position));
                startUnitEditActivity();
            }
        });

        // react to long click for deletion
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                // speichert ausgewählte unit im AppState
                Unit currentUnit = units.get(position);
                AppState.getInstance().setCurrentUnit(currentUnit);

                // create and show popup menu
                PopupMenu popup = createPopupMenu(view);
                popup.show();//showing popup menu

                return true;
            }
        });


        // configure customized BottomNavigationBar
        configureBottomBar(R.id.navigation);



    }


    // CONFIGURE BOTTOMBAR
    private void configureBottomBar(int navigation1) {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(navigation1);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationViewHelper.disableShiftMode(navigation);
    }

    // BOTTOMBAR Callback handler
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    // TODO provide action for first button in navigation bar
                    return true;
                case R.id.navigation_dashboard1:
                    // TODO provide action for second button in navigation bar
                    return true;
                case R.id.navigation_dashboard2:
                    // TODO provide action for third button in navigation bar
                    return true;
                case R.id.navigation_notifications:
                    // TODO provide action for forth button in navigation bar
                    // TODO substitute FAB here, just as a test
                    AppState.getInstance().setCurrentUnit(null);
                    startUnitAddActivity();
                    return true;
            }
            return false;
        }
    };


    /**
     * Erzeugt und initialisiert das Popupmenu für den übegebenen View
     * (in diesem Fall der ListView zur Anzeige der Units)
     *
     * @param view
     * @return
     */
    @NonNull
    private PopupMenu createPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(getApplicationContext(), view);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.list_unit_popup, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

                // unterscheidung zwischen den menu punkte: Auswahl
                String selection = item.getTitle().toString();

                if (selection.equalsIgnoreCase("üben")) {
                    // currentUnit speichern, ist oben passiert
                    // startActivity Üben
                    Intent levelOneIntent = new Intent(getApplicationContext(), LevelOneActivity.class);
                    startActivity(levelOneIntent);
                }

                return true;
            }
        });
        return popup;
    }


    /**
     * Deletes the unit at position in the ArrayList from the database
     * and updates the ListView.
     *
     * @param position of unit in ArrayListe
     */
    private void deleteListItem(int position) {
        int id = units.get(position).get_id();
        AppState.getInstance().getDatabaseHelper().deleteUnit(id);
        updateListView();
    }

    /**
     * Starts the UnitAddActivity.
     */
    private void startUnitEditActivity() {
        Intent intentEditUnit = new Intent(getApplicationContext(), UnitEditActivity.class);
        startActivity(intentEditUnit);
    }

    /**
     * starts the UniAddActivity
     */
    private void startUnitAddActivity() {
        Intent intentAddUnit = new Intent(getApplicationContext(), UnitAddActivity.class);
        startActivity(intentAddUnit);
    }


    /**
     * Updates the content of the ListView.
     */
    private void updateListView() {
        units = AppState.getInstance().getDatabaseHelper().getUnitsData(true); // aktuelle Daten holen

        unitlistCustomAdapter.setUnits(units); // daten in unitlistCustomAdapter setzen
        listView.invalidateViews(); // listView neu zeichnen
    }

    @Override
    protected void onResume() {
        super.onResume();
        // if database has changed, we need to update the ListView
        updateListView();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);

    }
}
