/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.Model;
import java.io.Serializable;


public class TempUser implements Serializable{
    private String uName;
    private String email;
    private String password;
    private String issuedDate;
    private String tokenID;
    private String userType;

    public TempUser() {
    }

    public TempUser(String uName, String email, String password, String issuedDate, String tokenID, String userType) {
        this.uName = uName;
        this.email = email;
        this.password = password;
        this.issuedDate = issuedDate;
        this.tokenID = tokenID;
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getTokenID() {
        return tokenID;
    }

    public void setTokenID(String tokenID) {
        this.tokenID = tokenID;
    }
    


    
    
}
