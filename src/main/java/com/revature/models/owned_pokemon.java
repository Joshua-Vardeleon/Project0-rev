package com.revature.models;

public class owned_pokemon {
    private int owned_id;
    private int trainer_id_fk;
    private int level;
    private int poke_fk_id;

    public owned_pokemon(int owned_id, int trainer_id_fk, int level, int poke_fk_id) {
        this.owned_id = owned_id;
        this.trainer_id_fk = trainer_id_fk;
        this.level = level;
        this.poke_fk_id = poke_fk_id;
    }

    public int getOwned_id() {
        return owned_id;
    }

    public void setOwned_id(int owned_id) {
        this.owned_id = owned_id;
    }

    public int getTrainer_id_fk() {
        return trainer_id_fk;
    }

    public void setTrainer_id_fk(int trainer_id_fk) {
        this.trainer_id_fk = trainer_id_fk;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPoke_fk_id() {
        return poke_fk_id;
    }

    public void setPoke_fk_id(int poke_fk_id) {
        this.poke_fk_id = poke_fk_id;
    }

    @Override
    public String toString() {
        return "owned_pokemon{" +
                "owned_id=" + owned_id +
                ", trainer_id_fk=" + trainer_id_fk +
                ", level=" + level +
                ", poke_fk_id=" + poke_fk_id +
                '}';
    }
}
