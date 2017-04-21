package view;

import connectToDB.CustomizeRole;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.SelectEvent;

@ManagedBean(name="vis")
public class Vis implements Serializable {
    
    public int id;
    public String name;
    public String type;
    public String comment;
    public String transfer;
    public String location;
    
    
    private String selectedName = "VMS";
    private String selectedType = "Файл";
    private String selectedAdress = "http://";
    
    private Vis select;
    
    public Vis() {}
    
    public Vis(int id, String name, String comment, String type, String transfer, String location) {
        this.id = id;
        this.name = name;
        this.comment=comment;
        this.type = type;
        this.transfer = transfer;
        this.location=location;
    }
    
    public void update() {
        try {
            selectedName = this.name;
            selectedType = this.type;
            selectedAdress = this.transfer;
        } catch (Exception ex) {
            Logger.getLogger(CustomizeRole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    
    
    public String getSelectedName() {
        return selectedName;
    }

    public void setSelectedName(String selectedName) {
        this.selectedName = selectedName;
    }
    
    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }
    
    public String getSelectedAdress() {
        return selectedAdress;
    }

    public void setSelectedAdress(String selectedAdress) {
        this.selectedAdress = selectedAdress;
    } 
    public void onRowSelect(SelectEvent event) {
       System.out.println(event.getObject());
    }

    public Vis getSelect() {
        return select;
    }
    
    public void setSelect(Vis select) {
        this.select = select;
    }
 
}