/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password311;

import java.util.Scanner;

/**
 *
 * @author srg5328
 */
public class Account {
    
     Scanner userInput = new Scanner(System.in); 
    
    
    private String credential; 
    private final String Account;
    
    public Account(String credential){
        this.Account = credential; 
    }
 
  
    
   public void LoginIn(){
       System.out.println("Please enter your username");
        String username = userInput.nextLine(); 
        
        System.out.println("Please enter your password");
        String password = userInput.nextLine(); 
        
        
        if (username.equals(username) && password.equals(password)){
            System.out.println("Login successful!");
            
            
        }
        
        else {
            System.out.println("Please try again");
        }
          
   }
   public void LogOut(){
       System.out.println("Do you want to log out?");
       Scanner userlogOut = new Scanner (System.in); 
      
       
      
       
   }
    
    
    
   
    
    
    
}
