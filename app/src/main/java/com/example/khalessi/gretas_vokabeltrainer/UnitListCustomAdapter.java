package com.example.khalessi.gretas_vokabeltrainer;

/**
 * Created by Alexandra Filbert on 04.01.18.
 */


import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.khalessi.gretas_vokabeltrainer.database.room.Unit;

import java.util.ArrayList;
import java.util.List;


public class UnitListCustomAdapter extends ArrayAdapter {
    private Context context;
    private List<Unit> units;

    public UnitListCustomAdapter(Context context, int textViewResourceId, List objects) {
        super(context, textViewResourceId, objects);

        this.context = context;
        units = objects;

    }

    private class ViewHolder {
        TextView tv_benutzername;
        TextView tv_unitId;
        TextView tv_description;
        TextView tv_title;
        TextView tv_voc_size;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.details_list_unit, null);

            holder = new ViewHolder();
            /*
            holder.tv_unitId = (TextView) convertView.findViewById(R.id.tv_unitId);
            holder.tv_benutzername = (TextView) convertView.findViewById(R.id.tv_userName);
            holder.tv_description = (TextView) convertView.findViewById(R.id.tv_description);
            */
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_voc_size = (TextView) convertView.findViewById(R.id.tv_voc_list_size);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Unit unit = units.get(position);
        /*
        holder.tv_description.setText(context.getString(R.string.listview_unitname) + unit.getDescription() + "");
        holder.tv_benutzername.setText(context.getString(R.string.listview_username) + unit.getUser() + "");
        holder.tv_unitId.setText(context.getString(R.string.listview_unitId) + unit.getUnitId());
        //holder.tv_title.setText(context.getString(R.string.listview_title) + unit.getTitle());
        */
        holder.tv_title.setText(unit.getTitle());
        holder.tv_voc_size.setText("Anzahl Wörter: "+ unit.getVoclist().size());
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
        return units.size();
    }

    @Override
    public Object getItem(int position) {
        return units.get(position);
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
        return Math.max(1, units.size());
    }

    @Override
    public boolean isEmpty() {
        return units.isEmpty();
    }



    // set the units

    public void setUnits(List<Unit> units) {
        this.units = units;
    }
}
