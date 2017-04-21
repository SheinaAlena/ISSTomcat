/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connectToDB.CheckboxView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Алена
 */
public class VisConformityDB {
    public static DataDB getData(String param) throws Exception {
        List<VisConformity> data = new ArrayList<VisConformity>();
        List<String> name = new ArrayList<String>();
        Connection connection = null;
        Statement ps = null;

        try {

            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/RSS");
            connection = ds.getConnection();
            System.out.println("Соединение установлено c visConformity");
            ps = connection.createStatement();
            ResultSet result = ps.executeQuery(
                    "SELECT * FROM vis_conformity"+param+" ORDER BY 1");
            System.out.println(result);
            ResultSetMetaData md = result.getMetaData();
            int cnt = md.getColumnCount(); // получаем кол-во колонок (1..cnt)
            while (result.next()) {
                
                    int id=result.getInt("ID");
                    String name_old=result.getString("name_old");
                    String name_new=result.getString("name_new");
                    String ID_vis=result.getString("ID_vis");
                    
                    VisConformity visConformity  = new VisConformity(id, name_old,name_new,ID_vis);
                    data.add(visConformity);
                
            }

            for (int i = 1; i <= cnt; i++) {
                name.add(md.getColumnName(i));  // получем имя колонки

            }

        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(CheckboxView.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CheckboxView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        DataDB arr = new DataDB(data, name);
        return arr;
    }
    static public class DataDB {

        public List<VisConformity> data;
        public List<String> name;

        public DataDB(List<VisConformity> data, List<String> name) {
            this.data = data;
            this.name = name;
        }

        public List<VisConformity> getData() {
            return data;
        }

        public List<String> getName() {
            return name;
        }

    }
}
