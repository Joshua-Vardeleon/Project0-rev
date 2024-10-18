package com.revature.DAO;

import com.revature.models.Pokedex;
import com.revature.models.Trainer;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PokeDexDAO {
    public Pokedex getByID(int id){
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM pokedex WHERE poke_no = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                Pokedex currPoke = new Pokedex(
                        rs.getInt("poke_no"),
                        rs.getString("pokemon_name"),
                        rs.getString("pokemon_type1"),
                        rs.getString("pokemon_type2"));
                return currPoke;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("could not get pokemon by id");
        }
        return null;
    }
    public Pokedex getByName(String name){
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM pokedex WHERE pokemon_name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                Pokedex currPoke = new Pokedex(
                        rs.getInt("poke_no"),
                        rs.getString("pokemon_name"),
                        rs.getString("pokemon_type1"),
                        rs.getString("pokemon_type2"));
                return currPoke;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("could not get pokemon by id");
        }
        return null;
    }

    public ArrayList<Pokedex> getByTypeMono(String type) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM pokedex WHERE pokemon_type1 = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            ArrayList<Pokedex> foundPoke = new ArrayList<>();
            while(rs.next()) {
                Pokedex currPoke = new Pokedex(
                        rs.getInt("poke_no"),
                        rs.getString("pokemon_name"),
                        rs.getString("pokemon_type1"));
                if(rs.getString("pokemon_type2") == null) {
                    foundPoke.add(currPoke);
                }

            }
            return foundPoke;

        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("unable to find pokemon");
        }
        return null;
    }
    public ArrayList<Pokedex> getByType(String type) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM pokedex WHERE pokemon_type1 = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            ArrayList<Pokedex> foundPoke = new ArrayList<>();
            while(rs.next()) {
                Pokedex currPoke = new Pokedex(
                        rs.getInt("poke_no"),
                        rs.getString("pokemon_name"),
                        rs.getString("pokemon_type1"));
                foundPoke.add(currPoke);

            }
            return foundPoke;

        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("unable to find pokemon");
        }
        return null;
    }
    public ArrayList<Pokedex> getByType(String type1, String type2) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM pokedex WHERE pokemon_type1 = ? AND pokemon_type2 = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, type1);
            ps.setString(2, type2);
            ResultSet rs = ps.executeQuery();
            ArrayList<Pokedex> foundPoke = new ArrayList<>();
            while(rs.next()) {
                Pokedex currPoke = new Pokedex(
                        rs.getInt("poke_no"),
                        rs.getString("pokemon_name"),
                        rs.getString("pokemon_type1"),
                        rs.getString("pokemon_type2"));
                foundPoke.add(currPoke);

            }
            sql = "SELECT * FROM pokedex WHERE pokemon_type2 = ? AND pokemon_type1 = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, type1);
            ps.setString(2, type2);
            rs = ps.executeQuery();
            while(rs.next()) {
                Pokedex currPoke = new Pokedex(
                        rs.getInt("poke_no"),
                        rs.getString("pokemon_name"),
                        rs.getString("pokemon_type1"),
                        rs.getString("pokemon_type2"));
                foundPoke.add(currPoke);

            }
            return foundPoke;

        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("unable to find pokemon");
        }
        return null;
    }
}
