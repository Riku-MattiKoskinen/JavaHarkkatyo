package com.example.harjoitustyo.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.harjoitustyo.EarthLutemon;
import com.example.harjoitustyo.Lutemon;
import com.example.harjoitustyo.MarsLutemon;
import com.example.harjoitustyo.MercuryLutemon;
import com.example.harjoitustyo.MoonLutemon;
import com.example.harjoitustyo.R;
import com.example.harjoitustyo.Storage;
import com.example.harjoitustyo.SunLutemon;

import java.util.List;


public class FragmentHome extends Fragment {

    private EditText addName;
    private RadioGroup radioGroup;
    private Button addButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        addName = view.findViewById(R.id.textName);
        radioGroup = view.findViewById(R.id.rgLutemontype);

        addButton = view.findViewById(R.id.addButton);
        setAddButtonClickListener();
        return view;
    }


    public void addLutemon() {
        String name = addName.getText().toString();
        String type;

        if (radioGroup.getCheckedRadioButtonId() == R.id.rbEarth) {
            type = "Earth";
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.rbMoon) {
            type = "Moon";
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.rbMars) {
            type = "Mars";
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.rbSun) {
            type = "Sun";
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.rbMercury) {
            type = "Mercury";
        } else {
            type = "Earth";
        }


        switch (type) {
            case "Earth":
                EarthLutemon newEarthLutemon = new EarthLutemon(name);
                Storage.getInstance().addLutemon(newEarthLutemon);
                break;
            case "Moon":
                MoonLutemon newMoonLutemon = new MoonLutemon(name);
                Storage.getInstance().addLutemon(newMoonLutemon);
                break;
            case "Mars":
                MarsLutemon newMarsLutemon = new MarsLutemon(name);
                Storage.getInstance().addLutemon(newMarsLutemon);
                break;
            case "Sun":
                SunLutemon newSunLutemon = new SunLutemon(name);
                Storage.getInstance().addLutemon(newSunLutemon);
                break;
            case "Mercury":
                MercuryLutemon newMercuryLutemon = new MercuryLutemon(name);
                Storage.getInstance().addLutemon(newMercuryLutemon);
                break;
            default:
                break;
        }
        Storage.getInstance().printLutemonList();

    }

    public void setAddButtonClickListener() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLutemon();
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && addButton != null) {
            setAddButtonClickListener();
        }
    }


}