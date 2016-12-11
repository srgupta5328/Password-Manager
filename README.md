# Password311
IST 311 SDLC Project
The purpose of this project is to design a function password management application for IST 311. We will have assignments that 
require us to properly code the assignment/

Password311 Credential Management Application


Included:
- Database File Folder
- Netbeans Project Folder


The project can be opened in Netbeans.
To use the database, you must have the "derby" database software installed.
Link the database to the Netbeans project and connect to the database in the services window.


The database username/password information is as follows:
Database Name: Password311
Username: Rohan
Password: Hello
Table used: CREDENTIAL


Once the program is setup*, you should execute the "run file" option on "MainApp.java" (right-click)


This will launch the app interface properly, then you will need to press the "login" button.
The information to login is as follows:
Username: Max
Password: Hello


Once you have logged into the app you will be free to navigate the interfaces.
If the database has been properly linked you should now be able to:
- Add, Delete, or Update Credentials
- Generate a Password automatically***
- Search for a Credential


Once Credentials have been added to the database, you will be able to take advantage of AES Encryption**.


* IF you have an error with DB DriverManager not being recognized you will need to add the 'Java DB Driver' library in Netbeans projects window.
**Stored Passwords are automatically encrypted when the app is closed.
**Stored Passwords are automatically decrypted when the app is opened.
**encryption happens behind the scenes but if you add system output to the encryption loop you can watch it happen at the console.
***Password generation is pseudo-random & non-customizable but is functional.
