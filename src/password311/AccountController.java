/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password311;

/**
 *
 * @author srg5328
 */

import java.awt.event.ActionListener;
		

public class AccountController implements java.awt.event.ActionListener {
    /*+getLogin(String)
+getPassword(String)
+LogOutButton */ 
    Account account; 
    
private  AccountController() {	
		System.out.println ("Controller()");
	} 
	
	public void actionPerformed(java.awt.event.ActionEvent e){
		
		System.out.println ("Controller: The " + e.getActionCommand() 
			+ " button is clicked at " + new java.util.Date(e.getWhen())
			+ " with e.paramString " + e.paramString() );
		
		System.out.println("Please Login: ");
		account.LoginIn();
                
	} 

	
	public void getLogOut (Account m){
		System.out.println("Would you lie to log out?");
		account.LogOut(); 
        }    
	

} 
  

