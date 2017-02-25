/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.Model;

import java.io.Serializable;

public class recommend implements Serializable{
    private String sEmail;
    private String fEmail;

    public recommend() {
    }

    public recommend(String sEmail, String fEmail) {
        this.sEmail = sEmail;
        this.fEmail = fEmail;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getfEmail() {
        return fEmail;
    }

    public void setfEmail(String fEmail) {
        this.fEmail = fEmail;
    }
    
    
}
