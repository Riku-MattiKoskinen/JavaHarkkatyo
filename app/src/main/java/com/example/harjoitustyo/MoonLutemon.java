package com.example.harjoitustyo;

public class MoonLutemon extends Lutemon{
    public MoonLutemon(String name) {
        this.name = name;
        this.type = "Moon";
        this.ID = String.valueOf(Math.random() * Math.random() * 10000);
        this.attack = 7;
        this.defence = 2;
        this.experience = 0;
        this.health = 20;
        this.image = R.drawable.moon;

    }
}
