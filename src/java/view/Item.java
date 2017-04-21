/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Faritko
 */
public class Item {
        
    public Integer id;
    public Integer id_role;
    public String column;

    public Item() {}
    
    public Item(Integer id, Integer id_role, String column) {
        this.id = id;
        this.id_role = id_role;
        this.column = column;
    }
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_role() {
        return id_role;
    }
    public void setId_role(Integer id_role) {
        this.id_role = id_role;
    }
    
    public String getColumn() {
        return column;
    }
    public void setColumn(String column) {
        this.column = column;
    }
   
}
