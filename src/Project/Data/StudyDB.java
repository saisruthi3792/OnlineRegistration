package Project.Data;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Project.Model.Answer;
import Project.Model.Study;


public class StudyDB {

	static ArrayList<Study> studyList = new ArrayList<Study>();
	static List answerList = new ArrayList();
	static ArrayList<Answer> answersList = new ArrayList<Answer>();

	public static ArrayList<Study> getStudies() {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		ArrayList<Study> studies = new ArrayList<Study>();
		String query = "SELECT * from study";
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Study study = new Study();
				List<String> answerList = new ArrayList<String>();
				study.setStudyCode(rs.getString("StudyID"));
				study.setStudyName(rs.getString("StudyName"));
				study.setDescription(rs.getString("Description"));
				study.setEmail(rs.getString("Username"));
				study.setDateCreated(rs.getTimestamp("DateCreated"));
				study.setImageURL(rs.getString("ImageURL"));
				study.setRequestedParticipants(rs.getInt("ReqParticipants"));
				study.setNumOfParticipants(rs.getInt("ActParticipants"));
				study.setStatus(rs.getString("SStatus"));
				String query1 = "SELECT * from question where StudyID=?";
				try {
					ps1 = connection.prepareStatement(query1);
					ps1.setString(1, rs.getString("StudyID"));
					rs1 = ps1.executeQuery();
					while (rs1.next()) {
						study.setQuestion(rs1.getString("Question"));
						study.setAnswerType(rs1.getString("AnswerType"));
						answerList.add(rs1.getString("Option1"));
						answerList.add(rs1.getString("Option2"));
						answerList.add(rs1.getString("Option3"));
						answerList.add(rs1.getString("Option4"));
						answerList.add(rs1.getString("Option5"));
						study.setAnswers(answerList);
					}
				} catch (SQLException e) {
					System.out.println(e);
				}
				studies.add(study);
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs1);
			DBUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}
		return studies;
	}


	public static Study getStudy(String StudyCode) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		List<String> answerList = new ArrayList<String>();
		ResultSet rs1 = null;
		String query = "SELECT * from study WHERE StudyID = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, StudyCode);
			rs = ps.executeQuery();
			while (rs.next()) {
				Study study = new Study();
				study.setStudyCode(rs.getString("StudyID"));
				study.setStudyName(rs.getString("StudyName"));
				study.setDescription(rs.getString("Description"));
				study.setEmail(rs.getString("Username"));
				study.setDateCreated(rs.getTimestamp("DateCreated"));
				study.setImageURL(rs.getString("ImageURL"));
				study.setRequestedParticipants(rs.getInt("ReqParticipants"));
				study.setNumOfParticipants(rs.getInt("ActParticipants"));
				study.setStatus(rs.getString("SStatus"));
				String query1 = "SELECT * from question where StudyID=?";
				try {
					ps1 = connection.prepareStatement(query1);
					ps1.setString(1, rs.getString("StudyID"));
					rs1 = ps1.executeQuery();
					while (rs1.next()) {
						study.setQuestion(rs1.getString("Question"));
						study.setAnswerType(rs1.getString("AnswerType"));
						answerList.add(rs1.getString("Option1"));
						answerList.add(rs1.getString("Option2"));
						answerList.add(rs1.getString("Option3"));
						answerList.add(rs1.getString("Option4"));
						answerList.add(rs1.getString("Option5"));
						study.setAnswers(answerList);
					}
				} catch (SQLException e) {
					System.out.println(e);
				}
				return study;
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs1);
			DBUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}
		return null;
	}


	public static Study getStudy(String StudyCode, String email) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		List<String> answerList = new ArrayList<String>();
		ResultSet rs1 = null;
		String query = "SELECT * from study WHERE StudyID = ? and Username =?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, StudyCode);
			ps.setString(2, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				Study study = new Study();
				study.setStudyCode(rs.getString("StudyID"));
				study.setStudyName(rs.getString("StudyName"));
				study.setDescription(rs.getString("Description"));
				study.setEmail(rs.getString("Username"));
				study.setDateCreated(rs.getTimestamp("DateCreated"));
				study.setImageURL(rs.getString("ImageURL"));
				study.setRequestedParticipants(rs.getInt("ReqParticipants"));
				study.setNumOfParticipants(rs.getInt("ActParticipants"));
				study.setStatus(rs.getString("SStatus"));
				String query1 = "SELECT * from question where StudyID=?";
				try {
					ps1 = connection.prepareStatement(query1);
					ps1.setString(1, rs.getString("StudyID"));
					rs1 = ps1.executeQuery();
					while (rs1.next()) {
						study.setQuestion(rs1.getString("Question"));
						study.setAnswerType(rs1.getString("AnswerType"));
						answerList.add(rs1.getString("Option1"));
						answerList.add(rs1.getString("Option2"));
						answerList.add(rs1.getString("Option3"));
						answerList.add(rs1.getString("Option4"));
						answerList.add(rs1.getString("Option5"));
						study.setAnswers(answerList);
					}
				} catch (SQLException e) {
					System.out.println(e);
				}
				return study;
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs1);
			DBUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}
		return null;
	}


	public static List<Study> getStudies(String email) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		ArrayList<Study> studies = new ArrayList<Study>();
		String query = "SELECT * from study where Username=?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				Study study = new Study();
				List<String> answerList = new ArrayList<String>();
				study.setStudyCode(rs.getString("StudyID"));
				study.setStudyName(rs.getString("StudyName"));
				study.setDescription(rs.getString("Description"));
				study.setEmail(rs.getString("Username"));
				study.setDateCreated(rs.getTimestamp("DateCreated"));
				study.setImageURL(rs.getString("ImageURL"));
				study.setRequestedParticipants(rs.getInt("ReqParticipants"));
				study.setNumOfParticipants(rs.getInt("ActParticipants"));
				study.setStatus(rs.getString("SStatus"));
				String query1 = "SELECT * from question where StudyID=?";
				try {
					ps1 = connection.prepareStatement(query1);
					ps1.setString(1, rs.getString("StudyID"));
					rs1 = ps1.executeQuery();
					while (rs1.next()) {
						study.setQuestion(rs1.getString("Question"));
						study.setAnswerType(rs1.getString("AnswerType"));
						answerList.add(rs1.getString("Option1"));
						answerList.add(rs1.getString("Option2"));
						answerList.add(rs1.getString("Option3"));
						answerList.add(rs1.getString("Option4"));
						answerList.add(rs1.getString("Option5"));
						study.setAnswers(answerList);
					}
				} catch (SQLException e) {
					System.out.println(e);
				}
				studies.add(study);
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs1);
			DBUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}
		return studies;
	}


	public static List<Study> getStudiesByStatus(String status) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		ArrayList<Study> studies = new ArrayList<Study>();
		String query = "SELECT * from study where SStatus=?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, status);
			rs = ps.executeQuery();
			while (rs.next()) {
				Study study = new Study();
				List<String> answerList = new ArrayList<String>();
				study.setStudyCode(rs.getString("StudyID"));
				study.setStudyName(rs.getString("StudyName"));
				study.setDescription(rs.getString("Description"));
				study.setEmail(rs.getString("Username"));
				study.setDateCreated(rs.getTimestamp("DateCreated"));
				study.setImageURL(rs.getString("ImageURL"));
				study.setRequestedParticipants(rs.getInt("ReqParticipants"));
				study.setNumOfParticipants(rs.getInt("ActParticipants"));
				study.setStatus(rs.getString("SStatus"));
				String query1 = "SELECT * from question where StudyID=?";
				try {
					ps1 = connection.prepareStatement(query1);
					ps1.setString(1, rs.getString("StudyID"));
					rs1 = ps1.executeQuery();
					while (rs1.next()) {
						study.setQuestion(rs1.getString("Question"));
						study.setAnswerType(rs1.getString("AnswerType"));
						answerList.add(rs1.getString("Option1"));
						answerList.add(rs1.getString("Option2"));
						answerList.add(rs1.getString("Option3"));
						answerList.add(rs1.getString("Option4"));
						answerList.add(rs1.getString("Option5"));
						study.setAnswers(answerList);
					}
				} catch (SQLException e) {
					System.out.println(e);
				}
				studies.add(study);
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs1);
			DBUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}
		return studies;
	}



	public static void addStudy(Study study) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		String query = "INSERT INTO study "
				+ "(StudyID, StudyName, Description, Username, DateCreated, ImageURL, ReqParticipants, ActParticipants, SStatus) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String query1 = "INSERT INTO question "
				+ "(StudyID, QuestionID, Question, AnswerType, Option1, Option2, Option3, Option4, Option5) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, study.getStudyCode());
			ps.setString(2, study.getStudyName());
			ps.setString(3, study.getDescription());
			ps.setString(4, study.getEmail());
			java.util.Date date = new java.util.Date();
			ps.setTimestamp(5, new Timestamp(date.getTime()));
			ps.setString(6, study.getImageURL());
			ps.setInt(7, study.getRequestedParticipants());
			ps.setInt(8, study.getNumOfParticipants());
			ps.setString(9, study.getStatus());
			ps.executeUpdate();
			try {
				ps1 = connection.prepareStatement(query1);
				ps1.setString(1, study.getStudyCode());
				ps1.setString(2, study.getStudyCode() + "" + study.getStudyName());
				ps1.setString(3, study.getQuestion());
				ps1.setString(4, "MCQ");
				if (study.getAnswers().size() == 3) {
					ps1.setString(5, (String) study.getAnswers().get(0));
					ps1.setString(6, (String) study.getAnswers().get(1));
					ps1.setString(7, (String) study.getAnswers().get(2));
					ps1.setString(8, null);
					ps1.setString(9, null);
				} else if (study.getAnswers().size() == 4) {
					ps1.setString(5, (String) study.getAnswers().get(0));
					ps1.setString(6, (String) study.getAnswers().get(1));
					ps1.setString(7, (String) study.getAnswers().get(2));
					ps1.setString(8, (String) study.getAnswers().get(3));
					ps1.setString(9, null);
				} else if (study.getAnswers().size() == 5) {
					ps1.setString(5, (String) study.getAnswers().get(0));
					ps1.setString(6, (String) study.getAnswers().get(1));
					ps1.setString(7, (String) study.getAnswers().get(2));
					ps1.setString(8, (String) study.getAnswers().get(3));
					ps1.setString(9, (String) study.getAnswers().get(4));
				}
				ps1.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e);
			}
		} catch (SQLException e) {
			System.out.println(e);

		} finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}
	}


	public static void removeStudy(String studyCode) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();

		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		String query = "DELETE FROM study " + "WHERE StudyID = ?";
		String query1 = "DELETE FROM question " + "WHERE StudyID = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, studyCode);
			ps.executeUpdate();
			ps1 = connection.prepareStatement(query1);
			ps1.setString(1, studyCode);
			ps1.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}
	}


	public static void updateStudy(String sCode, Study study) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		String query = "UPDATE study SET " + "StudyID = ?, " + "StudyName = ?, " + "Description = ?, "
				+ "Username = ?, " + "DateCreated = ?, " + "ImageURL = ?, " + "ReqParticipants = ?, "
				+ "ActParticipants = ?, " + "SStatus = ? " + "WHERE StudyID = ?";
		String query1 = "UPDATE question SET " + "StudyID = ?, " + "QuestionID = ?, " + "Question = ?, "
				+ "AnswerType = ?, " + "Option1 = ?, " + "Option2 = ?, " + "Option3 = ?, " + "Option4 = ?, "
				+ "Option5 = ? " + "WHERE StudyID = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, study.getStudyCode());
			ps.setString(2, study.getStudyName());
			ps.setString(3, study.getDescription());
			ps.setString(4, study.getEmail());
			java.util.Date date = new java.util.Date();
			ps.setTimestamp(5, new Timestamp(date.getTime()));
			ps.setString(6, study.getImageURL());
			ps.setInt(7, study.getRequestedParticipants());
			ps.setInt(8, study.getNumOfParticipants());
			ps.setString(9, study.getStatus());
			ps.setString(10, sCode);
			ps.executeUpdate();
			try {
				ps1 = connection.prepareStatement(query1);
				ps1.setString(1, study.getStudyCode());
				ps1.setString(2, study.getStudyCode() + "" + study.getStudyName());
				ps1.setString(3, study.getQuestion());
				ps1.setString(4, "MCQ");
				if (study.getAnswers().size() == 3) {
					ps1.setString(5, (String) study.getAnswers().get(0));
					ps1.setString(6, (String) study.getAnswers().get(1));
					ps1.setString(7, (String) study.getAnswers().get(2));
					ps1.setString(8, null);
					ps1.setString(9, null);
				} else if (study.getAnswers().size() == 4) {
					ps1.setString(5, (String) study.getAnswers().get(0));
					ps1.setString(6, (String) study.getAnswers().get(1));
					ps1.setString(7, (String) study.getAnswers().get(2));
					ps1.setString(8, (String) study.getAnswers().get(3));
					ps1.setString(9, null);
				} else if (study.getAnswers().size() == 5) {
					ps1.setString(5, (String) study.getAnswers().get(0));
					ps1.setString(6, (String) study.getAnswers().get(1));
					ps1.setString(7, (String) study.getAnswers().get(2));
					ps1.setString(8, (String) study.getAnswers().get(3));
					ps1.setString(9, (String) study.getAnswers().get(4));
				}
				ps1.setString(10, sCode);
				ps1.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e);
			}
		} catch (SQLException e) {
			System.out.println(e);

		} finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}
	}

	public static void updateStudyStatus(String sCode, String email, String status) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query = "UPDATE study SET " + "SStatus = ? " + "WHERE StudyID = ? and Username=?";
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

	public static void updateStudyPar(String sCode, int par) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query = "UPDATE study SET " + "ActParticipants = ? " + "WHERE StudyID = ? ";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, par);
			ps.setString(2, sCode);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);

		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}


	public static int getParticipants(String email) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int x = 0;
		String query = "SELECT * from study where Username=?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				x = x + rs.getInt("ActParticipants");
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		return x;

	}


	public static void addAnswers(Answer answer) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		String query = "INSERT INTO answer " + "(StudyID, questionId, Username, Choice, Datesubmitted)"
				+ "VALUES (?, ?, ?, ?, ?)";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, answer.getStudyCode());
			ps.setString(2, answer.getQuesId());
			ps.setString(3, answer.getEmail());
			ps.setString(4, answer.getChoice());
			java.util.Date date = new java.util.Date();
			ps.setTimestamp(5, new Timestamp(date.getTime()));
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);

		} finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}
	}
	
	
	
	
	public static int getStudyCount(String StudyCode) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT count(*) from study WHERE StudyID = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, StudyCode);
			rs = ps.executeQuery();
			if(rs.next())
			{
				 int numberOfRows = rs.getInt(1);
			    System.out.println("numberOfRows= " + numberOfRows);
			    return numberOfRows;
			}
		} catch (SQLException e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		return 0;
	}
	
	
    public static String getSCode(String a)
	{
		if(StudyDB.getStudyCount(a)!=1)
		{
			return a;
		}else
		{
			return getSCode("XY" + (int) (Math.random() * 100));
		}
	}
}
