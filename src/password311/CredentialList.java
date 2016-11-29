/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password311;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Maxwell
 */

public class CredentialList {
    private final String DATABASE_URL = "jdbc:derby://localhost:1527/Password311";
    private final String USERNAME = "Rohan";
    private final String PASSWORD = "Hello";
    
    private static Connection connection;
    static CredentialList aCredentialList = null;
     
    public CredentialList ()
    {
        try
        {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD); 
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
      }
   
    public static CredentialList getInstance()
    {
        if (aCredentialList == null)
        {
            aCredentialList = new CredentialList();
        }
        
        return aCredentialList;
    }     

        public static Connection getConnection()
        {
            return connection;
        }
    
}

//to use this file you must add the Java DB Driver library on netbeans in the project library folder
//you also must ensure you have the database stuff saved at jdbc:derby folder on your local pc which it should be anyway 
