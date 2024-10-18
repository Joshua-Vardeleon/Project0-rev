package com.revature.DAO;
import com.revature.models.Owned_pokemon;
import com.revature.models.Pokedex;
import com.revature.models.Trainer;
import com.revature.utils.ConnectionUtil;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
public class Owned_PokemonDAO {
    public Owned_pokemon insertOwnedPoke(Owned_pokemon ownpoke) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO owned_pokemon (trainer_id_fk, level, poke_fk_id) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, ownpoke.getTrainer_id_fk());
            ps.setInt(2, ownpoke.getLevel());
            ps.setInt(3, ownpoke.getPoke_fk_id());

            ps.executeUpdate();

            return ownpoke;
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("could not insert");

        }
        return null;



    }
    public Owned_pokemon insertByName(int level, String trainer,String pokemon) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO owned_pokemon (trainer_id_fk, level, poke_fk_id) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            TrainerDAO tDAO = new TrainerDAO();
            PokeDexDAO pDAO = new PokeDexDAO();
            ps.setInt(1, tDAO.getTrainerByName(trainer).getUser_id());
            ps.setInt(2, level);
            ps.setInt(3, pDAO.getByName(pokemon).getPoke_no());

            ps.executeUpdate();

            return new Owned_pokemon(level, pDAO.getByName(pokemon),tDAO.getTrainerByName(trainer));
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("could not insert");

        }
        return null;



    }
    public Owned_pokemon catchPokemon(Trainer trainer) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO owned_pokemon (trainer_id_fk, level, poke_fk_id) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            int randlevel = (int)(Math.random() * 100) + 1;
            int randpokemon = (int)(Math.random() * 809) + 1;

            PokeDexDAO pDAO = new PokeDexDAO();
            TrainerDAO tDAO = new TrainerDAO();

            Owned_pokemon newMon = new Owned_pokemon(randlevel,pDAO.getByID(randpokemon), trainer);

            ps.setInt(1, newMon.getTrainer_id_fk());
            ps.setInt(2, newMon.getLevel());
            ps.setInt(3, newMon.getPoke_fk_id());

            ps.executeUpdate();

            return newMon;
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("could not catch pokemon");

        }
        return null;

    }
    public Owned_pokemon releasePokemon(int owned_id) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "DELETE INTO owned_pokemon WHERE owned_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, owned_id);

            ps.executeUpdate();


        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("cant delete");
        }
        return null;
    }


     public ArrayList<Owned_pokemon> getAllOwnedPokemon() {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM owned_pokemon";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            PokeDexDAO pDAO = new PokeDexDAO();
            TrainerDAO tDAO = new TrainerDAO();
            ArrayList<Owned_pokemon> ownedPokeList = new ArrayList<>();
            while(rs.next()) {
                Owned_pokemon curr = new Owned_pokemon(
                        rs.getInt("level"),
                        pDAO.getByID(rs.getInt("poke_fk_id")),
                        tDAO.getTrainerByID(rs.getInt("trainer_id_fk"))
                );
                ownedPokeList.add(curr);

            }
            return ownedPokeList;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("could not get owned pokemon");
        }



        return null;
    }
    public ArrayList<Owned_pokemon> getbyTrainerID(int id) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM owned_pokemon WHERE trainer_id_fk = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            PokeDexDAO pDAO = new PokeDexDAO();
            TrainerDAO tDAO = new TrainerDAO();
            ArrayList<Owned_pokemon> ownedPokeList = new ArrayList<>();
            while(rs.next()) {
                Owned_pokemon curr = new Owned_pokemon(
                        rs.getInt("level"),
                        pDAO.getByID(rs.getInt("poke_fk_id")),
                        tDAO.getTrainerByID(rs.getInt("trainer_id_fk"))
                );
                ownedPokeList.add(curr);

            }
            return ownedPokeList;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("could not get owned pokemon");
        }



        return null;
    }
}
