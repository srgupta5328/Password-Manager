/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password311;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maxwell
 */
public class Credentials {
    private PreparedStatement pst;
    private PreparedStatement pst2;
    private PreparedStatement status;
    private PreparedStatement insertCredential;
    private int updateValue;
    private ResultSet rs;
    private ArrayList<String> results;
    static Credentials aCredential = null;
    private final String DATABASE_URL = "jdbc:derby://localhost:1527/Password311";
    private final String USERNAME = "Rohan";
    private final String PASSWORD = "Hello";
    private static Connection connection;
    private Statement stmt;
    private String decryptedPassword;
    private String encryptedPassword;
    private int numberOfUsers;
    private int usersDone;
    
    //private Credentials(){};
    
    public static Credentials getInstance()
    {
        if (aCredential == null)
        {
            aCredential = new Credentials();
        }
        
        return aCredential;
    }
    
    public void getCredentials()//ArrayList<String> getCredentials()
    {
        try {
            //needs to be updated to select something from credential table
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD); 
            pst = connection.prepareStatement("Select * FROM CREDENTIAL");
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
           
            //print all the credentials
            while (rs.next()) {
//Print one row          
        for(int i = 1 ; i <= columnsNumber; i++){

            System.out.print(rs.getString(i) + " "); //Print one element of a row

                pst.close();
        }   

         System.out.println();//Move to the next line to print the next row.           

         }
          //  results = new ArrayList<>();
           // while (rs.next())
           // {
            //    results.add(rs.getString("CREDENTIAL"));
           // }
            
        } catch (SQLException ex) {
            Logger.getLogger(Credentials.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //return results;
    }
    
    
    public void updateCredentialUser(int ID, String newUser) throws SQLException
    {
      DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());    
        connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);    
        pst = connection.prepareStatement("Update CREDENTIAL SET USERNAME = ? WHERE ID = ?");  
        pst.setString(1, newUser);
        pst.setInt(2, ID);
        pst.executeUpdate();
        pst.close();
        
//        pst = connection.prepareStatement("Select * FROM CREDENTIAL WHERE ID = ?");
//        pst.setInt(1, ID);
//        rs = pst.executeQuery();
//            JTable table = new JTable(buildTableModel(rs));
//            JOptionPane.showMessageDialog(null, new JScrollPane(table));
//            pst.close();
    }
    
    public void updateCredentialPass(int ID, String newPass) throws SQLException
    {
      DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());    
        connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);    
        pst = connection.prepareStatement("Update CREDENTIAL SET PASSWORD = ? WHERE ID = ?");  
        pst.setString(1, newPass);
        pst.setInt(2, ID);
        pst.executeUpdate();
        pst.close();
        
//        pst = connection.prepareStatement("Select * FROM CREDENTIAL WHERE ID = ?");
//        pst.setInt(1, ID);
//        rs = pst.executeQuery();
//            JTable table = new JTable(buildTableModel(rs));
//            JOptionPane.showMessageDialog(null, new JScrollPane(table));
//            pst.close();
    }
    
    /**
     *
     */
    public void encryptAllCredentials() throws SQLException
    {
        usersDone = 0;
        numberOfUsers = getNumberUsers();
        
        while(usersDone < numberOfUsers)
        {
            try {
                DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                pst = connection.prepareStatement("Update CREDENTIAL SET PASSWORD = ? WHERE ID = ?");
                pst2 = connection.prepareStatement("SELECT PASSWORD FROM CREDENTIAL WHERE ID = ?");
                pst2.setInt(1, usersDone+1);
                rs = pst2.executeQuery();
                rs.next();
                decryptedPassword = rs.getString(1);
                //System.out.println("DECRYPTED PASSWORD:");
                //System.out.print(decryptedPassword);
                pst.setString(1, AESCrypt.encrypt(decryptedPassword));
                pst.setInt(2, usersDone+1);
                pst.executeUpdate();
                pst.close();
                pst2.close();
                
                
            } catch (Exception ex) {
                Logger.getLogger(Credentials.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            usersDone++;
        }
    }
    
    /**
     *
     */
    public void decryptAllCredentials() throws SQLException
    {
        usersDone = 0;
        numberOfUsers = getNumberUsers();
       // System.out.print(numberOfUsers);
        while (usersDone < numberOfUsers)
        {
            try {
                DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);   
                pst = connection.prepareStatement("SELECT PASSWORD FROM CREDENTIAL WHERE ID = ?");
                pst2 = connection.prepareStatement("Update CREDENTIAL SET PASSWORD = ? WHERE ID = ?");
                pst.setInt(1, usersDone+1);
                rs = pst.executeQuery();
                rs.next();
                encryptedPassword = rs.getString(1);
                //System.out.println("ENCRYPTED PASSWORD:");
                //System.out.print(encryptedPassword);
                pst2.setString(1, AESCrypt.decrypt(encryptedPassword));
                pst2.setInt(2, usersDone+1);
                pst2.executeUpdate();
                pst.close();
                pst2.close(); 
              
            } catch (Exception ex) {
                Logger.getLogger(Credentials.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            usersDone++;
        }
    }
    
    public int getNumberUsers() {
  
        int rowcount = 0;
        
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD); 
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM CREDENTIAL");
            resultSet.next();
            rowcount = resultSet.getInt(1);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AddCredentialDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        return rowcount; 
    // Get the number of rows from the result set and add 1 for next user id

    }
    
    public void updateCredentialLabel(int ID, String newLabel) throws SQLException
    {
      DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());    
        connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);    
        pst = connection.prepareStatement("Update CREDENTIAL SET LABEL = ? WHERE ID = ?");  
        pst.setString(1, newLabel);
        pst.setInt(2, ID);
        pst.executeUpdate();
        pst.close();
        
//        pst = connection.prepareStatement("Select * FROM CREDENTIAL WHERE ID = ?");
//        pst.setInt(1, ID);
//        rs = pst.executeQuery();
//            JTable table = new JTable(buildTableModel(rs));
//            JOptionPane.showMessageDialog(null, new JScrollPane(table));
//            pst.close();
    }
    
    /**
     *
     * @param searchTerm
     * @throws SQLException
     */
    public void searchCredentialUser(String searchTerm) throws SQLException
    {
     
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
      //  System.out.println("X");
        connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
      //  System.out.println("Y");
        pst = connection.prepareStatement("Select * FROM CREDENTIAL WHERE USERNAME = ?");
        pst.setString(1, searchTerm);
       // System.out.println("Z");
        rs = pst.executeQuery();
   //System.out.println("1");
            JTable table = new JTable(buildTableModel(rs));
            //System.out.println("2");
            JOptionPane.showMessageDialog(null, new JScrollPane(table));
            pst.close();
           // System.out.println("3");
    }
    
        public void searchCredentialPass(String searchTerm) throws SQLException
    {
     
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
      //  System.out.println("X");
        connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
      //  System.out.println("Y");
        pst = connection.prepareStatement("Select * FROM CREDENTIAL WHERE PASSWORD = ?");
        pst.setString(1, searchTerm);
       // System.out.println("Z");
        rs = pst.executeQuery();
   //System.out.println("1");
            JTable table = new JTable(buildTableModel(rs));
            //System.out.println("2");
            JOptionPane.showMessageDialog(null, new JScrollPane(table));
            pst.close();
           // System.out.println("3");
    }
    
    public void searchCredentialLabel(String searchTerm) throws SQLException
    {
     
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
      //  System.out.println("X");
        connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
      //  System.out.println("Y");
        pst = connection.prepareStatement("Select * FROM CREDENTIAL WHERE LABEL = ?");
        pst.setString(1, searchTerm);
       // System.out.println("Z");
        rs = pst.executeQuery();
   //System.out.println("1");
            JTable table = new JTable(buildTableModel(rs));
            //System.out.println("2");
            JOptionPane.showMessageDialog(null, new JScrollPane(table));
            pst.close();
           // System.out.println("3");
    }    
        
    public void searchCredentials(String searchTerm) throws SQLException
    {
     ResultSet rs1;
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
     
        connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
      
        pst = connection.prepareStatement("Select * FROM CREDENTIAL WHERE USERNAME = ? union Select * FROM CREDENTIAL WHERE PASSWORD = ? union Select * FROM CREDENTIAL WHERE LABEL = ?");
        pst.setString(1, searchTerm);
        pst.setString(2, searchTerm);
        pst.setString(3, searchTerm);
  
        rs1 = pst.executeQuery();
        
            JTable table = new JTable(buildTableModel(rs1));
 
            JOptionPane.showMessageDialog(null, new JScrollPane(table));
   pst.close();
    }   
    
    public void showCredentials() throws SQLException
    {
          //  ResultSet rss;
       // rss = stmt.executeQuery("select * from CREDENTIAL");
DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD); 
        pst = connection.prepareStatement("Select * FROM CREDENTIAL");
            rs = pst.executeQuery();
    // It creates and displays the table
    JTable table = new JTable(buildTableModel(rs));

    // Closes the Connection

    JOptionPane.showMessageDialog(null, new JScrollPane(table));
    pst.close();
    }
    
    
    public static DefaultTableModel buildTableModel(ResultSet rs)
        throws SQLException {

    ResultSetMetaData metaData = rs.getMetaData();

    // names of columns
    Vector<String> columnNames = new Vector<String>();
    int columnCount = metaData.getColumnCount();
    for (int column = 1; column <= columnCount; column++) {
        columnNames.add(metaData.getColumnName(column));
    }

    // data of the table
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    while (rs.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            vector.add(rs.getObject(columnIndex));
        }
        data.add(vector);
    }

    return new DefaultTableModel(data, columnNames);

}
//    public ArrayList getStatus(String credentialName)
//    {
//        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
//        ArrayList<ArrayList<Object>> twoDArrayList = new ArrayList<>();
//        String sql = "Select CUSTOMER_NAME, MAGICIAN_NAME from BOOKINGS where HOLIDAY_NAME = ?";
//            
//        try {
//            status = getConnection().prepareStatement(sql);
//            status.setString(1, holidayName);
//            rs = status.executeQuery();
//            
//            while(rs.next())
//            {
//                ArrayList columnList = new ArrayList();
//                twoDArrayList.add(columnList);
//                columnList.add(rs.getString("CUSTOMER_NAME"));
//                columnList.add(rs.getString("MAGICIAN_NAME"));   
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Credentials.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return twoDArrayList;
//    }    
    
    
    public int addCredential(int user_id, String username, String password, String label)
    {
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD); 
            insertCredential = connection.prepareStatement("INSERT INTO CREDENTIAL (ID, USERNAME, PASSWORD, LABEL) values (?, ?, ?, ?)");
            insertCredential.setInt(1, user_id);
            insertCredential.setString(2, username); 
            insertCredential.setString(3, password); 
            insertCredential.setString(4, label); 
            updateValue = insertCredential.executeUpdate();
            
//            insertCredential = getConnection().prepareStatement("INSERT INTO CREDENTIAL (PASSWORD) values (?)");
//            insertCredential.setString(1, password);  
//            updateValue = insertCredential.executeUpdate();
//            
//            insertCredential = getConnection().prepareStatement("INSERT INTO CREDENTIAL (LABEL) values (?)");
//            insertCredential.setString(1, label);  
//            updateValue = insertCredential.executeUpdate();
            insertCredential.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Credentials.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return updateValue;
        
    }   
    
    
    public void deleteCredential(int ID)
    {
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD); 
            PreparedStatement deleteCredential = connection.prepareStatement("DELETE FROM CREDENTIAL WHERE ID = ?");
            deleteCredential.setInt(1, ID);
           // insertCredential.setString(2, username); 
           // insertCredential.setString(3, password); 
           // insertCredential.setString(4, label); 
            deleteCredential.execute();
            
        usersDone = ID+1; //this where we start rolling back the IDs to avoid a crash on add
        numberOfUsers = getNumberUsers();
        
        while(usersDone <= numberOfUsers+1) //1 more than the number of users because we just deleted a user
        {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            pst = connection.prepareStatement("Update CREDENTIAL SET ID = ? WHERE ID = ?");
            pst.setInt(1, usersDone-1);
            pst.setInt(2, usersDone);
            pst.executeUpdate();
            pst.close();
            
            usersDone++;
        }
//            insertCredential = getConnection().prepareStatement("INSERT INTO CREDENTIAL (PASSWORD) values (?)");
//            insertCredential.setString(1, password);  
//            updateValue = insertCredential.executeUpdate();
//            
//            insertCredential = getConnection().prepareStatement("INSERT INTO CREDENTIAL (LABEL) values (?)");
//            insertCredential.setString(1, label);  
//            updateValue = insertCredential.executeUpdate();
            deleteCredential.close();
            connection.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Credentials.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private Connection getConnection()
    {
        return CredentialList.getConnection();
    }    
}
