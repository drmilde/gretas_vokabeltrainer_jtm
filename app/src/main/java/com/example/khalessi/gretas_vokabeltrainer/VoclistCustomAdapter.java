package com.example.khalessi.gretas_vokabeltrainer;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.khalessi.gretas_vokabeltrainer.database.Unit;
import com.example.khalessi.gretas_vokabeltrainer.database.VocabularyItem;

import java.util.ArrayList;

/**
 * Created by milde on 08.01.18.
 */

public class VoclistCustomAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<VocabularyItem> voclist;

    public VoclistCustomAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context, textViewResourceId, objects);

        this.context = context;
        voclist = objects;
    }

    private class ViewHolder {
        TextView tv_foreign;
        TextView tv_native;
        TextView tv_description;
        TextView tv_unitId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VoclistCustomAdapter.ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.voclist_details, null);

            holder = new VoclistCustomAdapter.ViewHolder();
            holder.tv_unitId = (TextView) convertView.findViewById(R.id.tv_voc_unitId);
            holder.tv_foreign = (TextView) convertView.findViewById(R.id.tv_voc_foreignLang);
            holder.tv_description = (TextView) convertView.findViewById(R.id.tv_voc_Description);
            holder.tv_native = (TextView) convertView.findViewById(R.id.tv_voc_nativeLang);
            convertView.setTag(holder);

        } else {
            holder = (VoclistCustomAdapter.ViewHolder) convertView.getTag();
        }

        // hier erfolgt die Anzeige
        VocabularyItem vocitem = voclist.get(position);
        holder.tv_description.setText("Description: " + vocitem.getDescription() + "");
        holder.tv_foreign.setText("Foreign: " + vocitem.getForeignLang() + "");
        holder.tv_unitId.setText("UniId" + vocitem.getUnitId());
        holder.tv_native.setText("Native: " + vocitem.getNativeLang());
        return convertView;
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
        return voclist.size();
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
        return voclist.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }



    // set the units

    public void setVoclist(ArrayList<VocabularyItem> voclist) {
        this.voclist = voclist;
    }
}