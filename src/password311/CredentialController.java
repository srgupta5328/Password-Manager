/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password311;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author rohangupta
 */
public class CredentialController {
    public static ArrayList<Credential> credential= new ArrayList(){
        
       
    }
            
            public void addCredential(Credential user) throws IOException {
        if (!credential.contains(user)) {
            credential.add(user);
            exportCredential();
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
