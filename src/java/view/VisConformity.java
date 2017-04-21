/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Алена
 */
@ManagedBean(name="visConformity")
public class VisConformity  implements Serializable  {
    public int id;
    public String name_old;
    public String name_new;
    public String ID_vis;
    
    public VisConformity() {}
    
    public VisConformity(int id, String name_old, String name_new, String ID_vis) {
        this.id = id;
        this.name_old = name_old;
        this.name_new=name_new;
        this.ID_vis=ID_vis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_old() {
        return name_old;
    }

    public void setName_old(String name_old) {
        this.name_old = name_old;
    }

    public String getName_new() {
        return name_new;
    }

    public void setName_new(String name_new) {
        this.name_new = name_new;
    }

    public String getID_vis() {
        return ID_vis;
    }

    public void setID_vis(String ID_vis) {
        this.ID_vis = ID_vis;
    }
    
}
