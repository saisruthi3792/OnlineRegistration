/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Project.Model.recommend;


public class RecommendDB {
     public static int addUser(recommend user) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;

	        String query = "INSERT INTO recommend "
	                + "(sEmail, fEmail) "
	                + "VALUES (?, ?)";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, user.getsEmail());
	            ps.setString(2, user.getfEmail());
	                               
	            return ps.executeUpdate();
                    
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
    public static recommend getUser(String email) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        String query = "select * FROM recommend WHERE fEmail = ?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, email);
	            rs= ps.executeQuery();
                    recommend user = new recommend();
	            while (rs.next())
	            {
	             
                     user.setsEmail(rs.getString("sEmail"));
                     user.setfEmail(rs.getString("fEmail"));
	            }
                    //System.out.println("Re"+user.getsEmail());
                    return user;

                    
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
                 }
		
	    }
    
}
