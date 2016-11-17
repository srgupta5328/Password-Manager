package password311;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.Scanner;
/**
 *
 * @author rohangupta
 */
public class Register {
      Scanner userInput = new Scanner (System.in); 
      
    public Register(String user, String pass, String aLabel) {
        
        user = userInput.nextLine(); 
        pass = userInput.nextLine();
        aLabel = userInput.nextLine(); 
        
        
    }
    
    
      
}
