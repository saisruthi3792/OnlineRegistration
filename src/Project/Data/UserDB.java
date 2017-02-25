package Project.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Project.Model.User;
import Project.Util.PasswordUtil;

public class UserDB {

	
	 public static boolean selectUser(String email, String pwd) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        String query = "select * FROM user WHERE Username = ? and Password = ?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, email);
	            ps.setString(2, PasswordUtil.hashPassword(pwd));
	            rs= ps.executeQuery();
	            while (rs.next())
	            {
	             return true;
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return false;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
			return false;
	    }
	
	  public static User getUser(String email) {
	         ConnectionPool pool = ConnectionPool.getInstance();
                Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        String query = "SELECT * from user WHERE Username = ?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, email);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	User user = new User();
	            	user.setName(rs.getString("name"));
	            	user.setEmail(rs.getString("Username"));
	            	user.setUserType(rs.getString("Type"));
	            	user.setNumPostedStudies(rs.getInt("Studies"));
	            	user.setNumParticipation(rs.getInt("Participation"));
	            	user.setNumCoins(rs.getInt("Coins"));
	               return user;
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	        return null;
	    }
	
	  public static List<User> getUsers() {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	       ArrayList<User> users = new ArrayList<User>();
	        String query = "SELECT * from user";
	        try {
	            ps = connection.prepareStatement(query);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	User user = new User();
	               	user.setName(rs.getString("name"));
	            	user.setEmail(rs.getString("Username"));
	            	user.setUserType(rs.getString("Type"));
	            	user.setNumPostedStudies(rs.getInt("Studies"));
	            	user.setNumParticipation(rs.getInt("Participation"));
	            	user.setNumCoins(rs.getInt("Coins"));
	                users.add(user);
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	        return users;
	    }

    public static int addUser(User user) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;

	        String query = "INSERT INTO user "
	                + "(Username, Password, Type, Studies, Participation, Coins, name, Salt) "
	                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, user.getEmail());
	            ps.setString(2, user.getPassword());
	            ps.setString(3, user.getUserType());
	            ps.setInt(4, user.getNumPostedStudies());
	            ps.setInt(5, user.getNumParticipation());
	            ps.setInt(6, user.getNumCoins());
	            ps.setString(7, user.getName());
                    ps.setString(8, user.getsalt());
                    
	            return ps.executeUpdate();
                    
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
	 
	 public static void updateUserParticipation(String email, int parNum) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        String query = "UPDATE user SET "
	                + "Participation = ? "
	                + "WHERE Username = ? ";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setInt(1, parNum);
	            ps.setString(2, email);
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	          
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }

	 public static void updateUserCoins(String email, int coins) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
                ResultSet rs = null;
                int current_coins = 0;
                String query1 = "Select Coins from user" + " WHERE Username = ? ";
                try {
	            ps = connection.prepareStatement(query1);
	            ps.setString(1, email);
	            rs = ps.executeQuery();
                    while(rs.next()){
                    current_coins = rs.getInt("Coins");
                    }
                    //System.out.println("update"+email);
                } catch (SQLException e) {
	            System.out.println(e);
                }
	        String query = "UPDATE user SET "
	                + "Coins = ? "
	                + "WHERE Username = ? ";
                
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setInt(1, current_coins + coins);
	            ps.setString(2, email);
	            ps.executeUpdate();
                    System.out.println("update"+email);
	        } catch (SQLException e) {
	            System.out.println(e);
	          
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
         public static void updatePassword(String email, String password){
             ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        String query = "UPDATE user SET "
	                + "Password = ? "
	                + "WHERE Username = ? ";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, PasswordUtil.hashPassword(password));
	            ps.setString(2, email);
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	          
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
                
             
         }
         
         public static String getsalt(String email){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null; 
        String query = "SELECT Salt from User where Username = ?";
         try {
      
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
           if(rs.next())
           {
            return rs.getString("Salt");
           }
           else return null;
         }catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    
    }
}
