package Project.Model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Report implements Serializable{
	private String questionID;
	private String studyCode;
	private String ques;
	private String reporterEmail;
	private Timestamp repDate;
	private String repStatus;
	
	
	public String getQuestionID() {
		return questionID;
	}
	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}
	public String getStudyCode() {
		return studyCode;
	}
	public void setStudyCode(String studyCode) {
		this.studyCode = studyCode;
	}
	public String getQues() {
		return ques;
	}
	public void setQues(String ques) {
		this.ques = ques;
	}
	public String getReporterEmail() {
		return reporterEmail;
	}
	public void setReporterEmail(String reporterEmail) {
		this.reporterEmail = reporterEmail;
	}
	public Timestamp getRepDate() {
		return repDate;
	}
	public void setRepDate(Timestamp repDate) {
		this.repDate = repDate;
	}
	public String getRepStatus() {
		return repStatus;
	}
	public void setRepStatus(String repStatus) {
		this.repStatus = repStatus;
	}
	public Report(String studyCode, String ques, String reporterEmail, Timestamp repDate, String repStatus, String quesID) {
		super();
		this.studyCode = studyCode;
		this.ques = ques;
		this.reporterEmail = reporterEmail;
		this.repDate = repDate;
		this.repStatus = repStatus;
		this.questionID=quesID;
	}
	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Report [studyCode=" + studyCode + ", ques=" + ques + ", reporterEmail=" + reporterEmail + ", repDate="
				+ repDate + ", repStatus=" + repStatus + "]";
	}
	

}
