package com.revature.models;

public class Pokedex {

    private int poke_no;
    private String poke_name;
    private String poke_type1;
    private String poke_type2;

    public Pokedex(int poke_no, String poke_name, String poke_type1, String poke_type2) {
        this.poke_no = poke_no;
        this.poke_name = poke_name;
        this.poke_type1 = poke_type1;
        this.poke_type2 = poke_type2;
    }

    public int getPoke_no() {
        return poke_no;
    }

    public void setPoke_no(int poke_no) {
        this.poke_no = poke_no;
    }

    public String getPoke_name() {
        return poke_name;
    }

    public void setPoke_name(String poke_name) {
        this.poke_name = poke_name;
    }

    public String getPoke_type1() {
        return poke_type1;
    }

    public void setPoke_type1(String poke_type1) {
        this.poke_type1 = poke_type1;
    }

    public String getPoke_type2() {
        return poke_type2;
    }

    public void setPoke_type2(String poke_type2) {
        this.poke_type2 = poke_type2;
    }

    @Override
    public String toString() {
        return "pokedex{" +
                "poke_no=" + poke_no +
                ", poke_name='" + poke_name + '\'' +
                ", poke_type1='" + poke_type1 + '\'' +
                ", poke_type2='" + poke_type2 + '\'' +
                '}';
    }
}


