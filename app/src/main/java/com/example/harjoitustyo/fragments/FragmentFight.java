package com.example.harjoitustyo.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.harjoitustyo.Lutemon;
import com.example.harjoitustyo.R;
import com.example.harjoitustyo.Storage;

import java.util.ArrayList;
import java.util.List;

public class FragmentFight extends Fragment {

    private TextView resultsTextView;
    private ScrollView resultsScrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fight, container, false);

        LinearLayout lutemonCheckboxLayout = view.findViewById(R.id.lutemonCheckboxLayout);
        createLutemonCheckboxes(lutemonCheckboxLayout);

        // Find the BATTLE button and the results TextView
        Button battleButton = view.findViewById(R.id.battleButton);
        resultsTextView = view.findViewById(R.id.resultsTextView);
        resultsScrollView = view.findViewById(R.id.resultsScrollView);


        // Set the OnClickListener for the BATTLE button
        battleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Lutemon> selectedLutemons = getSelectedLutemons(lutemonCheckboxLayout);
                if (selectedLutemons.size() == 2) {
                    performBattle(selectedLutemons.get(0), selectedLutemons.get(1));
                } else {
                    Toast.makeText(getContext(), "Select exactly two Lutemons for battle", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void createLutemonCheckboxes(LinearLayout lutemonCheckboxLayout) {
        Storage storage = Storage.getInstance();
        List<Lutemon> lutemons = storage.getLutemons();

        for (Lutemon lutemon : lutemons) {
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(lutemon.getName());
            checkBox.setTag(lutemon);
            lutemonCheckboxLayout.addView(checkBox);
        }
    }

    private List<Lutemon> getSelectedLutemons(LinearLayout lutemonCheckboxLayout) {
        List<Lutemon> selectedLutemons = new ArrayList<>();
        for (int i = 0; i < lutemonCheckboxLayout.getChildCount(); i++) {
            CheckBox checkBox = (CheckBox) lutemonCheckboxLayout.getChildAt(i);
            if (checkBox.isChecked()) {
                selectedLutemons.add((Lutemon) checkBox.getTag());
            }
        }
        return selectedLutemons;
    }

    private void performBattle(Lutemon lutemon1, Lutemon lutemon2) {
        Log.d("FragmentFight", "performBattle() called");
        StringBuilder battleLog = new StringBuilder();
        int originalHealthLutemon1 = lutemon1.getHealth();
        int originalHealthLutemon2 = lutemon2.getHealth();

        // Display the attributes of the contestants
        battleLog.append("Battle starts! Contestants are:\n");

        // Perform the battle
        final Handler handler = new Handler();
        final Runnable battleRound = new Runnable() {
            @Override
            public void run() {
                Log.d("FragmentFight", "battleRound.run() called");
                battleLog.append("(" + lutemon1.getType() + ") ").append(lutemon1.getName())
                        .append(" (HP: ").append(lutemon1.getHealth()).append(")\n");
                battleLog.append("(" + lutemon2.getType() + ") ").append(lutemon2.getName())
                        .append(" (HP: ").append(lutemon2.getHealth()).append(")\n");
                // Lutemon 1 attacks
                int damageToLutemon2 = Math.max(0, lutemon1.getAttack() - lutemon2.getDefence());
                lutemon2.setHealth(lutemon2.getHealth() - damageToLutemon2);
                battleLog.append(lutemon1.getName()).append(" attacks ").append(lutemon2.getName())
                        .append(" for ").append(damageToLutemon2).append(" damage.\n");

                // Lutemon 2 attacks
                int damageToLutemon1 = Math.max(0, lutemon2.getAttack() - lutemon1.getDefence());
                lutemon1.setHealth(lutemon1.getHealth() - damageToLutemon1);
                battleLog.append(lutemon2.getName()).append(" attacks ").append(lutemon1.getName())
                        .append(" for ").append(damageToLutemon1).append(" damage.\n");
                resultsTextView.setText(battleLog.toString());
                resultsScrollView.fullScroll(View.FOCUS_DOWN);

                // If neither Lutemon has been defeated, schedule the next round
                if (lutemon1.getHealth() > 0 && lutemon2.getHealth() > 0) {
                    handler.postDelayed(this, 1000); // Wait 1000ms (1 second) before executing the next round
                } else {
                    if (lutemon1.getHealth() <= 0 && lutemon2.getHealth() <= 0) {
                        // It's a tie
                        resultsTextView.setText(battleLog.toString());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                resultsTextView.append("It's a tie!");
                                lutemon1.setHealth(originalHealthLutemon1);
                                lutemon2.setHealth(originalHealthLutemon2);
                                resultsScrollView.fullScroll(View.FOCUS_DOWN);
                            }
                        }, 1000);
                    } else {
                        Lutemon winner, loser;
                        if (lutemon1.getHealth() > 0) {
                            winner = lutemon1;
                            loser = lutemon2;
                        } else {
                            winner = lutemon2;
                            loser = lutemon1;
                        }

                        // Restore the winner's health and increase its experience by one
                        winner.setHealth(winner == lutemon1 ? originalHealthLutemon1 : originalHealthLutemon2);
                        winner.setExperience(winner.getExperience() + 1);

                        // Remove the loser from the list
                        Storage storage = Storage.getInstance();
                        storage.getLutemons().remove(loser);
                        LinearLayout lutemonCheckboxLayout = getView().findViewById(R.id.lutemonCheckboxLayout);
                        updateLutemonCheckboxes(lutemonCheckboxLayout);


                        // Update the results TextView with the battle log and post-battle winner stats
                        resultsTextView.setText(battleLog.toString());
                        resultsScrollView.fullScroll(View.FOCUS_DOWN);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                resultsTextView.append(winner.getName() + " is winner!");
                                resultsScrollView.fullScroll(View.FOCUS_DOWN);
                            }
                        }, 1000);
                    }
                }
            }
        };


        handler.post(battleRound);
    }
    private void updateLutemonCheckboxes(LinearLayout lutemonCheckboxLayout) {
        lutemonCheckboxLayout.removeAllViews();
        createLutemonCheckboxes(lutemonCheckboxLayout);
    }
    @Override
    public void onResume() {
        super.onResume();
        LinearLayout lutemonCheckboxLayout = getView().findViewById(R.id.lutemonCheckboxLayout);
        updateLutemonCheckboxes(lutemonCheckboxLayout);
    }


}

