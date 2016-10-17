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
public class Database{

  private String userList[] = new String[5];
  private String passList[] = new String[5];
  
  //hardcoded users
  userList[0] = "johndoe1";
  userList[1] = "johndoe2";
  userList[2] = "guy123";
  userList[3] = "guy456";
  userList[4] = "user1";
    
  //hardcoded passwords, where user [0] == pass [0]
  passList[0] = "Newpassword";
  passlist[1] = "Weakpassword";
  passList[2] = "Strongpassword";
  passList[3] = "Mybirthday123";
  passList[4] = "Dogcatcowmouse";
  
  private int verifyUser(String user, String pass)
  {
    //where 0 = verified and 1 = fail
     if (user == "johndoe1")
     {
        if (password ==  "Newpassword")
        {
          return 0;
        }
        else
        {
          return 1;
        }
     }
     if (user == "johndoe2")
     {
        if (password ==  "Weakpassword")
        {
          return 0;
        }
        else
        {
          return 1;
        }
     }
     if (user == "guy123")
     {
        if (password ==  "Strongpassword")
        {
          return 0;
        }
        else
        {
          return 1;
        }
     }
     if (user == "guy456")
     {
        if (password ==  "Mybirthday123")
        {
          return 0;
        }
        else
        {
          return 1;
        }
     }  
     if (user == "user1")
     {
        if (password ==  "Dogcatcowmouse")
        {
          return 0;
        }
        else
        {
          return 1;
        }
     }
    
  }

}
