/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password311;

import com.sun.xml.internal.ws.util.StringUtils;
import static java.awt.SystemColor.text;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rohangupta
 */
public class CredentialController {
    public static ArrayList<Credential> credential= new ArrayList();
        
      public static void readCredentials() {
       try (BufferedReader br = new BufferedReader(new FileReader("C:\\UserAccount.txt.txt")))
		{ String currentLine;
                    while ((currentLine = br.readLine()) != null) {
                        System.out.println(currentLine);}
                    } 
                 catch (IOException e) {
                    e.printStackTrace();
                    }
       
         } 
      
      public static void writeCredentials(){
          
      }
      
    
            
            public void addCredential(Credential user) {
        if (!credential.contains(user)) {
            credential.add(user);
            
        }
    }
    
    public void removeCredential(Credential user) {
        if (credential.contains(user)) {
            credential.remove(user);
        }
    }
    
    public static ArrayList<Credential> getCredentialList() {
        return credential;
    }
    
    }
}
