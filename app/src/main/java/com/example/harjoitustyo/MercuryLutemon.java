package com.example.harjoitustyo;

public class MercuryLutemon extends Lutemon{
    public MercuryLutemon(String name) {
        this.name = name;
        this.type = "Mercury";
        this.ID = String.valueOf(Math.random() * Math.random() * 10000);
        this.attack = 5;
        this.defence = 5;
        this.experience = 0;
        this.health = 20;
        this.image = R.drawable.mercury;

    }
}
