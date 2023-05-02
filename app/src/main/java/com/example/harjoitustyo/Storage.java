package com.example.harjoitustyo;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    private static Storage storage = null;

    private Storage() {
    }

    public static Storage getInstance() {
        if(storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    public ArrayList<Lutemon> getLutemons() {
        return lutemons;
    }

    public void addLutemon(Lutemon lutemon) {
        lutemons.add(lutemon);
    }

    public void loadLutemons(Context context) {
        try {
            ObjectInputStream lutemonReader = new ObjectInputStream(context.openFileInput("lutemons.data"));
            lutemons = (ArrayList<Lutemon>) lutemonReader.readObject();
            lutemonReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Lutemonien lukeminen ei onnistunut");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Lutemonien lukeminen ei onnistunut");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Lutemonien lukeminen ei onnistunut");
            e.printStackTrace();
        }
    }
    public void printLutemonList() {
        List<Lutemon> lutemons = getLutemons();
        System.out.println("Current Lutemon list:");
        for (Lutemon lutemon : lutemons) {
            System.out.println(lutemon.getName() + " - " + lutemon.getType());
        }
    }

}
