package Project.Model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Answer implements Serializable {
	private String studyCode;
	private String quesId;
	private String email;
	private String choice;
	private Timestamp submissionDate;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getQuesId() {
		return quesId;
	}

	public void setQuesId(String quesId) {
		this.quesId = quesId;
	}

	public String getStudyCode() {
		return studyCode;
	}

	public void setStudyCode(String studyCode) {
		this.studyCode = studyCode;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Timestamp submissionDate) {
		this.submissionDate = submissionDate;
	}

	public Answer(String studyCode, String email, Timestamp submissionDate, String choice, String quesId) {
		super();
		this.studyCode = studyCode;
		this.email = email;
		this.choice = choice;
		this.submissionDate = submissionDate;
		this.quesId=quesId;
	}

	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}

}
