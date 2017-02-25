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

import Project.Model.TempUserPassword;
import Project.Util.PasswordUtil;
import java.sql.Timestamp;
import java.util.Date;

public class TempUserPasswordDB {
    public static int addUser(TempUserPassword user) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
                //java.util.Date date = new Date();
                //Object param = new java.sql.Timestamp(date.getTime());    
                //preparedStatement.setObject(param);    
                //System.out.println("out");

	        String query = "INSERT INTO TempUserPassword "
	                + "(Email, ExpirationDate, Token) "
	                + "VALUES (?, ?, ?)";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, user.getEmail());
       	            ps.setString(2, user.getExpirationDate());
	            ps.setString(3, user.getTokenfp());
                   // System.out.println("try");
	                   
	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
    public static TempUserPassword getUser(String token){
        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
                ResultSet rs = null;

	        String query = "Select * from TempUserPassword where Token = ?";
                try{
                    ps = connection.prepareStatement(query);
                    ps.setString(1, token);
                    rs = ps.executeQuery();
                    TempUserPassword user = new TempUserPassword();
                    while(rs.next()){
                        
                        user.setEmail(rs.getString("Email"));
                        user.setExpirationDate(rs.getString("ExpirationDate"));
                        user.setTokenfp(rs.getString("Token"));
                        user.setUserType("fp");
                        
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
    public static Timestamp getDate(String token){
        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
                ResultSet rs = null;

	        String query = "Select ExpirationDate from TempUserPassword where Token = ?";
                try{
                    ps = connection.prepareStatement(query);
                    ps.setString(1, token);
                    rs = ps.executeQuery();
                    TempUserPassword user = new TempUserPassword();
                    //long retryDate = System.currentTimeMillis();
                    //java.sql.Timestamp dbSqlDate = new Timestamp(retryDate);
                    while(rs.next()){
                        
                       // user.setEmail(rs.getString("Email"));
                        user.setExpirationDate(rs.getString("ExpirationDate"));
                        //user.setTokenfp(rs.getString("Token"));
                        //user.setUserType("fp");
                        java.sql.Timestamp dbSqlDate = rs.getTimestamp("ExpirationDate");
                        return dbSqlDate;
                    }
                    
                }catch (SQLException e) {
	            System.out.println(e);
                    return null;
	            
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	             return null;
           }
    public static int delUser(String token){
        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
       

	        String query = "Delete from TempUserPassword where Token = ?";
                try{
                    ps = connection.prepareStatement(query);
                    ps.setString(1, token);
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
