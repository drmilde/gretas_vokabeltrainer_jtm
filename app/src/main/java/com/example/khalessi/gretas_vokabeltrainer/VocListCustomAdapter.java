package com.example.khalessi.gretas_vokabeltrainer;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.khalessi.gretas_vokabeltrainer.database.VocabularyItem;

import java.util.ArrayList;

/**
 * Created by milde on 08.01.18.
 */

public class VocListCustomAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<VocabularyItem> voclist;

    public VocListCustomAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context, textViewResourceId, objects);

        this.context = context;
        voclist = objects;
    }

    private class ViewHolder {
        TextView tv_foreign;
        TextView tv_native;
        TextView tv_description;
        TextView tv_unitId;
        TextView tv_count1;
        TextView tv_count2;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VocListCustomAdapter.ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.details_list_voc, null);

            holder = new VocListCustomAdapter.ViewHolder();
            holder.tv_unitId = (TextView) convertView.findViewById(R.id.tv_voc_unitId);
            holder.tv_foreign = (TextView) convertView.findViewById(R.id.tv_voc_foreignLang);
            holder.tv_description = (TextView) convertView.findViewById(R.id.tv_voc_Description);
            holder.tv_native = (TextView) convertView.findViewById(R.id.tv_voc_nativeLang);
            holder.tv_count1 = (TextView) convertView.findViewById(R.id.tv_voc_count1);
            holder.tv_count2 = (TextView) convertView.findViewById(R.id.tv_voc_count2);
            convertView.setTag(holder);

        } else {
            holder = (VocListCustomAdapter.ViewHolder) convertView.getTag();
        }

        // hier erfolgt die Anzeige
        VocabularyItem vocitem;
        if (voclist.size() > 0) {
            vocitem = voclist.get(position);

            holder.tv_description.setText(vocitem.getDescription() + "");
            holder.tv_foreign.setText(vocitem.getForeignLang() + "");
            holder.tv_unitId.setText(vocitem.getUnitId() + "");
            holder.tv_native.setText(vocitem.getNativeLang() + "");
            holder.tv_count1.setText(vocitem.getLevel1() + "");
            holder.tv_count2.setText(vocitem.getLevel2() + "");

            return convertView;
        } else {
            // es wird nur das leere element angezeigt
            vocitem = new VocabularyItem("", "", "", "");
            vocitem.setForeignLang("Vokabelliste ist leer");
            vocitem.setDescription("Clicken für einen neuen Eintrag");

            holder.tv_description.setText(vocitem.getDescription() + "");
            holder.tv_foreign.setText(vocitem.getForeignLang() + "");
            holder.tv_unitId.setText("");
            holder.tv_native.setText("");
            holder.tv_count1.setText("");
            holder.tv_count2.setText("");
            return convertView;
        }
    }


    // ListAdapter Methoden

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        // TODO keine Ahnung, was hier hinkommt
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        // TODO keine Ahnung, was hier hinkommt
    }

    @Override
    public int getCount() {
        return Math.max(1, voclist.size());
    }

    @Override
    public Object getItem(int position) {
        return voclist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return Math.max(1, voclist.size());
    }

    @Override
    public boolean isEmpty() {
        return voclist.isEmpty();
    }


    // set the units

    public void setVoclist(ArrayList<VocabularyItem> voclist) {
        this.voclist = voclist;
    }
}