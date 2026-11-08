package TrafficManagementSystem.dao;

import java.sql.*;
import java.util.*;
import TrafficManagementSystem.model.TrafficSignal;

/**
 * This class is a DAO (Data Access Object) - Use JDBC API
 * It encapsulates all the database operations for the TrafficSignal table
 */
public class TrafficSignalDAO {
    
    public TrafficSignal findSignalById(int signalId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement(); 
        
        String querySQL = "SELECT * FROM traffic_signal WHERE id='" + signalId + "';";
        ResultSet rs = stmt.executeQuery(querySQL);
       
        TrafficSignal signal = null;
        
        if (rs.next()) {
            String location = rs.getString("location");
            String state = rs.getString("state");
            int systemId = rs.getInt("system_id");

            // put the data from the resultset into a TrafficSignal Object
            signal = new TrafficSignal(signalId, location, state, systemId);
        }   
        
        // close result set, statement and the connection to the database
        stmt.close(); 
        rs.close();
        conn.close();
        
        return signal;
    }
    
    public int addNewSignal(int signalId, String location, String state, int systemId)  throws SQLException {
        Connection conn = DBConnection.getConnection();
        String insertSQL = "INSERT INTO traffic_signal VALUES(" + signalId + ",'" 
                + location + "','" + state + "'," + systemId + ");";
        System.out.println(insertSQL);
        Statement stmt = conn.createStatement();
        int insertStatus = stmt.executeUpdate(insertSQL);
        stmt.close();
        conn.close();
        return insertStatus;
    }
    
    //Update signal record by signal_id
    public int updateSignal(int signalId, String location, String state, int systemId)  throws SQLException {
        Connection conn = DBConnection.getConnection();
        String updateSQL = "UPDATE traffic_signal SET location ='"+ location 
                + "', state = '"+ state 
                + "', system_id=" + systemId 
                + " WHERE id='"+ signalId +"';";
        System.out.println(updateSQL);
        Statement stmt = conn.createStatement();
        int updateStatus = stmt.executeUpdate(updateSQL);
        stmt.close();
        conn.close();
        return updateStatus;
    }
    
    public int deleteSignal(int signalId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String deleteSQL = "DELETE FROM traffic_signal WHERE id=?;";
        System.out.println(deleteSQL);
        PreparedStatement pstmt = conn.prepareStatement(deleteSQL);
        pstmt.setInt(1, signalId);
        int deleteStatus = pstmt.executeUpdate();
        pstmt.close();
        conn.close();
        return deleteStatus;
    }
    
    public List<TrafficSignal> getAllSignals() throws SQLException {
        String sql = "SELECT * FROM traffic_signal";
        
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        TrafficSignal signal;
        List<TrafficSignal> signalList = new ArrayList<>();
        
        while(rs.next()) {
            int signalId = rs.getInt("id");
            String location = rs.getString("location");
            String state = rs.getString("state");
            int systemId = rs.getInt("system_id");

            // put the data from the resultset into a TrafficSignal Object
            signal = new TrafficSignal(signalId, location, state, systemId);
            
            // add signal to the list
            signalList.add(signal);
        }
        
        // close all the db resources
        rs.close();
        stmt.close();
        conn.close();
        
        return signalList;
    }

    //create a method to get all signals by system_id
    public List<TrafficSignal> getAllSignals(int systemId) throws SQLException {

        //step1: connect to the database
        Connection conn = DBConnection.getConnection();

        //step2: create a prepared statement
        String sql = "SELECT * FROM traffic_signal WHERE system_id=?;";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, systemId);

        //step3: execute the prepared statement
        ResultSet rs = pstmt.executeQuery();

        TrafficSignal signal;
        ArrayList<TrafficSignal> signalList = new ArrayList<TrafficSignal>();

        while (rs.next()) {
            int signalId = rs.getInt("id");
            String location = rs.getString("location");
            String state = rs.getString("state");

            signal = new TrafficSignal(signalId, location, state, systemId);
            signalList.add(signal);
        }

        //step4: close the connection
        rs.close();
        pstmt.close();
        conn.close();

        return signalList;
    }

    //create a method to get all signals by system_id & location
    public List<TrafficSignal> getAllSignals(int systemId, String location) throws SQLException {

        //step1: connect to the database
        Connection conn = DBConnection.getConnection();

        //step2: create a prepared statement
        String sql = "SELECT * FROM traffic_signal WHERE system_id=? AND LOWER(location)=LOWER(?);";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, systemId);
        pstmt.setString(2, location);

        //step3: execute the prepared statement
        ResultSet rs = pstmt.executeQuery();

        TrafficSignal signal;
        ArrayList<TrafficSignal> signalList = new ArrayList<TrafficSignal>();

        while (rs.next()) {

            int signalId = rs.getInt("id");
            String state = rs.getString("state");

            // location already known from parameter OR you can read again:
            // String location = rs.getString("location");

            signal = new TrafficSignal(signalId, location, state, systemId);

            signalList.add(signal);
        }

        //step4: close the connection
        rs.close();
        pstmt.close();
        conn.close();

        return signalList;
    }
} // end TrafficSignalDAO class
