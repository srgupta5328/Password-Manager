package password311;
 
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
 
public class MainApp {
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Password Management");
        final JButton btnLogin = new JButton("Click to login to your account");
        final JFrame regframe = new JFrame("Register an account"); 
        final JButton btnRegister = new JButton("Register an account");
        final JFrame addCredFrame = new JFrame("Add a Credential"); 
        final JFrame viewCredFrame = new JFrame("View Credentials");
        final JFrame updateCredFrame = new JFrame("Update Credentials");
        final JButton btnAdd = new JButton("Add"); 
        final JButton btnGenerate = new JButton("Generate"); 
        final JButton btnViewCred = new JButton("View Credentials"); 
        final JButton btnSearchCred = new JButton("Search Credentials");
        final JTextField searchField = new JTextField(20); 
        final JButton btnUpdateCred = new JButton ("Update Credentials");
        final  JButton btnLogOut = new JButton("LogOut"); 
         btnAdd.setVisible(false);
         btnViewCred.setVisible(false);
         btnSearchCred.setVisible(false);
         btnUpdateCred.setVisible(false);
         btnSearchCred.setVisible(false);
         searchField.setVisible(false); 
        btnLogOut.setVisible(false);
        
        
        btnLogin.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        LoginDialog loginDlg = new LoginDialog(frame);
                       loginDlg.setVisible(true);
                        if(loginDlg.isSucceeded()){
                            try {
                                btnLogin.setText("Hi " + loginDlg.getUsername() + "!");
                                btnRegister.setVisible(false);
                                btnAdd.setVisible(true);
                                btnViewCred.setVisible(true);
                                btnSearchCred.setVisible(true);
                                btnUpdateCred.setVisible(true);
                                searchField.setVisible(true);
                                btnLogOut.setVisible(true);
                                Credentials.getInstance().decryptAllCredentials();
                            } catch (SQLException ex) {
                                Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                });
        
        btnRegister.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    RegisterDialog registerDlg = new RegisterDialog(regframe);
                    registerDlg.setVisible(true);
                   
                }
            });
        btnAdd.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    AddCredentialDialog addDlg = new AddCredentialDialog(addCredFrame);
                    addDlg.setVisible(true);
                   
                }
          
            });
        btnGenerate.addActionListener(
        new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                AddCredentialDialog generatedlg = new AddCredentialDialog(addCredFrame);
                    generatedlg.setVisible(true);
                
            }
        });
        
        btnViewCred.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                 // CredentialListView credView = null;
                 // credView = new CredentialListView(viewCredFrame);
                    try {
                        Credentials.getInstance().showCredentials();
                    } catch (SQLException ex) {
                       
                    }
                 // credView.setVisible(true);
                   
                }
                
          
            });
        
    btnSearchCred.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                 // CredentialListView credView = null;
                    try {
                        
                    //    credView = new CredentialListView(viewCredFrame);
                        Credentials.getInstance().searchCredentials(searchField.getText().trim());
                    } catch (SQLException ex) {
                        Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   // Credentials.getInstance().searchCredentialLabel(searchTerm);
                   // Credentials.getInstance().searchCredentialPass(searchTerm);
                    //Credentials.getInstance().searchCredentials(searchTerm);
                   // Credentials.getInstance().searchCredentials(searchTerm);
                 // credView.setVisible(true);
                 
                  
                   
                }
                
              //   public String getSearchTerm() {
               //         return searchField.getText().trim(); 
 //}

           // @Override
           // public void actionPerformed(ActionEvent e) {
//                throw new UnsupportedOperationException("Search is being built."); //To change body of generated methods, choose Tools | Templates.
           // }

           // @Override
           // public void actionPerformed(ActionEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            //}
               
          
           // });
            });
    
        
        btnLogOut.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            try {
                Credentials.getInstance().encryptAllCredentials();
                System.exit(0);
            } catch (SQLException ex) {
                Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        });
        
     btnUpdateCred.addActionListener(
            new ActionListener(){
              //  @Override
                public void actionPerformed(ActionEvent e, int ID) throws SQLException {
                  // CredentialListView credView = null;
                    try {
                    //    credView = new CredentialListView(viewCredFrame);
                        Credentials.getInstance().updateCredentialUser(ID, null);
                    } catch (SQLException ex) {
                        Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
 
                            
                //  credView.setVisible(true);
                   
                }
                
                }
                

            @Override
            public void actionPerformed(ActionEvent e) {
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            });
     
     
     btnUpdateCred.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    UpdateCredentialDialog addDlg = new UpdateCredentialDialog(updateCredFrame);
                    addDlg.setVisible(true);
                   
                }
          
            });
     
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().add(btnLogin);
        frame.getContentPane().add(btnRegister);
        frame.getContentPane().add(btnAdd);
        frame.getContentPane().add(btnViewCred);
        frame.getContentPane().add(btnSearchCred); 
        frame.getContentPane().add(btnUpdateCred);
        frame.getContentPane().add(searchField);
        frame.getContentPane().add(btnLogOut);
        
        frame.setVisible(true);
    }
}
