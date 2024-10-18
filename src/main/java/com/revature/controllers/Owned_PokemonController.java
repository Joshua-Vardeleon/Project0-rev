package com.revature.controllers;


import com.revature.DAO.Owned_PokemonDAO;
import com.revature.models.Owned_pokemon;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class Owned_PokemonController {
    Owned_PokemonDAO pDAO = new Owned_PokemonDAO();

    public Handler  getAllOwnedPokemonHandler = ctx -> {
        ArrayList<Owned_pokemon> mons = pDAO.getAllOwnedPokemon();
        ctx.json(mons);

    };
    public Handler  insertByName = ctx -> {

        ctx.result("Enter as: Level,trainer name,pokemon name");
        String userInput = ctx.body();
        int level;
        String trainer_name;
        String pokemon_name;
        String[] substring = userInput.split(",");
        if(substring.length == 3) {
            level = Integer.parseInt(substring[0]);
            trainer_name = substring[1];
            pokemon_name = substring[2];
            ctx.json(pDAO.insertByName(level, trainer_name, pokemon_name));

        } else {
            System.out.println("input is wrong, try again");
        }

    };

    public Handler catchRandom = ctx -> {
        ctx.result("Enter trainer name");
        String name = ctx.body();

        if(!name.isBlank() || !(name == null)){

            ctx.json(pDAO.catchNamePokemon(name));
        }

    };



}
