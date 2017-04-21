/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectToDB;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.RowEditEvent;

import view.Vis;
import view.VisConformity;
import view.VisConformityDB;

/**
 *
 * @author Алена
 */
@ManagedBean
public class VisChange implements Serializable {

    private List<Vis> visView;
    private List<VisConformity> visConformity;
    private List<String> columns;
    private String selectVis;
    private String selectVisID;

    private String selectTable;
    private String selectTableID;
    private String selectColumn;

    @PostConstruct
    public void init() {
        try {
            visView = VisDB.getData().data;
            columns = ConnectToDB.getData("\"name_rusC\"", "columns").data;
        } catch (Exception ex) {
            Logger.getLogger(dataDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void change() {
        try {
            selectVisID = ConnectToDB.getData("\"ID\"", "vis where name='" + selectVis + "'").data.get(0);
            visConformity = VisConformityDB.getData(" where \"ID_vis\"=" + selectVisID).data;

        } catch (Exception ex) {
            Logger.getLogger(VisChange.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void tableColumn() {
        try {
            selectTableID = ConnectToDB.getData("\"ID\"", "tables where name_rus='" + selectTable + "'").data.get(0);
            columns = ConnectToDB.getData("\"name_rusC\"", "columns where \"ID_table\"=" + selectTableID).data;
        } catch (Exception ex) {
            Logger.getLogger(VisChange.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void save() {

    }


    public String getSelectVis() {
        return selectVis;
    }

    public void setSelectVis(String selectVis) {
        this.selectVis = selectVis;
    }

    public List<Vis> getVisView() {
        return visView;
    }

    public void setVisView(List<Vis> visView) {
        this.visView = visView;
    }

    public List<VisConformity> getVisConformity() {
        return visConformity;
    }

    public void setVisConformity(List<VisConformity> visConformity) {
        this.visConformity = visConformity;
    }

    public String getSelectTable() {
        return selectTable;
    }

    public void setSelectTable(String selectTable) {
        this.selectTable = selectTable;
    }

    public String getSelectColumn() {
        return selectColumn;
    }

    public void setSelectColumn(String selectColumn) {
        this.selectColumn = selectColumn;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

}
