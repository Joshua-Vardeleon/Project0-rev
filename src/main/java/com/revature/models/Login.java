package com.revature.models;

public class Login {
    
    private int login_id;
    private int trainer_id_fk;
    private String username;
    private String password;

    public Login(int login_id, int trainer_id_fk, String username, String password) {
        this.login_id = login_id;
        this.trainer_id_fk = trainer_id_fk;
        this.username = username;
        this.password = password;
    }

    public int getLogin_id() {
        return login_id;
    }

    public void setLogin_id(int login_id) {
        this.login_id = login_id;
    }

    public int getTrainer_id_fk() {
        return trainer_id_fk;
    }

    public void setTrainer_id_fk(int trainer_id_fk) {
        this.trainer_id_fk = trainer_id_fk;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "login_id=" + login_id +
                ", trainer_id_fk=" + trainer_id_fk +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
