package com.example.khalessi.gretas_vokabeltrainer;

/**
 * Created by Alexandra Filbert on 04.01.18.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.khalessi.gretas_vokabeltrainer.database_vocabulary.Units;

import java.util.ArrayList;


public class UnitCustomAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Units> car;

    public UnitCustomAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context, textViewResourceId, objects);

        this.context = context;
        car = objects;

    }

    private class ViewHolder {
        TextView tv_benutzername;
        TextView tv_unitId;
        TextView tv_description;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.unit_details, null);

            holder = new ViewHolder();
            holder.tv_unitId = (TextView) convertView.findViewById(R.id.tv_unitId);
            holder.tv_benutzername = (TextView) convertView.findViewById(R.id.tv_userName);
            holder.tv_description = (TextView) convertView.findViewById(R.id.tv_description);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Units individualCar = car.get(position);
        holder.tv_description.setText(context.getString(R.string.listview_unitname) + individualCar.getDescription() + "");
        holder.tv_benutzername.setText(context.getString(R.string.listview_username) + individualCar.getUser() + "");
        holder.tv_unitId.setText(context.getString(R.string.listview_unitId) + individualCar.getUnitId());
        return convertView;


    }
}
