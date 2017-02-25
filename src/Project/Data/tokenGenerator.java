/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.Data;
import java.util.UUID;

public class tokenGenerator {
    
     public String randomToken() {
        // Creating a random UUID (Universally unique identifier).
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
       
        return randomUUIDString;
    }
    
    
}
