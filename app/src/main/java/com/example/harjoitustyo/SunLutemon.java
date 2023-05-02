package com.example.harjoitustyo;

public class SunLutemon extends Lutemon{
    public SunLutemon(String name) {
        this.name = name;
        this.type = "Sun";
        this.ID = String.valueOf(Math.random() * Math.random() * 10000);
        this.attack = 8;
        this.defence = 1;
        this.experience = 0;
        this.health = 20;

        this.image = R.drawable.sun;

    }
}
