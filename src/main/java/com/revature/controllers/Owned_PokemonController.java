package com.revature.controllers;


import com.revature.DAO.Owned_PokemonDAO;
import com.revature.models.Owned_pokemon;
import com.revature.models.Trainer;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class Owned_PokemonController {
    Owned_PokemonDAO pDAO = new Owned_PokemonDAO();

    public Handler  getAllOwnedPokemonHandler = ctx -> {
        ArrayList<Owned_pokemon> mons = pDAO.getAllOwnedPokemon();
        ctx.json(mons);

    };
    public Handler  insertByName = ctx -> {

        Owned_pokemon userInput = ctx.bodyAsClass(Owned_pokemon.class);
        int level;
        String name;
        String trainer_name;
        Owned_pokemon poke;
        int trainer_id_fk;
        int poke_fk_id;
        if(!(userInput.getLevel() == 0 || userInput.getTrainer_name() == null || userInput.getName() == null)) {
            level = userInput.getLevel();
            name = userInput.getName();
            trainer_name = userInput.getTrainer_name();
            poke = pDAO.insertByName(level, trainer_name, name);
            ctx.json(poke);
        }else if(!(userInput.getPoke_fk_id() == 0 || userInput.getLevel() == 0 || userInput.getTrainer_id_fk() == 0)){
            pDAO.insertOwnedPoke(userInput);
            level = userInput.getLevel();
            poke_fk_id = userInput.getPoke_fk_id();
            trainer_id_fk = userInput.getTrainer_id_fk();
            poke = new Owned_pokemon(level, poke_fk_id,trainer_id_fk);
            ctx.json(poke);

        } else if (userInput.getTrainer_name() == null) {
            ctx.result("Trainer name is invalid");
            ctx.status(400);
        } else if (userInput.getLevel() <= 0) {
            ctx.result("level is invalid");
            ctx.status(400);
        }else if (userInput.getName() == null) {
            ctx.result("Pokemon is not found in the pokedex");
            ctx.status(400);
        }


    };

    public Handler catchRandom = ctx -> {
        Owned_pokemon name = ctx.bodyAsClass(Owned_pokemon.class);


        if(!(name.getTrainer_name() == null)){

            ctx.json(pDAO.catchNamePokemon(name.getTrainer_name()));
        }

    };

    public Handler releasePokemon = ctx -> {
        Owned_pokemon id = ctx.bodyAsClass(Owned_pokemon.class);
        Owned_PokemonDAO pDAO = new Owned_PokemonDAO();
        Owned_pokemon curr = pDAO.releasePokemonbyID(id.getId());




        if(!(id.getId() == 0 || curr == null)){

            ctx.json(curr);
        }
        else {
            ctx.result("That owned id does not exist");
            ctx.status(200);
        }

    };

    public Handler getOwnedbyName = ctx -> {
        Trainer search = ctx.bodyAsClass(Trainer.class);
        Owned_PokemonDAO pDAO = new Owned_PokemonDAO();
        ArrayList <Owned_pokemon> curr = pDAO.getbyTrainerID(search.getUser_id());




        if(curr != null){

            ctx.json(curr);
        }
        else {
            ctx.result("trainer_id does not exist");
            ctx.status(200);
        }

    };



}
