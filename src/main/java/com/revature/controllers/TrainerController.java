package com.revature.controllers;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.revature.DAO.Owned_PokemonDAO;
import com.revature.models.Trainer;
import com.revature.DAO.TrainerDAO;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class TrainerController {
    TrainerDAO tDAO = new TrainerDAO();

    public Handler getbyIDHandler = ctx -> {

        int user_id = Integer.parseInt(ctx.pathParam("userid"));


        Trainer trainer = tDAO.getTrainerByID(user_id);

        if(user_id <= 0){
            ctx.result("User id must be larger than 0");
            ctx.status(400); //Bad Request
        } else if (trainer == null){
            ctx.result("Trainer not found");
            ctx.status(404);
        } else {

            trainer.setRoster();
            ctx.json(trainer);
            ctx.status(200);
        }


    };

    public Handler getbyAllTrainersHandler = ctx -> {
        ArrayList<Trainer> trainers = tDAO.getAllTrainers();
        for(Trainer curr : trainers) {
            curr.setRoster();
        }

        ctx.json(trainers);
    };

    public Handler insertTrainersHandler = ctx -> {
        Trainer trainers = ctx.bodyAsClass(Trainer.class);

        if(trainers.getTrainer_name() == null || trainers.getTrainer_name().isBlank()){
            ctx.result("name field not filled");
            ctx.status(400);
        } else if(trainers.getRegion() == null || trainers.getRegion().isBlank()){
            ctx.result("region field not filled");
            ctx.status(400);
        } else {

            Trainer newTrainer = tDAO.insertTrainer(trainers);
            ctx.status(201);
            ctx.json(newTrainer);
        }
    };

    public Handler changeRegionHandler = ctx -> {

        Trainer data = ctx.bodyAsClass(Trainer.class);

        if(data.getUser_id() != 0) {
            tDAO.changeRegionbyID(data.getUser_id(), data.getRegion());
            Trainer trainer =  tDAO.getTrainerByID(data.getUser_id());
            if(trainer != null) {
                trainer.setRoster();
                ctx.json(trainer);
            } else {
                ctx.result("User not found, Please Try again");
                ctx.status(400);
            }

        } else if (!(data.getTrainer_name().isEmpty())) {
            tDAO.changeRegionbyName(data.getTrainer_name(), data.getRegion());
            Trainer trainer =  tDAO.getTrainerByName(data.getTrainer_name());
            if(trainer != null) {
                trainer.setRoster();
                ctx.json(trainer);
            }
            else {
                ctx.result("User not found, Please Try again");
                ctx.status(400);
            }

        }


    };
    public Handler battle = ctx -> {


        String data = ctx.body();
        String[] battleInput = data.split(",");
        int trainerid1;
        int trainerid2;

        trainerid1 =  Integer.parseInt(battleInput[0]);
        trainerid2 = Integer.parseInt(battleInput[1]);

        Owned_PokemonDAO pDAO = new Owned_PokemonDAO();
        ctx.result(pDAO.battle(trainerid1,trainerid2));

    };

}
