package TrafficManagementSystem.dao;

import java.sql.*;
import java.util.*;
import TrafficManagementSystem.model.TrafficSensor;

public class TrafficSensorDAO {

// Find one sensor by id (id is String)
public TrafficSensor findSensorById(int sensorId) throws SQLException {
    Connection conn = DBConnection.getConnection();
    Statement stmt = conn.createStatement();

    String querySQL = "SELECT * FROM traffic_sensor WHERE id='" + sensorId + "';";
    ResultSet rs = stmt.executeQuery(querySQL);

    TrafficSensor sensor = null;

    if (rs.next()) {
        String location = rs.getString("location");
        String status = rs.getString("status");
        int systemId = rs.getInt("system_id");
        sensor = new TrafficSensor(sensorId, location, status, systemId);
    }

    // close resources
    rs.close();
    stmt.close();
    conn.close();

    return sensor;
}

// Insert a new sensor (all columns)
public int addNewSensor(int sensorId, String location, String status, int systemId)
throws SQLException {
    Connection conn = DBConnection.getConnection();
    String insertSQL = "INSERT INTO traffic_sensor VALUES('" 
            + sensorId + "','" 
            + location + "','" 
            + status + "','" 
            + systemId + "');";
    System.out.println(insertSQL);
    Statement stmt = conn.createStatement();
    int insertStatus = stmt.executeUpdate(insertSQL);
    stmt.close();
    conn.close();
    return insertStatus;
}

// Update sensor by id
public int updateSensor(int sensorId, String location, String status, int systemId) 
throws SQLException {
    Connection conn = DBConnection.getConnection();
    String updateSQL = "UPDATE traffic_sensor SET location = '" + location 
            + "', status = '" + status 
            + "', system_id = '" + systemId 
            + "' WHERE id = '" + sensorId + "';";
    System.out.println(updateSQL);
    Statement stmt = conn.createStatement();
    int updateStatus = stmt.executeUpdate(updateSQL);
    stmt.close();
    conn.close();
    return updateStatus;
}

// Delete sensor by id (PreparedStatement)
public int deleteSensor(int sensorId) 
throws SQLException {
    Connection conn = DBConnection.getConnection();
    String deleteSQL = "DELETE FROM traffic_sensor WHERE id=?;";
    System.out.println(deleteSQL);
    PreparedStatement pstmt = conn.prepareStatement(deleteSQL);
    pstmt.setInt(1, sensorId);
    int deleteStatus = pstmt.executeUpdate();
    pstmt.close();
    conn.close();
    return deleteStatus;
}

// Get all sensors
public List<TrafficSensor> getAllSensors()
throws SQLException {
    String sql = "SELECT * FROM traffic_sensor";

    Connection conn = DBConnection.getConnection();
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(sql);

    TrafficSensor sensor;
    ArrayList<TrafficSensor> sensorList = new ArrayList<TrafficSensor>();
    
    while (rs.next()) {
        int sensorId = rs.getInt("id");
        String location = rs.getString("location");
        String status = rs.getString("status");
        int systemId = rs.getInt("system_id");

        sensor = new TrafficSensor(sensorId, location, status, systemId);
        sensorList.add(sensor);
    }

    rs.close();
    stmt.close();
    conn.close();

    return sensorList;
}

// Get all sensors by system_id
public List<TrafficSensor> getAllSensors(int systemId)
throws SQLException {
    Connection conn = DBConnection.getConnection();

    String sql = "SELECT * FROM traffic_sensor WHERE system_id=?;";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, systemId);

    ResultSet rs = pstmt.executeQuery();

    TrafficSensor sensor;
    ArrayList<TrafficSensor> sensorList = new ArrayList<TrafficSensor>();
    
    while (rs.next()) {
        int sensorId = rs.getInt("id");
        String location = rs.getString("location");
        String status = rs.getString("status");

        sensor = new TrafficSensor(sensorId, location, status, systemId);
        sensorList.add(sensor);
    }

    rs.close();
    pstmt.close();
    conn.close();

    return sensorList;
}

// Get all sensors by system_id & location
public List<TrafficSensor> getAllSensors(int systemId, String location)
throws SQLException {
    Connection conn = DBConnection.getConnection();

    String sql = "SELECT * FROM traffic_sensor WHERE system_id=? AND LOWER(location) = LOWER(?);";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, systemId);
    pstmt.setString(2, location);

    ResultSet rs = pstmt.executeQuery();

    TrafficSensor sensor;
    ArrayList<TrafficSensor> sensorList = new ArrayList<TrafficSensor>();

    while (rs.next()) {
        int sensorId = rs.getInt("id");
        String status = rs.getString("status");

        sensor = new TrafficSensor(sensorId, location, status, systemId);
        sensorList.add(sensor);
    }

    rs.close();
    pstmt.close();
    conn.close();

    return sensorList;
}
}
