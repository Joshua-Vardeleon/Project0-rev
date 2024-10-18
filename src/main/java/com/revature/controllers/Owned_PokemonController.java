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
}
