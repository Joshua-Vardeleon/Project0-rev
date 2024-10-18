package com.revature.models;

public class Owned_pokemon {
    private int level;

    private Pokedex pokedex;
    private int poke_fk_id;

//    private Trainer trainer;
    private String trainer_name;
    private int trainer_id_fk;
    private String name;

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
