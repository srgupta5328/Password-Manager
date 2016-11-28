package password311;
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class MainApp {
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Password Management Login");
        final JButton btnLogin = new JButton("Click to login to your account");
        final JFrame regframe = new JFrame("Register an account"); 
        final JButton btnRegister = new JButton("Register an account");
        final JFrame addCredFrame = new JFrame("Add a Credential"); 
        final JButton btnAdd = new JButton("Add"); 
        
        btnLogin.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        LoginDialog loginDlg = new LoginDialog(frame);
                        loginDlg.setVisible(true);
                        if(loginDlg.isSucceeded()){
                            btnLogin.setText("Hi " + loginDlg.getUsername() + "!");
                            btnRegister.setVisible(false);
                        }
                    }
                });
        btnRegister.addActionListener(
            new ActionListener(){
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
    
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().add(btnLogin);
        frame.getContentPane().add(btnRegister);
        frame.getContentPane().add(btnAdd);
        frame.setVisible(true);
    }
}
