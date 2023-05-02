package com.example.harjoitustyo.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.harjoitustyo.Lutemon;
import com.example.harjoitustyo.LutemonSpinnerAdapter;
import com.example.harjoitustyo.R;
import com.example.harjoitustyo.Storage;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentGym#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentGym extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private TextView countDownView;
    private CountDownTimer countDownTimer;
    private Button trainButton;
    long timeLeftInMilliseconds = 10000; // 10 seconds
    boolean timeRunning;

    public FragmentGym() {
        // Required empty public constructor
    }

    public static FragmentGym newInstance(String param1, String param2) {
        FragmentGym fragment = new FragmentGym();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gym, container, false);

        Spinner lutemonSpinner = view.findViewById(R.id.spinner);

        Storage.getInstance().loadLutemons(getContext());
        countDownView = view.findViewById(R.id.countDownView);

        ArrayList<Lutemon> lutemonsList = Storage.getInstance().getLutemons();

        LutemonSpinnerAdapter adapter = new LutemonSpinnerAdapter(getContext(), lutemonsList);

        lutemonSpinner.setAdapter(adapter);

        trainButton = view.findViewById(R.id.trainingButton);
        trainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lutemon selectedLutemon = (Lutemon) lutemonSpinner.getSelectedItem();
                startStop(selectedLutemon);
                adapter.notifyDataSetChanged();
            }
        });
        updateTimer();

        return view;
    }
    public void startStop(Lutemon lutemon) {
        if (timeRunning) {
            stopTimer();
        } else {
            startTimer(lutemon);
        }
    }
    public void startTimer(Lutemon lutemon) {
        trainButton.setVisibility(View.INVISIBLE);
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                timeRunning = false;
                timeLeftInMilliseconds = 10000;
                updateTimer();
                trainButton.setVisibility(View.VISIBLE);
                lutemon.train();
            }
        }.start();

        timeRunning = true;
    }
    public void stopTimer() {
        countDownTimer.cancel();
        timeRunning = false;
    }
    public void updateTimer() {
        int seconds = (int) timeLeftInMilliseconds / 1000;
        String timeLeftText;
        if (seconds == 0) {
            timeLeftText = "Trained!";
        } else {
            timeLeftText = "" + seconds;
        }


        countDownView.setText(timeLeftText);
    }

}