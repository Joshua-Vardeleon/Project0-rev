package com.revature;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.controllers.Owned_PokemonController;
import com.revature.controllers.TrainerController;
import io.javalin.Javalin;
import com.revature.DAO.Owned_PokemonDAO;
import com.revature.DAO.PokeDexDAO;
import com.revature.DAO.TrainerDAO;
import com.revature.models.Owned_pokemon;
import com.revature.models.Pokedex;
import com.revature.models.Trainer;
import com.revature.utils.ConnectionUtil;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try(Connection conn = ConnectionUtil.getConnection()){
            System.out.println("CONNECTION SUCCESSFUL");
        } catch(SQLException e){
            e.printStackTrace(); //this is what tells us our error message (the red text)
            System.out.println("AHHHHHHHHHHHHHH CANT CONNECT AHHHHHH");
        }

//        TrainerDAO tDAO = new TrainerDAO();
//        tDAO.changeRegionbyID(5, "Kanto");
//        ArrayList<Trainer> allTrainers = tDAO.getAllTrainers();
//        for(Trainer currTrainer : allTrainers) {
//            System.out.println(currTrainer.getTrainer_name() + " from " + currTrainer.getRegion());
//        }

//        PokeDexDAO dexDAO = new PokeDexDAO();
//        ArrayList<Pokedex> search = dexDAO.getByType("Water","Bug");
//        for(Pokedex curr : search) {
//            System.out.println(curr);
//        }
//
//        TrainerDAO tDAO = new TrainerDAO();
//        Owned_PokemonDAO opDAO = new Owned_PokemonDAO();
//        opDAO.catchPokemon(tDAO.getTrainerByID(5));
//        ArrayList<Owned_pokemon> list = opDAO.getAllOwnedPokemon();
//        for(Owned_pokemon curr : list) {
//            System.out.println(curr);
//        }



        var app = Javalin.create().start(7000);

        //Exception handler for the before handler, telling the user to log in if the session is null
        app.exception(IllegalArgumentException.class, (e, ctx) -> {
            ctx.status(401);
            ctx.result(e.getMessage()); //we set this in the throw in the before handler above
        });

        /* We need create() to begin the instantiation of our Javalin object
         We need start() to actually start our Javalin app on a port of our choosing
         You can choose any port, but make sure it's a port that isn't being used (like 5432)
         A port is like a parking space, a place for your app sit where other apps can find it */

        //Very basic callable resource just for fun
        //NOTE: we sent a response from Launcher here, but Responses will really be in the Controllers
        app.get("/", ctx -> ctx.result("Hello Javalin and Postman!"));

        TrainerController tc = new TrainerController();
        Owned_PokemonController oc = new Owned_PokemonController();
        app.get("/trainers/{userid}", tc.getbyIDHandler);
        app.get("/trainers", tc.getbyAllTrainersHandler);
        app.post("/trainers", tc.insertTrainersHandler);
        app.patch("/trainers/{id}", tc.changeRegionHandler);
        app.get("/owned", oc.getAllOwnedPokemonHandler);
        app.post("/owned", oc.insertByName);
        app.get("/owned/catch", oc.catchRandom);

    }
}