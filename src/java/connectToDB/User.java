/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectToDB;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Алена
 */
public class User extends ConnectToDB {
    private List<String> user;

    public User(List<String> user) {
        try {
            user = ConnectToDB.getData("*", "users").data;
            System.out.println(user);
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
