package Project.Data;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Project.Model.Report;


public class ReportDB {
	
	
	static ArrayList<Report> reportList = new ArrayList<Report>();
	
	 public static  ArrayList<Report> getReports() {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        ArrayList<Report> reports = new ArrayList<Report>();
	        String query = "SELECT * from reported";
	        try {
	            ps = connection.prepareStatement(query);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	Report report = new Report();
	            	report.setQues(rs.getString("Question"));
	            	report.setReporterEmail(rs.getString("Username"));
	            	report.setRepStatus(rs.getString("Status"));
	            	report.setStudyCode(rs.getString("StudyID"));
	            	report.setRepDate(rs.getTimestamp("Date"));
	                reports.add(report);
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	        return reports;
	    }

	 public static  ArrayList<Report> getReports(String email) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        ArrayList<Report> reports = new ArrayList<Report>();
	        String query = "SELECT * from reported where Username=?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1,email);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	Report report = new Report();
	            	report.setQues(rs.getString("Question"));
	            	report.setReporterEmail(rs.getString("Username"));
	            	report.setRepStatus(rs.getString("Status"));
	            	report.setStudyCode(rs.getString("StudyID"));
	            	report.setRepDate(rs.getTimestamp("Date"));
	                reports.add(report);
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	        return reports;
	    }
	
	 public static  Report getReport(String studyCode) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        String query = "SELECT * from reported where StudyID=?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1,studyCode);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	Report report = new Report();
	            	report.setQues(rs.getString("Question"));
	            	report.setReporterEmail(rs.getString("Username"));
	            	report.setRepStatus(rs.getString("Status"));
	            	report.setStudyCode(rs.getString("StudyID"));
	            	report.setRepDate(rs.getTimestamp("Date"));
	            	 return report;
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
	
	 public static void addReport(Report rep) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;

	        String query = "INSERT INTO reported "
	                + "(QuestionID, StudyID, Date, Question, Username, NumParticipants, Status) "
	                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, rep.getQuestionID());
	            ps.setString(2, rep.getStudyCode());
	            ps.setString(4, rep.getQues());
	            ps.setTimestamp(3, rep.getRepDate());
	            ps.setString(5, rep.getReporterEmail());
	            ps.setInt(6, 0);
	            ps.setString(7, rep.getRepStatus());
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
	
	 public static Report getReport(String studyCode, String email) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        String query = "SELECT * from reported where StudyID=? and Username=?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1,studyCode);
	            ps.setString(2,email);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	Report report = new Report();
	            	report.setQues(rs.getString("Question"));
	            	report.setReporterEmail(rs.getString("Username"));
	            	report.setRepStatus(rs.getString("Status"));
	            	report.setStudyCode(rs.getString("StudyID"));
	            	report.setRepDate(rs.getTimestamp("Date"));
	            	 return report;
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
	 
	 
	 
	 public static void updateReportStatus(String sCode, String email,String status) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        String query = "UPDATE reported SET "
	                + "Status = ? "
	                + "WHERE StudyID = ? and Username=?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, status);
	            ps.setString(2, sCode);
	            ps.setString(3, email);
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	          
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
}
