/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testingwithgit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author wongj
 */
class SignUpLoginManagerService {
    protected int SignUp = 1;
    protected int Login = 0;
    
    public boolean userLogin(String username, String password)
    {
        if (!username.isEmpty() && !password.isEmpty()) {
            String[] findUser = findUser(username);
            if (findUser != null) {
                if (findUser[2].equals(password)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean userSignUp (String username, String password, String DOB, String gender, String mobileNo)
    {
        String[] userNotExist = this.findUser(username);
        if (userNotExist == null) {
            return this.storeUserAccount(username, password, DOB, gender, mobileNo);
        }
        
        return false;
    }
    
    public String[] findUser(String username) {
        try {
            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String item;
            String[] status = null;
            boolean firstLine = true;
            
            while ((item = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                }
                else {
                    String[] user = item.split(":");
                    
                    if (username.equals(user[1])) {
                        return user;
                    }
                }
            }
            br.close();
            fr.close();
            
            return status;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean storeUserAccount(String username, String password, String DOB, String gender, String mobileNo) {
        try {
            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            Scanner sc = new Scanner(br);
            boolean firstLine = true;
            String users;
            String newID = "1";
            
            while ((users = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                }
                else if (!sc.hasNext()) {
                    String[] user = users.split(":");
                    String lastID = user[0];
                    newID = String.valueOf(Integer.valueOf(lastID) + 1);
                }
            } 
            br.close();
            fr.close();
            
            FileWriter fw = new FileWriter("users.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            
            bw.write(newID + ":" + username + ":" + password + ":" + DOB + ":" + gender + ":" + mobileNo + ":" + dtf.format(now) + ":" + dtf.format(now) + ":");
            
            bw.close();
            fw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return true;
    }
}
