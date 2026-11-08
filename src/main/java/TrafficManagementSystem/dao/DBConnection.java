package TrafficManagementSystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            String dbURL = "jdbc:sqlite:traffic.db";
            conn = DriverManager.getConnection(dbURL);

            //used this in order to link the sensor and signal table directly 
            //with the system table. so all can update, add and delete 
            //according to the system id's available
            Statement stmt = conn.createStatement();
            stmt.execute("PRAGMA foreign_keys = ON");
            stmt.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return conn;
    }
}