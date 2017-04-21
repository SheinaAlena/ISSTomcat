/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectToDB;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import view.Users;

/**
 *
 * @author Алена
 */
@ManagedBean
public class CustomizeRole implements Serializable {

    private String selectedRole = "Администратор";
    private String selectedRoleT = "Администратор";
    private List<String> nameRole;

    private List<String> selectedTable;
    private List<String> table;
    private List<String> tableall;

    private List<String> columnTable;

    private List<String> selectedAuthority;
    private List<String> authority;
    private List<String> authorityall;

    private List<String> user;
    private List<Users> userView;
    private List<String> selectedUser;
    private List<String> columns;

    private List<String> vis;

    private String userRole;

    private int cnt;
    private int rowRole;
    private int rowUser;

    private String newNameRole;
    private String newRole;
    private String logUser;

    private String selectChangeStatus;
    private String selectChangeRole;
    private List<String> status;
    private List<String> idRole;
    private List<String> idRoleT;

    private Users selectedUserChange;
    private String selectTableVis;
    private String selectColumnsTableVis;

    private Users changeID;
    private String selectChangeRoleId;
    
    

    @PostConstruct
    public void init() {
        try {
            nameRole = ConnectToDB.getData("name", "role").data;
            table = ConnectToDB.getData("name_rus", "tables").data;
            columnTable = ConnectToDB.getData("\"name_rusC\"", "columns").data;
            authority = ConnectToDB.getData("name", "authority").data;
            vis = ConnectToDB.getData("name", "vis").data;
            authorityall = ConnectToDB.getData("*", "authority").data;
            tableall = ConnectToDB.getData("*", "tables").data;

            user = ConnectToDB.getData("users.*, role.name", "role, user_role, users where  user_role.\"ID_user\" = users.\"ID\" and  user_role.\"ID_role\" = role.\"ID\"").data;
            columns = ConnectToDB.getData("users.*,\"ID_role\"", "users, user_role where user_role.\"ID\"=users.\"ID\"").name;

            rowRole = nameRole.size();
            cnt = columns.size();

            userView = UsersDB.getData().data;
            
            selectedAuthority = ConnectToDB.getData("authority.name",
                    "public.role_authority, public.role, public.authority WHERE role_authority.\"ID_role\"=role.\"ID\" and role_authority.\"ID_authority\"=authority.\"ID\" and role.name='" + selectedRole + "'").data;

            // logUser=(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            selectedTable = ConnectToDB.getData("tables.name_rus",
                    "public.role_tables, public.role, public.tables WHERE role_tables.\"ID_role\"=role.\"ID\" and role_tables.\"ID_tables\"=tables.\"ID\" and role.name='" + selectedRole + "'").data;

            status = new ArrayList<String>();
            status.add("Активен");
            status.add("Неактивен");
            status.add("Заблокирован");
        } catch (Exception ex) {
            Logger.getLogger(dataDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void refreshRole() {
        try {
            selectedAuthority = ConnectToDB.getData("authority.name",
                    "public.role_authority, public.role, public.authority WHERE role_authority.\"ID_role\"=role.\"ID\" and role_authority.\"ID_authority\"=authority.\"ID\" and role.name='" + selectedRole + "'").data;

        } catch (Exception ex) {
            Logger.getLogger(CustomizeRole.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void refreshRoleT() {
        try {
            selectedTable = ConnectToDB.getData("tables.name_rus",
                    "public.role_tables, public.role, public.tables WHERE role_tables.\"ID_role\"=role.\"ID\" and role_tables.\"ID_tables\"=tables.\"ID\" and role.name='" + selectedRoleT + "'").data;
        } catch (Exception ex) {
            Logger.getLogger(CustomizeRole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addRoles() {
        rowRole = rowRole + 1;
        try {
            if (newNameRole != null) {
                ConnectToDB.insertData("public.role( name)", "'" + newNameRole + "'");
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomizeRole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addAuthority() {

        try {
            idRole = ConnectToDB.getData("\"ID\"", "role where role.name='" + selectedRole + "'").data;

            ConnectToDB.deleteData("role_authority", "\"ID_role\"=" + idRole.get(0) + "");
            for (int i = 0; i < selectedAuthority.size(); i++) {
                for (int j = 1; j < authorityall.size(); j = j + 2) {
                    if (selectedAuthority.get(i).equals(authorityall.get(j))) {
                        ConnectToDB.insertData("role_authority( \"ID_role\", \"ID_authority\")", idRole.get(0) + ", " + authorityall.get(j - 1));
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomizeRole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addTables() {
        try {
            idRoleT = ConnectToDB.getData("\"ID\"", "role where role.name='" + selectedRoleT + "'").data;
            ConnectToDB.deleteData("role_tables", "\"ID_role\"=" + idRoleT.get(0) + "");
            for (int i = 0; i < selectedTable.size(); i++) {
                for (int j = 2; j < tableall.size(); j = j + 3) {
                    if (selectedTable.get(i).equals(tableall.get(j))) {
                        ConnectToDB.insertData("role_tables( \"ID_role\", \"ID_tables\")", idRoleT.get(0) + ", " + tableall.get(j - 2));
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomizeRole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buttonAdd() {
        try {
            cnt = columns.size();
        } catch (Exception ex) {
            Logger.getLogger(dataDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



     public void onRowEdit(RowEditEvent event) {
         
        try {
            changeID=(Users)event.getObject();
            ConnectToDB.updateData("users", "status='" + selectChangeStatus + "' where \"ID\"=" + changeID.getId());
            selectChangeRoleId=ConnectToDB.getData("\"ID\"", "role where name='"+selectChangeRole+"'").data.get(0);
            ConnectToDB.updateData("user_role", "\"ID_role\"=" + selectChangeRoleId + " where \"ID_user\"=" + changeID.getId());
        userView = UsersDB.getData().data;
        } catch (Exception ex) {
            Logger.getLogger(CustomizeRole.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Users getChangeID() {
        return changeID;
    }

    public void setChangeID(Users changeID) {
        this.changeID = changeID;
    }

    public String getSelectChangeRoleId() {
        return selectChangeRoleId;
    }

    public void setSelectChangeRoleId(String selectChangeRoleId) {
        this.selectChangeRoleId = selectChangeRoleId;
    }

    public String getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }

    public List<String> getNameRole() {
        return nameRole;
    }

    public List<String> getSelectedTable() {
        return selectedTable;
    }

    public List<String> getTable() {
        return table;
    }

    public void setSelectedTable(List<String> selectedTable) {
        this.selectedTable = selectedTable;
    }

    public String getNewNameRole() {

        return newNameRole;
    }

    public void setNewNameRole(String newNameRole) {
        this.newNameRole = newNameRole;
    }

    public List<String> getSelectedAuthority() {
        return selectedAuthority;
    }

    public void setSelectedAuthority(List<String> selectedAuthority) {
        this.selectedAuthority = selectedAuthority;
    }

    public List<String> getAuthority() {
        return authority;
    }

    public List<String> getUser() {
        return user;
    }

    public String getSingleUser() {
        return user.get(0);
    }

    public void setUser(List<String> user) {
        this.user = user;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public int getCnt() {
        return cnt;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getRowRole() {
        return rowRole;
    }

    public String getSelectChangeStatus() {
        return selectChangeStatus;
    }

    public void setSelectChangeStatus(String selectChangeStatus) {
        this.selectChangeStatus = selectChangeStatus;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public Users getSelectedUserChange() {
        return selectedUserChange;
    }

    public void setSelectedUserChange(Users selectedUserChange) {
        this.selectedUserChange = selectedUserChange;
    }

    public String getSelectChangeRole() {
        return selectChangeRole;
    }

    public void setSelectChangeRole(String selectChangeRole) {
        this.selectChangeRole = selectChangeRole;
    }

    public String getSelectTableVis() {
        return selectTableVis;
    }

    public void setSelectTableVis(String selectTableVis) {
        this.selectTableVis = selectTableVis;
    }

    public List<String> getColumnTable() {
        return columnTable;
    }

    public void setColumnTable(List<String> columnTable) {
        this.columnTable = columnTable;
    }

    public String getSelectColumnsTableVis() {
        return selectColumnsTableVis;
    }

    public void setSelectColumnsTableVis(String selectColumnsTableVis) {
        this.selectColumnsTableVis = selectColumnsTableVis;
    }

    public List<String> getVis() {
        return vis;
    }

    public void setVis(List<String> vis) {
        this.vis = vis;
    }

    public List<Users> getUserView() {
        return userView;
    }

    public void setUserView(List<Users> userView) {
        this.userView = userView;
    }

    public String getLogUser() {
        return logUser;
    }

    public void setLogUser(String logUser) {
        this.logUser = logUser;
    }

    public String getSelectedRoleT() {
        return selectedRoleT;
    }

    public void setSelectedRoleT(String selectedRoleT) {
        this.selectedRoleT = selectedRoleT;
    }

    public List<String> getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(List<String> selectedUser) {
        this.selectedUser = selectedUser;
    }

    public int getRowUser() {
        return rowUser;
    }

    public void setRowUser(int rowUser) {
        this.rowUser = rowUser;
    }

   
}
