package com.revature.DAO;
import com.revature.models.Trainer;
import com.revature.utils.ConnectionUtil;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
/*
TODO
- get trainer by id
- get all trainers
- get all by region
 */
public class TrainerDAO {

    public Trainer insertTrainer(Trainer trainer) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO trainer (trainer_name , region) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, trainer.getTrainer_name());
            ps.setString(2, trainer.getRegion());

            ps.executeUpdate();

            return trainer;
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("could not insert");

        }
        return null;
    }

    public Trainer getTrainerByID(int id) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM trainer WHERE user_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Trainer currTrainer = new Trainer(
                        rs.getInt("user_id"),
                        rs.getString("trainer_name"),
                        rs.getString("region"));
                currTrainer.setUser_id(id);
                return currTrainer;
            }
        } catch (SQLException e) {
            System.out.println("Couldnt connect to database");
        }
        return null;
    }
    public int changeRegionbyID(int userID, String changeRegion){
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE trainer SET  region = ? Where user_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, changeRegion);
            ps.setInt(2,userID);

            ps.executeUpdate();

            return userID;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in trying to update user's region");
        }
        return 0;
    }
    public Trainer getTrainerByName(String name) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM trainer WHERE trainer_name = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Trainer currTrainer = new Trainer(
                        rs.getInt("user_id"),
                        rs.getString("trainer_name"),
                        rs.getString("region"));

                return currTrainer;
            }
        } catch (SQLException e) {
            System.out.println("Couldnt connect to database");
        }
        return null;
    }
    public ArrayList<Trainer> getAllTrainers(){
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM trainer";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            ArrayList<Trainer> trainers = new ArrayList<>();
            while(rs.next()) {
                Trainer currTrainer = new Trainer(
                        rs.getInt("user_id"),
                        rs.getString("trainer_name"),
                        rs.getString("region"));

                trainers.add(currTrainer);
            }
            return trainers;

        } catch (SQLException e) {
            System.out.println("Couldnt connect to database");
        }
        return null;
    }
    public ArrayList<Trainer> getTrainerByRegion(String region){
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM trainer WHERE region = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, region);

            ResultSet rs = ps.executeQuery();
            ArrayList<Trainer> trainers = new ArrayList<>();
            while(rs.next()) {
                Trainer currTrainer = new Trainer(
                        rs.getInt("user_id"),
                        rs.getString("trainer_name"),
                        rs.getString("region"));

                trainers.add(currTrainer);
            }
            return trainers;

        } catch (SQLException e) {
            System.out.println("Couldnt connect to database");
            e.printStackTrace();
        }
        return null;
    }
}
