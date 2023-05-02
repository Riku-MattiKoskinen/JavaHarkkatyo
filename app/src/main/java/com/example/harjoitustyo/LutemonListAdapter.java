package com.example.harjoitustyo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class LutemonListAdapter extends RecyclerView.Adapter<LutemonListAdapter.LutemonViewHolder> {

    Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    public LutemonListAdapter(Context context, ArrayList<Lutemon> lutemons) {

        this.context = context;
        this.lutemons = lutemons;

    }

    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.lutemon_view, parent, false);


        return new LutemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        Lutemon lutemon = lutemons.get(position);
        holder.textName.setText(lutemon.getName());
        holder.textAttack.setText(String.valueOf(lutemon.getAttack()));
        holder.textDefence.setText(String.valueOf(lutemon.getDefence()));
        holder.textHealth.setText(String.valueOf(lutemon.getHealth()));
        holder.textExp.setText(String.valueOf(lutemon.getExperience()));
        holder.imageView.setImageResource(lutemon.getImage());
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }

    public class LutemonViewHolder extends RecyclerView.ViewHolder{

        ImageView  imageView;
        TextView textName, textAttack, textDefence, textHealth, textExp;


        public LutemonViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textName = itemView.findViewById(R.id.textName);
            textAttack = itemView.findViewById(R.id.textAttack);
            textDefence = itemView.findViewById(R.id.textDefence);
            textHealth = itemView.findViewById(R.id.textHealth);
            textExp = itemView.findViewById(R.id.textExp);
        }
    }

    public void updateLutemons(ArrayList<Lutemon> newLutemons) {
        this.lutemons = newLutemons;
        notifyDataSetChanged();
    }

}
