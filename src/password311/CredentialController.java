/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password311;

import java.sql.SQLException;
import java.util.Scanner;


/**
 *
 * @author rohangupta
 */
public class CredentialController extends Credential {

    public CredentialController(String user, String pass, String aLabel) {
        super(user, pass, aLabel);
    }
 
    public void addCredentialtoDatabase(){
      Credentials.getInstance().addCredential(0, username, password, label); 
      Scanner userInput = new Scanner(System.in);
      username = userInput.nextLine(); 
      password = userInput.nextLine();
      label = userInput.nextLine(); 
      
     
    
    }
    
    public void editCredentialtoDatabase() throws SQLException{
        Credentials.getInstance().updateCredentialLabel(0, label);
        Credentials.getInstance().updateCredentialPass(0, username);
        Credentials.getInstance().updateCredentialUser(0, label);
        
    }
   
    
    
            
       
    
}
