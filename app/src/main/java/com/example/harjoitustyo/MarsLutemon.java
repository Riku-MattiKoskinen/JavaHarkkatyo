package com.example.harjoitustyo;

public class MarsLutemon extends Lutemon{

    public MarsLutemon(String name) {
        this.name = name;
        this.type = "Mars";
        this.ID = String.valueOf(Math.random() * Math.random() * 10000);
        this.attack = 6;
        this.defence = 3;
        this.experience = 0;
        this.health = 20;
        this.image = R.drawable.mars;

    }


}
