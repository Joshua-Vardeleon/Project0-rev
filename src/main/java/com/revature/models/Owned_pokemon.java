package com.revature.models;

import com.revature.DAO.PokeDexDAO;
import com.revature.DAO.TrainerDAO;

public class Owned_pokemon {
    private int level;

    private Pokedex pokedex;
    private int poke_fk_id;

//    private Trainer trainer;
    private String trainer_name;
    private int trainer_id_fk;
    private String name;
    private int id;

    public Owned_pokemon() {

    }
    public Owned_pokemon(int level, Pokedex pokedex, Trainer trainer) {
        this.level = level;
        this.pokedex = pokedex;
        poke_fk_id = pokedex.getPoke_no();
        name = pokedex.getPoke_name();
        trainer_name = trainer.getTrainer_name();
        trainer_id_fk = trainer.getUser_id();
    }

    public Owned_pokemon(int level, int poke_fk_id, int trainer_id_fk) {
        this.level = level;
        this.poke_fk_id = poke_fk_id;
        this.trainer_id_fk = trainer_id_fk;
        TrainerDAO tDAO = new TrainerDAO();
        PokeDexDAO pDAO = new PokeDexDAO();
        pokedex = pDAO.getByID(poke_fk_id);
        name = pDAO.getByID(poke_fk_id).getPoke_name();
        trainer_name = tDAO.getTrainerByID(trainer_id_fk).getTrainer_name();
    }
    public Owned_pokemon(int level, int poke_fk_id, int trainer_id_fk, int id) {
        this.level = level;
        this.poke_fk_id = poke_fk_id;
        this.trainer_id_fk = trainer_id_fk;
        TrainerDAO tDAO = new TrainerDAO();
        PokeDexDAO pDAO = new PokeDexDAO();
        pokedex = pDAO.getByID(poke_fk_id);
        name = pDAO.getByID(poke_fk_id).getPoke_name();
        trainer_name = tDAO.getTrainerByID(trainer_id_fk).getTrainer_name();
        this.id = id;
    }

    public Owned_pokemon(int level, String name, String trainer_name) {
        this.level = level;
        this.name = name;
        this.trainer_name = trainer_name;
        PokeDexDAO pDAO = new PokeDexDAO();
        poke_fk_id = pDAO.getByName(name).getPoke_no();
        pokedex = pDAO.getByID(poke_fk_id);
        TrainerDAO tDAO = new TrainerDAO();
        trainer_id_fk = tDAO.getTrainerByName(trainer_name).getUser_id();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public void setTrainer_name(String trainer_name) {
        this.trainer_name = trainer_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Pokedex getPokedex() {
        return pokedex;
    }

    public void setPokedex(Pokedex pokedex) {
        this.pokedex = pokedex;
    }

    public int getPoke_fk_id() {
        return poke_fk_id;
    }

    public void setPoke_fk_id(int poke_fk_id) {
        this.poke_fk_id = poke_fk_id;
    }

//    public Trainer getTrainer() {
//        return trainer;
//    }
//
//    public void setTrainer(Trainer trainer) {
//        this.trainer = trainer;
//    }

    public int getTrainer_id_fk() {
        return trainer_id_fk;
    }

    public void setTrainer_id_fk(int trainer_id_fk) {
        this.trainer_id_fk = trainer_id_fk;
    }

    @Override
    public String toString() {
        return "Owned_pokemon{" +
                "level=" + level +
                ", pokedex=" + pokedex +
                ", poke_fk_id=" + poke_fk_id +
                ", trainer_name='" + trainer_name + '\'' +
                ", trainer_id_fk=" + trainer_id_fk +
                ", name='" + name + '\'' +
                '}';
    }
}
