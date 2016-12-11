/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password311;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author rohangupta
 */
public class UpdateCredentialDialog extends javax.swing.JDialog {

    /**
     * Creates new form UpdateCredentialDialog
     */
    public UpdateCredentialDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    private JTextField tfLabel;
    private JTextField tfUsername; 
    private JPasswordField tfPassword;
    private JTextField tfUserID;
    private JLabel lbLabel; 
    private JLabel lbUsername;
    private JLabel lbPassword; 
    private JLabel lbUserID;
    private JButton btnCancel;
    private JButton btnUpdate; 
    private JButton btnGenerate;
    private JButton btnDelete;
    private boolean succeeded;
    private final String DATABASE_URL = "jdbc:derby://localhost:1527/Password311";
    private final String USERNAME = "Rohan";
    private final String PASSWORD = "Hello";
    private static Connection connection;
    private int successful;
    private int printTable;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public UpdateCredentialDialog(Frame parent){
        super(parent,"Update Credential", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL;
        lbLabel = new JLabel("Label: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbLabel, cs);
        
        tfLabel = new JTextField(20); 
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(tfLabel, cs);
        
        lbUsername = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);
 
        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);
 
        lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);
 
        tfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(tfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
        
        lbUserID = new JLabel("UserID #: ");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        panel.add(lbUserID, cs);
 
        tfUserID = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 1;
        panel.add(tfUserID, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
        
 
       btnGenerate = new JButton("Generate");
        btnGenerate.addActionListener(new ActionListener(){
        
        public void actionPerformed(ActionEvent e){
             if (btnGenerate.getActionListeners().length<1) 
             {
             btnGenerate.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                   //JOptionPane.showInputDialog(Password.makePassword(12)); 
                     
                     if(tfPassword.getText().equals(""))
                     {     
                   tfPassword.setText(PasswordGenerator.getInstance().makePassword(12));
                   JOptionPane.showMessageDialog(new javax.swing.JFrame(),"New Password Generated:"+getPassword());
                     }
                      else
                     {
                         JOptionPane.showMessageDialog(new javax.swing.JFrame(),"Field Must Be Empty To Generate");
                     }
                 //  JOptionPane.showInputDialog(tfPassword, PasswordGenerator.makePassword(12));
                   // user Copies the password provided and inputs it into the dialog box
                   // copy whats inside the dialog box into the main password text field
                   
                    }
                });
             }
             else
             {
                try
                 {
                     //successful = Credentials.getInstance().addCredential(getUserID(), getUsername(), getPassword(), geabel());
                    // System.out.println("Count of listeners: " + ((JButton) e.getSource()).getActionListeners().length);
                     if(tfPassword.getText().equals(""))
                     {
                     tfPassword.setText(PasswordGenerator.getInstance().makePassword(12));
                     JOptionPane.showMessageDialog(new javax.swing.JFrame(),"New Password Generated:"+getPassword());
                     }
                     else
                     {
                         JOptionPane.showMessageDialog(new javax.swing.JFrame(),"Field Must Be Empty To Generate");
                     }
                 }
                  catch(Exception ex)
                    {
                     System.out.println('F'); //failure
                    } 
             }
            }
        }); 
        
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener(){
        
        //if (JBtn.getActionListeners().length<1)) //the if statement that would work here    
            
        public void actionPerformed(ActionEvent e){
        if (!tfUserID.getText().equals(""))
        {
             if (btnUpdate.getActionListeners().length<1) 
             {
                 
                 btnUpdate.addActionListener(
                new ActionListener(){ 
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
              
                            printTable = 0;
                            
                            if(!tfPassword.getText().equals(""))
                            {
                            //Credentials.getInstance().updateCredentialPass(1,getPassword());
                            Credentials.getInstance().updateCredentialPass(getUserIDforUpdate(),getPassword());
                            printTable = 1;
                            }
                            
                            if(!tfUsername.getText().equals(""))
                            {
                          //  Credentials.getInstance().updateCredentialUser(1,getUsername());
                            Credentials.getInstance().updateCredentialUser(getUserIDforUpdate(),getUsername());
                            printTable = 1;
                            }
                            
                            if(!tfLabel.getText().equals(""))
                            {
                          //  Credentials.getInstance().updateCredentialLabel(1,geabel());
                            Credentials.getInstance().updateCredentialLabel(getUserIDforUpdate(),geabel());
                            printTable = 1;
                            }
                            
                            if(printTable == 1)
                            {
                                DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
                                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                                pst = connection.prepareStatement("Select * FROM CREDENTIAL WHERE ID = ?");
                                 pst.setInt(1, getUserIDforUpdate());
                                //pst.setInt(1, 1); //REPLACE THIS WITH THE USER ID FRom input
                                rs = pst.executeQuery();
                                JTable table = new JTable(Credentials.getInstance().buildTableModel(rs));
                                JOptionPane.showMessageDialog(null, new JScrollPane(table));
                                 pst.close();
                            }
                     
                        } catch (SQLException ex) {
                            Logger.getLogger(UpdateCredentialDialog.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
             }
             else
             {
                 try
                 {
                     printTable = 0;
                     
                     if(!tfPassword.getText().equals(""))
                            {
                            //Credentials.getInstance().updateCredentialPass(1,getPassword());
                            Credentials.getInstance().updateCredentialPass(getUserIDforUpdate(),getPassword());
                            printTable = 1;
                            }
                            
                            if(!tfUsername.getText().equals(""))
                            {
                            //Credentials.getInstance().updateCredentialUser(1,getUsername());
                            Credentials.getInstance().updateCredentialUser(getUserIDforUpdate(),getUsername());
                            printTable = 1;
                            }
                            
                            if(!tfLabel.getText().equals(""))
                            {
                          //  Credentials.getInstance().updateCredentialLabel(1,geabel());
                            Credentials.getInstance().updateCredentialLabel(getUserIDforUpdate(),geabel());
                            printTable = 1;
                            }
                            
                            if(printTable == 1)
                            {
                                DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
                                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                                pst = connection.prepareStatement("Select * FROM CREDENTIAL WHERE ID = ?");
                                // pst.setInt(1, ID);
                                pst.setInt(1, getUserIDforUpdate());
                                //pst.setInt(1, 1); //REPLACE THIS WITH THE USER ID FRom input
                                rs = pst.executeQuery();
                                JTable table = new JTable(Credentials.getInstance().buildTableModel(rs));
                                JOptionPane.showMessageDialog(null, new JScrollPane(table));
                                 pst.close();
                            }
   
                 }
                  catch(Exception ex)
                    {
                     System.out.println('F'); //failure
                     JOptionPane.showMessageDialog(new javax.swing.JFrame(),"Failure");
                    }
               // finally
               // {
                   // successful = Credentials.getInstance().addCredential(getUserID(), getUsername(), getPassword(), geabel());
                    //System.out.println("Count of listeners: " + ((JButton) e.getSource()).getActionListeners().length);
                    // JOptionPane.showMessageDialog(new javax.swing.JFrame(),"You've clicked Add new credential button");
                //}
            }    
        }
        else
        {
            JOptionPane.showMessageDialog(new javax.swing.JFrame(),"You must enter the proper ID to update a Credential.");
        }
   }});   
        
        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                if (!tfUserID.getText().equals(""))
                {    
                 // CredentialListView credView = null;
                 // credView = new CredentialListView(viewCredFrame);
                    try {
                        Credentials.getInstance().deleteCredential(getUserIDforUpdate());
                        JOptionPane.showMessageDialog(new javax.swing.JFrame(),"You Deleted Credential: "+getUserIDforUpdate());
                        Credentials.getInstance().showCredentials();
                    } catch (SQLException ex) {
                       
                    }
                 // credView.setVisible(true);
                   
                }
                else
                {
                    JOptionPane.showMessageDialog(new javax.swing.JFrame(),"You must enter the proper ID to update a Credential.");                    
                }
                
            }
                
          
            });
        
         JPanel addCredentialScreen = new JPanel();
        
        addCredentialScreen.add(btnCancel);
        addCredentialScreen.add(btnUpdate);
        addCredentialScreen.add(btnGenerate); 
        addCredentialScreen.add(btnDelete);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(addCredentialScreen, BorderLayout.PAGE_END);
 
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
        
    
        
        public String geabel() {
     return tfLabel.getText().trim(); 
 }
    public String getUsername() {
        return tfUsername.getText().trim();
    }
 
    public String getPassword() {
        return new String(tfPassword.getPassword());
    }
 
    public int getUserIDforUpdate() {
        String newUserID;
        newUserID = tfUserID.getText();
        return Integer.parseInt(newUserID);
    }
    
    public int getUserID() {
  
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
   
        return rowcount+1; 
    // Get the number of rows from the result set and add 1 for next user id

    }
    
    public boolean isSucceeded() {
        return succeeded;
    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UpdateCredentialDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateCredentialDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateCredentialDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateCredentialDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdateCredentialDialog dialog = new UpdateCredentialDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    // End of variables declaration                   
}
