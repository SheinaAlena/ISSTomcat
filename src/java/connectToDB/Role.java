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
public class Role extends ConnectToDB {
    private List<String> role;

    public Role(List<String> role) {
        try {
            role = ConnectToDB.getData("name_role", "role").data;
            System.out.println(role);
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
