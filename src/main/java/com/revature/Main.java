package com.revature;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.DAO.TrainerDAO;
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

        TrainerDAO tDAO = new TrainerDAO();

//        ArrayList<Trainer> allTrainers = tDAO.getTrainerByRegion("Kanto");
//        for(Trainer currTrainer : allTrainers) {
//            System.out.println(currTrainer.getTrainer_name() + " from " + currTrainer.getRegion());
//        }
    }
}