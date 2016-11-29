/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password311;

/**
 *
 * @author rohangupta
 */
public class LoginController {
     
   public static boolean authenticate(String username, String password){
       
        if (username.equals("Rohan") && password.equals("Hello")) {
            return true;
        }   
        
        else if (username.equals("Max") && password.equals("Hello")){
            return true; 
        }
       
        
        return false;
    }
     

    
}
