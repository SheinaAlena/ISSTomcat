/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectToDB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
public class ConnectToDB {

    public static DataDB getData(String param1, String param2) throws Exception {
        List<String> data = new ArrayList<String>();
        List<String> name = new ArrayList<String>();
        Connection connection = null;
        Statement ps = null;

        try {

            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/RSS");
            connection = ds.getConnection();
            System.out.println("Соединение установлено");
            ps = connection.createStatement();
            ResultSet result = ps.executeQuery(
                    "SELECT " + param1 + " FROM " + param2 + " ORDER BY 1");
            ResultSetMetaData md = result.getMetaData();
            int cnt = md.getColumnCount(); // получаем кол-во колонок (1..cnt)
            while (result.next()) {
                for (int i = 1; i <= cnt; i++) {
                    data.add(result.getString(i));    // получаем значение
                }
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

        public List<String> data;
        public List<String> name;

        public DataDB(List<String> data, List<String> name) {
            this.data = data;
            this.name = name;
        }

        public List<String> getData() {
            return data;
        }

        public List<String> getName() {
            return name;
        }

    }

    public static void insertData(String param1, String param2) throws Exception {
        Connection connection = null;
        Statement ps = null;

        try {

            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/RSS");
            connection = ds.getConnection();
            System.out.println("Соединение установлено");
            ps = connection.createStatement();
            //Выполним запрос
            ResultSet result = ps.executeQuery(
                    "INSERT INTO " + param1 + " VALUES (" + param2 + ");");
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
    }

    public static void updateData(String param1, String param2) throws Exception {
        Connection connection = null;
        Statement ps = null;

        try {

            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/RSS");
            connection = ds.getConnection();
            System.out.println("Соединение установлено");
            ps = connection.createStatement();
            //Выполним запрос
            ResultSet result = ps.executeQuery(
                    "UPDATE " + param1 + " SET " + param2 + ";");
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
    }
    
    public static void deleteData(String param1, String param2) throws Exception {
        Connection connection = null;
        Statement ps = null;

        try {

            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/RSS");
            connection = ds.getConnection();
            System.out.println("Соединение установлено");
            ps = connection.createStatement();
            //Выполним запрос
            ResultSet result = ps.executeQuery(
                    "DELETE FROM " + param1 + " WHERE " + param2 + ";");
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
    }

    public static DataDB columnData() throws Exception {
        List<String> data = new ArrayList<String>();
        List<String> name = new ArrayList<String>();
        Connection connection = null;
        Statement ps = null;

        try {

            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/RSS");
            connection = ds.getConnection();
            System.out.println("Соединение установлено");
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables("RSS", "public", "%", null);
            while (rs.next()) {
                data.add(rs.getString("TABLE_NAME"));
            }
            rs.close();

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

    public static DataDB tableData() throws Exception {
        List<String> data = new ArrayList<String>();
        List<String> name = new ArrayList<String>();
        Connection connection = null;
        Statement ps = null;

        try {

            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/RSS");
            connection = ds.getConnection();
            System.out.println("Соединение установлено");
            ps = connection.createStatement();
            ResultSet result = ps.executeQuery(
                    "select table_name from information_schema.tables where table_schema='public'");
            ResultSetMetaData md = result.getMetaData();
            int cnt = md.getColumnCount(); // получаем кол-во колонок (1..cnt)
            while (result.next()) {
                for (int i = 1; i <= cnt; i++) {
                    data.add(result.getString(i));    // получаем значение
                }
            }
            result.close();

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
}
