package com.revature.controllers;

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
        int user_id = Integer.parseInt(ctx.pathParam("id"));
        String region = ctx.body();
        tDAO.changeRegionbyID(user_id, region);
        Trainer trainer =  tDAO.getTrainerByID(user_id);
        trainer.setRoster();
        ctx.json(trainer);
    };

}
