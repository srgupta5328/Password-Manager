package password311;

/* need to make an abstract class, so that we can have the user be able to select 
different types of passwords (types: PIN, String, String Options
{e.g.= yes to characters, yes to numbers, no to symbols}) */

import java.util.Random;

public abstract class PasswordGenerator {
    public String result;
    public String password;   
    public String pLength;
    
    abstract int PINGenerator(){
        
    }
    
    abstract String startPwordGenerator(){
        result = setGenerationAlgorithm(pLength);
        System.out.println(result);
        return result;
    }
    
    public int getDesireLength(){
//user input for the pLength
    }
    
    public String setGenerationAlgorithm(int pLength){
        String password = "";
        
        for (int i=0; i<pLength - 2; i++){
            password = password + randomCharacter("abcdefghijklmnopqrstuvwxyz");
            
        }
        
        String randomDigit = randomCharacter("0123456789");
        password = insertAtRandom(password, randomDigit);
        String randomSymbol = randomCharacter("+-*/?!@#$%&");
        password = insertAtRandom(password, randomSymbol);
        return password;
    }
    
    public String randomCharacter(String characters){
        int n = characters.length();
        int r = (int)(n*Math.random());
        return characters.substring(r,r+1);
        
    }
    
    public String insertAtRandom(String str, String toInsert){
        int n = str.length();
        int r = (int) ((n + 1) * Math.random());
        return str.substring(0, r) + toInsert + str.substring(r);
        
    }
}
