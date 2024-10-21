package com.revature.DAO;

import com.revature.models.Trainer;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.*;

public class LoginDAO {
    public Trainer Login(int user_id, String trainer_name){

        //open a connection
        try(Connection conn = ConnectionUtil.getConnection()){

            //Create our SQL String
            String sql = "SELECT * FROM trainer WHERE user_id = ? AND trainer_name = ?";

            //PreparedStatement and fill in the blanks
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, user_id);
            ps.setString(2, trainer_name);

            ResultSet rs = ps.executeQuery();

            //Instantiate a RoleDAO to use the getRoleById method

            if(rs.next()){

                Trainer trainer = new Trainer(
                        rs.getInt("user_id"),
                        rs.getString("trainer_name"),
                        rs.getString("region")
                );

                return trainer;
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Couldn't login user");
        }

        return null;
    }

}

