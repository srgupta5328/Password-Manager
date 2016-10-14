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
public class Credential {
    String username;
    String password;
    String label;
    
    public Credential(String user, String pass, String aLabel){
        username = user;
        password = pass;
        label = aLabel;
    }
    
    void setCredentials(String user, String pass, String aLabel){
        this.username = user;
        this.password = pass;
        this.label = aLabel;
    }
    
    void setUsername(String user){
        this.username = user;
    }
    
    void setPassword(String pass){
        this.password = pass;
    }
    
    void setLabel(String aLabel){
        this.label = aLabel;
    }
    
    public String getCredentials(){  
        String fullCredential;
        fullCredential = username + password + label;
        return fullCredential;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public String getLabel(){
        return this.label;
    }

}
