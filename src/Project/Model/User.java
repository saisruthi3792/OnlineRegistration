package Project.Model;

import java.io.Serializable;

import Project.Util.PasswordUtil;

public class User implements Serializable{
	
	private String name;
	private String email;
	private String userType;
        private String salt;
	private String password;
	private String confirmPassword;
	private int numCoins;
	private int numPostedStudies;
	private int numParticipation;
	
        public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = PasswordUtil.hashPassword(password);;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int getNumCoins() {
		return numCoins;
	}
	public void setNumCoins(int numCoins) {
		this.numCoins = numCoins;
	}
	public int getNumPostedStudies() {
		return numPostedStudies;
	}
	public void setNumPostedStudies(int numPostedStudies) {
		this.numPostedStudies = numPostedStudies;
	}
        
          public String getsalt() {
		return salt;
	}
	public void setsalt(String salt) {
		this.salt = salt;
	}
	public int getNumParticipation() {
		return numParticipation;
	}
	public void setNumParticipation(int numParticipation) {
		this.numParticipation = numParticipation;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String name, String email, String userType,String salt, String password, String confirmPassword, int numCoins,
			int numPostedStudies, int numParticipation) {
		super();
		this.name = name;
		this.email = email;
		this.userType = userType;
                this.salt = salt;
		this.password = PasswordUtil.hashPassword(password + salt);
		this.confirmPassword = confirmPassword;
		this.numCoins = numCoins;
		this.numPostedStudies = numPostedStudies;
		this.numParticipation = numParticipation;
	}
	
	public User(String name, String email, String userType) {
		super();
		this.name = name;
		this.email = email;
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", userType=" + userType + ", numCoins=" + numCoins
				+ ", numPostedStudies=" + numPostedStudies + ", numParticipation=" + numParticipation + "]";
	}
	

}