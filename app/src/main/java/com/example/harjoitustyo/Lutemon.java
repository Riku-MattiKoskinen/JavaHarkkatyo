package com.example.harjoitustyo;

import java.io.Serializable;

public class Lutemon implements Serializable {
    protected String name;
    protected String type;
    protected int attack;
    protected int defence;
    protected int experience;
    protected int health;
    protected String ID;
    protected int image;


    public String getName() {
        return name;
    }
    public  int getImage() {return image;}

    public String getType() {
        return type;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getExperience() {
        return experience;
    }

    public int getHealth() {
        return health;
    }

    public String getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public String toString() {
        return this.name;
    }

    public void train() {
        if (this.experience == 0) {
            this.attack += 1;
            this.defence += 1;
        } else {
            this.attack += this.experience;
            this.defence += this.experience;
        }
    }

}
