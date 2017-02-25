/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quartz;
import Project.Data.ConnectionPool;
import Project.Data.DBUtil;
import static java.lang.String.format;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.*;
import java.sql.*;



public class QuartzJob implements Job {

    /**
     *
     * @param context
     * @throws JobExecutionException
     */
    @Override
        public void execute(JobExecutionContext context)
                        throws JobExecutionException {
            System.out.println("QuartZ Job");
                ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
                System.out.println(connection);
	        PreparedStatement ps = null;
                ResultSet rs = null;
                String selQuery = "Select * from TempUserPassword";
                String delQuery = "Delete from TempUserPassword where ExpirationDate = ? ";
                String curDate = null;
                //long timeDiff = 0;
                String expDate = null;
                try{
                    //System.out.println("try1");
                    ps = connection.prepareStatement(selQuery);
	           // ps.setString(1, email);
	            rs= ps.executeQuery();
                
                while(rs.next()){
                    //System.out.println("while");
                   //DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                   //Date dateobj = new Date();
                    //curDate = df.format(dateobj);
                    long retryDate = System.currentTimeMillis();
                    Timestamp original = new Timestamp(retryDate);
                    long diff;
                    java.sql.Timestamp dbSqlDate = rs.getTimestamp("ExpirationDate");
                    //rs.getString("ExpirationDate");
                    //Date date1 = new Date();
                    //Date date2 = new Date();
                    
                   // java.util.Time dbSqlDateConverted = new java.util.Time(dbSqlDate.getTime());
                    
                    //java.util.Date date = new java.util.Date();
                    //java.sql.Timestamp dateCurr = new Timestamp(date.getTime());
                    //System.out.println(date.getTime()); 
                    //System.out.println(date.compareTo(dbSqlDateConverted));
                    System.out.println(dbSqlDate);
                    System.out.println(original);
                    diff = original.getTime() - dbSqlDate.getTime();
                    System.out.println(diff);
                    //try {
                    //    date1 = df.parse(curDate);
                        //long diff = dateobj.getTime() - expDate;
                    //} catch (ParseException ex) {
                      //  Logger.getLogger(QuartzJob.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                    //try {
                      //  date2 = df.parse(expDate);
                   // } catch (ParseException ex) {
                    //    Logger.getLogger(QuartzJob.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                    
                    //timeDiff = date1.getTime() - date2.getTime();
                    
                    if(diff > 5) {
                        try{
                            System.out.println("If");
                    ps = connection.prepareStatement(delQuery);
                    ps.setTimestamp(1, dbSqlDate);
                    ps.executeUpdate();
                        }catch (SQLException e) {
	            System.out.println(e);
                        }
                     
                }
                }
                        
                    }catch (SQLException e) {
	            System.out.println(e);
                        }
                
                
                 
       

	        
                
             finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
                
   
    
}
               
        }
