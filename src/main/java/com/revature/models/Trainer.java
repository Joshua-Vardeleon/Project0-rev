package com.revature.models;

import com.revature.DAO.Owned_PokemonDAO;
import com.revature.DAO.PokeDexDAO;

import java.util.ArrayList;

public class Trainer extends Object{
    private int user_id;
    private String trainer_name;
    private String region;
    private ArrayList<String> roster = new ArrayList<>();

    public Trainer() {

    }
    public Trainer(int user_id, String trainer_name, String region) {
        this.user_id = user_id;
        this.trainer_name = trainer_name;
        this.region = region;
    }
    public Trainer(String trainer_name, String region) {
        this.trainer_name = trainer_name;
        this.region = region;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setRoster() {
        Owned_PokemonDAO mon = new Owned_PokemonDAO();
        PokeDexDAO dex = new PokeDexDAO();
        try {
            for(Owned_pokemon poke : mon.getbyTrainerID(user_id)) {
                roster.add(dex.getByID(poke.getPoke_fk_id()).getPoke_name());
            }
        } catch(NullPointerException _) {

        }




    }


    public ArrayList<String> getRoster() {
        return roster;
    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public void setTrainer_name(String trainer_name) {
        this.trainer_name = trainer_name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "user_id=" + user_id +
                ", trainer_name='" + trainer_name + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
