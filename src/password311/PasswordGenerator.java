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
public class PasswordGenerator {
    static PasswordGenerator aPasswordGenerator = null;
    
     public static PasswordGenerator getInstance()
    {
        if (aPasswordGenerator == null)
        {
            aPasswordGenerator = new PasswordGenerator();
        }
        
        return aPasswordGenerator;
    }
    
    public static String generate (String result){
     result = makePassword(12);  
    return result;
    }
    public static String makePassword(int length){
        String password = ""; 
        
        for (int i = 0; i < length - 2; i++){
            password = password + randomCharacter("abcdefghijklmnopqrstuvwxyz"); 
            
        }
        
        String randomDigit = randomCharacter("0123456789");
        password = insertAtRandom(password,randomDigit); 
        String randomSymbol = randomCharacter("+-*/?!@#$%");
        password = insertAtRandom(password, randomSymbol); 
        return password; 
    }
    
    public static String randomCharacter(String characters){
        int n= characters.length();
        int r= (int) (n * Math.random());
        return characters.substring(r,r+1); 
        
    }
    
    
    public static String insertAtRandom(String str, String toInsert){
        int n = str.length();
        int r = (int)((n+1) * Math.random()); 
        return str.substring(0,r) + toInsert + str.substring(r); 
    }
}
