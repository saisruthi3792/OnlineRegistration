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

import Project.Model.TempUser;
import Project.Util.PasswordUtil;

public class tempUserDB {
    
     public static int addUser(TempUser user) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;

	        String query = "INSERT INTO TempUser "
	                + "(UName, Email, Password, IssueDate, Token) "
	                + "VALUES (?, ?, ?, ?, ?)";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, user.getuName());
	            ps.setString(2, user.getEmail());
	            ps.setString(3, user.getPassword());
	            ps.setString(4, user.getIssuedDate());
	            ps.setString(5, user.getTokenID());
	                   
	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
    public static TempUser getUser(String token){
        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
                ResultSet rs = null;

	        String query = "Select * from TempUser where Token = ?";
                try{
                    ps = connection.prepareStatement(query);
                    ps.setString(1, token);
                    rs = ps.executeQuery();
                    TempUser user = new TempUser();
                    while(rs.next()){
                        
                        user.setEmail(rs.getString("Email"));
                        user.setuName(rs.getString("UName"));
                        user.setPassword(rs.getString("Password"));
                        user.setIssuedDate(rs.getString("IssueDate"));
                        user.setTokenID(rs.getString("Token"));
                        
                    }
                    return user;
                }catch (SQLException e) {
	            System.out.println(e);
                    return null;
	            
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	              
           }
    
    public static int delUser(TempUser user){
        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
       

	        String query = "Delete from TempUser where Token = ?";
                try{
                    ps = connection.prepareStatement(query);
                    ps.setString(1, user.getTokenID());
                    ps.executeUpdate();
                    return 1;
                     
                }
                catch (SQLException e) {
	            System.out.println(e);
                    return 0;
	            
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	              
      
        
    }
}
