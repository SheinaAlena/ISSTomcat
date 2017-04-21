/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectToDB;

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
import view.Users;
import view.Vis;

/**
 *
 * @author Алена
 */
public class VisDB {
    
    public static DataDB getData() throws Exception {
        List<Vis> data = new ArrayList<Vis>();
        List<String> name = new ArrayList<String>();
        Connection connection = null;
        Statement ps = null;

        try {

            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/RSS");
            connection = ds.getConnection();
            System.out.println("Соединение установлено c vis");
            ps = connection.createStatement();
            ResultSet result = ps.executeQuery(
                    "SELECT * FROM vis ORDER BY 1");
            System.out.println(result);
            ResultSetMetaData md = result.getMetaData();
            int cnt = md.getColumnCount(); // получаем кол-во колонок (1..cnt)
            while (result.next()) {
                
                    int id=result.getInt("ID");
                    String nameVis=result.getString("name");
                    String comment=result.getString("comment");
                    String typeFile=result.getString("typeFile");
                    String transfer=result.getString("transfer");
                    String location=result.getString("location");
                    
                    Vis vis = new Vis(id, nameVis,comment,typeFile,transfer,location);
                    data.add(vis);
                
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

        public List<Vis> data;
        public List<String> name;

        public DataDB(List<Vis> data, List<String> name) {
            this.data = data;
            this.name = name;
        }

        public List<Vis> getData() {
            return data;
        }

        public List<String> getName() {
            return name;
        }

    }
}
