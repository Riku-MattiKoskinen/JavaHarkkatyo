package com.example.harjoitustyo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LutemonSpinnerAdapter extends ArrayAdapter<Lutemon> {
    public LutemonSpinnerAdapter(Context context, ArrayList<Lutemon> lutemons) {
        super(context, 0, lutemons);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initLutemonView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initLutemonView(position, convertView, parent);
    }

    private View initLutemonView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lutemon_spinner_item, parent, false);
        }

        ImageView lutemonImage = convertView.findViewById(R.id.lutemonImage);
        TextView lutemonName = convertView.findViewById(R.id.lutemonName);

        Lutemon lutemon = getItem(position);

        if (lutemon != null) {
            lutemonImage.setImageResource(lutemon.getImage());
            lutemonName.setText(lutemon.getName());
        }

        return convertView;
    }
}

