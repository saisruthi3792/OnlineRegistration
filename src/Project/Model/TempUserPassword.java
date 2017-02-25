/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.Model;
import java.io.Serializable;
import java.sql.Timestamp;


public class TempUserPassword implements Serializable{
    private String email;
    private String tokenfp;
    private String expirationDate;
    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    

    public TempUserPassword() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTokenfp() {
        return tokenfp;
    }

    public void setTokenfp(String tokenfp) {
        this.tokenfp = tokenfp;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public TempUserPassword(String email, String tokenfp, String expirationDate, String userType) {
        this.email = email;
        this.tokenfp = tokenfp;
        this.expirationDate = expirationDate;
        this.userType = userType;
    }
    
    
    
}
