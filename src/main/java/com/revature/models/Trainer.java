package com.revature.models;

public class Trainer {
    private int user_id;
    private String trainer_name;
    private String region;

    public Trainer(int user_id, String trainer_name, String region) {
        this.user_id = user_id;
        this.trainer_name = trainer_name;
        this.region = region;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
