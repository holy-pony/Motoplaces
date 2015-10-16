package ru.nitrobubbles.motoplaces.storage;

import java.util.ArrayList;

import ru.nitrobubbles.motoplaces.model.Motoplace;

/**
 * Created by konstantinaksenov on 16.10.15.
 */
public class MotoplacesStorage {
    ArrayList<Motoplace> motoplaces = new ArrayList<>();
    private static MotoplacesStorage ourInstance = new MotoplacesStorage();

    public static MotoplacesStorage getInstance() {
        return ourInstance;
    }

    private MotoplacesStorage() {
    }

    public void setMotoplaces(ArrayList<Motoplace> motoplaces) {
        this.motoplaces.clear();
        this.motoplaces.addAll(motoplaces);
    }

    public ArrayList<Motoplace> getMotoplaces() {
        return motoplaces;
    }
}
