package com.revature.DAO;
import com.revature.models.Owned_pokemon;
import com.revature.models.Pokedex;
import com.revature.models.Trainer;
import com.revature.utils.ConnectionUtil;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.Stack;

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


            sql = "SELECT * FROM owned_pokemon";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);
            Owned_pokemon last;
            while(rs.next()) {
                Owned_pokemon curr = new Owned_pokemon(
                        rs.getInt("level"),
                        pDAO.getByID(rs.getInt("poke_fk_id")),
                        tDAO.getTrainerByID(rs.getInt("trainer_id_fk"))

                );
                curr.setId(rs.getInt("owned_id"));
                last = curr;

            }



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

            sql = "SELECT * FROM owned_pokemon";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);
            Owned_pokemon last;
            while(rs.next()) {
                Owned_pokemon curr = new Owned_pokemon(
                        rs.getInt("level"),
                        pDAO.getByID(rs.getInt("poke_fk_id")),
                        tDAO.getTrainerByID(rs.getInt("trainer_id_fk"))

                );
                curr.setId(rs.getInt("owned_id"));
                last = curr;

            }


            return newMon;
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("could not catch pokemon");

        }
        return null;

    }
    public Owned_pokemon catchNamePokemon(String name) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO owned_pokemon (trainer_id_fk, level, poke_fk_id) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            int randlevel = (int)(Math.random() * 100) + 1;
            int randpokemon = (int)(Math.random() * 809) + 1;

            PokeDexDAO pDAO = new PokeDexDAO();
            TrainerDAO tDAO = new TrainerDAO();


            Owned_pokemon newMon = new Owned_pokemon(randlevel,pDAO.getByID(randpokemon), tDAO.getTrainerByName(name));

            ps.setInt(1, newMon.getTrainer_id_fk());
            ps.setInt(2, newMon.getLevel());
            ps.setInt(3, newMon.getPoke_fk_id());
            ps.executeUpdate();

            sql = "SELECT * FROM owned_pokemon";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);
            Owned_pokemon last = null;

            while(rs.next()) {
                Owned_pokemon curr = new Owned_pokemon(
                        rs.getInt("level"),
                        pDAO.getByID(rs.getInt("poke_fk_id")),
                        tDAO.getTrainerByID(rs.getInt("trainer_id_fk"))

                );
                curr.setId(rs.getInt("owned_id"));
                last = curr;

            }


            return last;
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("could not catch pokemon");

        }
        return null;

    }
    public Owned_pokemon releasePokemonbyID(int owned_id) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "DELETE FROM owned_pokemon WHERE owned_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, owned_id);
            Owned_PokemonDAO pDAO = new Owned_PokemonDAO();
            Owned_pokemon deleted = pDAO.getbyOwnedID(owned_id);
            ps.executeUpdate();
            return deleted;

        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("cant delete");
        }
        return null;
    }
//    public Owned_pokemon releasePokemonByUser(String trainer_name) {
//        try (Connection conn = ConnectionUtil.getConnection()) {
//            String sql = "DELETE INTO owned_pokemon WHERE owned_id = ?";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            TrainerDAO tDao = new TrainerDAO();
//            int owned_id = tDao.getTrainerByName(trainer_name).getUser_id();
//            ps.setInt(1, owned_id);
//
//            ps.executeUpdate();
//
//
//        } catch(SQLException e) {
//            e.printStackTrace();
//            System.out.println("cant delete");
//        }
//        return null;
//    }


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
                curr.setId(rs.getInt("owned_id"));
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
                curr.setId(rs.getInt("owned_id"));
                ownedPokeList.add(curr);

            }
            return ownedPokeList;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("could not get owned pokemon");
        }



        return null;
    }
    public Owned_pokemon getbyOwnedID(int id) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM owned_pokemon WHERE owned_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            PokeDexDAO pDAO = new PokeDexDAO();
            TrainerDAO tDAO = new TrainerDAO();
            Owned_pokemon owned = new Owned_pokemon();
            if(rs.next()) {
                Owned_pokemon curr = new Owned_pokemon(
                        rs.getInt("level"),
                        pDAO.getByID(rs.getInt("poke_fk_id")),
                        tDAO.getTrainerByID(rs.getInt("trainer_id_fk"))

                );
                curr.setId(rs.getInt("owned_id"));
                owned = curr;
                return owned;

            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("could not get owned pokemon");
        }



        return null;
    }
    public String battle(int id1, int id2) {
        int[][] WEAKNESS_CHART = {
                {2,2,2,2,2,1,2,0,1,2,2,2,2,2,2,2,2,2},
                {4,2,1,1,2,4,1,0,4,2,2,2,2,1,4,2,4,1},
                {2,4,2,2,2,1,4,2,1,2,2,4,1,2,2,2,2,2},
                {2,2,2,1,1,1,2,1,0,2,2,4,2,2,2,2,2,4},
                {2,2,0,4,2,4,1,2,4,4,2,1,4,2,2,2,2,2},
                {2,1,4,2,1,2,4,2,1,4,2,2,2,2,4,2,2,2},
                {2,1,1,1,2,2,2,1,1,1,2,4,2,4,2,2,4,1},
                {0,2,2,2,2,2,2,4,2,2,2,2,2,4,2,2,1,2},
                {2,2,2,2,2,4,2,2,1,1,1,2,1,2,4,2,2,4},
                {2,2,2,2,2,1,4,2,4,1,1,4,2,2,4,1,2,2},
                {2,2,2,2,4,4,2,2,2,4,1,1,2,2,2,1,2,2},
                {2,2,1,1,4,4,1,2,1,1,4,1,2,2,2,1,2,2},
                {2,2,4,2,0,2,2,2,2,2,4,1,1,2,2,1,2,2},
                {2,4,2,4,2,2,2,2,1,2,2,2,2,1,2,2,0,2},
                {2,2,4,2,4,2,2,2,1,1,1,4,2,2,1,4,2,2},
                {2,2,2,2,2,2,2,2,1,2,2,2,2,2,2,4,2,0},
                {2,1,2,2,2,2,2,4,2,2,2,2,2,4,2,2,1,1},
                {2,4,2,1,2,2,2,2,1,1,2,2,2,2,2,4,4,2}
            };
        String[] types = {"Normal","Fighting","Flying","Poison","Ground","Rock","Bug","Ghost","Steel","Fire","Water",
                "Grass","Electric","Psychic","Ice","Dragon","Dark","Fairy"};
        try (Connection conn = ConnectionUtil.getConnection()) {
            String battle = "";
            Owned_PokemonDAO pDAO = new Owned_PokemonDAO();
            Stack<Owned_pokemon> team1 = new Stack<>();
            Stack<Owned_pokemon> team2 = new Stack<>();

            for(Owned_pokemon curr : pDAO.getbyTrainerID(id1)) {
                team1.push(curr);
            }
            for(Owned_pokemon curr : pDAO.getbyTrainerID(id2)) {
                team2.push(curr);

            }

            while(!(team2.isEmpty() || team1.isEmpty())){
                Pokedex poke1  = team1.peek().getPokedex();
                Pokedex poke2 = team2.peek().getPokedex();
                int team1TypeA= 2;
                int team1TypeB= 2;
                int team2TypeA= 2;
                int team2TypeB= 2;

                int battleScore1;
                int battleScore2;
                for(int x = 0; x < types.length; x++)
                {
                    if(poke1.getPoke_type1() != null && poke1.getPoke_type2() != null) {
                        if(poke1.getPoke_type1().equals(types[x])) {
                            team1TypeA = x;
                        }
                        if(poke1.getPoke_type2().equals(types[x])) {
                            team1TypeB = x;
                        }

                    }
                    if(poke2.getPoke_type1() != null && poke2.getPoke_type2() != null) {
                        if(poke2.getPoke_type1().equals(types[x])) {
                            team2TypeA = x;
                        }
                        if(poke2.getPoke_type2().equals(types[x])) {
                            team2TypeA = x;
                        }
                    }


                }
                battleScore1 = team1.peek().getLevel() * (WEAKNESS_CHART[team1TypeA][team2TypeA]*
                        WEAKNESS_CHART[team1TypeA][team2TypeB] * WEAKNESS_CHART[team1TypeB][team2TypeA] *
                        WEAKNESS_CHART[team1TypeB][team2TypeB]);
                System.out.println(battleScore1);
                battleScore2 = team2.peek().getLevel() * (WEAKNESS_CHART[team2TypeA][team1TypeA]*
                        WEAKNESS_CHART[team2TypeA][team1TypeB] * WEAKNESS_CHART[team2TypeB][team1TypeA] *
                        WEAKNESS_CHART[team2TypeB][team1TypeB]);
                System.out.println(battleScore2);
                if(battleScore1 == battleScore2) {
                    battle += team1.peek().getName() + " defeated " + team2.peek().getName() + "\n";
                    team2.pop();
                    if(team2.isEmpty()) {
                        battle += team1.peek().getTrainer_name() + " has won";
                    }
                } else if (battleScore1 > battleScore2) {
                    battle += team1.peek().getName() + " defeated " + team2.peek().getName() + "\n";
                    team2.pop();
                    if(team2.isEmpty()) {
                        battle += team1.peek().getTrainer_name() + " has won";
                        System.out.println(battle);
                    }
                } else {
                    battle += team2.peek().getName() + " defeated " + team1.peek().getName() + "\n";
                    team1.pop();
                    if(team1.isEmpty()) {
                        battle += team2.peek().getTrainer_name() + " has won";
                        System.out.println(battle);
                    }

                }


            }
            return battle;





        } catch (SQLException e) {

        }
        return null;
    }
}
