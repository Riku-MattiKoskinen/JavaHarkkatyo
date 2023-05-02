package com.example.harjoitustyo;

public class EarthLutemon extends Lutemon{
    public EarthLutemon(String name) {
        this.name = name;
        this.type = "Earth";
        this.ID = String.valueOf(Math.random() * Math.random() * 10000);
        this.attack = 9;
        this.defence = 0;
        this.experience = 0;
        this.health = 20;
        this.image = R.drawable.earth;

    }
}
