package TrafficManagementSystem.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import TrafficManagementSystem.model.TrafficSystem;
import TrafficManagementSystem.model.TrafficSensor;
import TrafficManagementSystem.model.TrafficSignal;

public class TrafficSystemDAO {
    
    // findTrafficSystemByID(int systemID): 
    // method takes a systemID and returns the TrafficSystem object if it exists in DB.
    public TrafficSystem findTrafficSystemByID(int systemID) throws SQLException {
        // step 1: connect
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM traffic_system WHERE system_id=?;";

        // step 2: create prepared statement
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, systemID);

        // step 3: execute query
        ResultSet rs = pstmt.executeQuery();
        TrafficSystem system = null;
        if (rs.next()) {
            int id = rs.getInt("system_id");
            system = new TrafficSystem(id);
        }

        // step 4: close
        rs.close();
        pstmt.close();
        conn.close();
        return system;
    }

    //this will get all systems and load the sensor and signals based on the systemID
    public List<TrafficSystem> getAllTrafficSystems() throws SQLException {

        Connection conn = DBConnection.getConnection();

        String sql = "SELECT * FROM traffic_system;";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        TrafficSystem system;
        ArrayList<TrafficSystem> systemList = new ArrayList<>();

        while (rs.next()) {

            int systemId = rs.getInt("system_id");

            system = new TrafficSystem(systemId);

            TrafficSensorDAO sensorDAO = new TrafficSensorDAO();
            system.setSensors(new ArrayList<>(sensorDAO.getAllSensors(systemId)));
            
            TrafficSignalDAO signalDAO = new TrafficSignalDAO();
            system.setSignals(new ArrayList<>(signalDAO.getAllSignals(systemId)));
            
            systemList.add(system);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return systemList;
    }

    //this will get all systems and load the sensor and signals based on the systemID & location
    public List<TrafficSystem> getAllTrafficSystems(String location) throws SQLException {

        Connection conn = DBConnection.getConnection();

        String sql = "SELECT * FROM traffic_system;";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        TrafficSystem system;
        ArrayList<TrafficSystem> systemList = new ArrayList<>();

        while (rs.next()) {

            int systemId = rs.getInt("system_id");
            system = new TrafficSystem(systemId);

            TrafficSensorDAO sensorDAO = new TrafficSensorDAO();
            system.setSensors(new ArrayList<>(sensorDAO.getAllSensors(systemId, location)));

            TrafficSignalDAO signalDAO = new TrafficSignalDAO();
            system.setSignals(new ArrayList<>(signalDAO.getAllSignals(systemId, location)));

            systemList.add(system);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return systemList;
    }

    //addNewTrafficSystem(int systemId): method takes systemId as input, adds the traffic system into the database,and returns the number of rows affected.
    public int addNewTrafficSystem(int systemId) throws SQLException {

        //step1: connect to the database
        Connection conn = DBConnection.getConnection();

        //step2: create a prepared statement
        String sql = "INSERT INTO traffic_system VALUES(?);";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, systemId);

        //step3: execute the query
        int insertStatus = pstmt.executeUpdate(); // return 1 if success / 0 if fail

        //step4: close the connection
        pstmt.close();
        conn.close();

        return insertStatus;
    }

    public int updateTrafficSystem(int oldSystemId, int newSystemId) throws SQLException {

    //step1: connect to the database
    Connection conn = DBConnection.getConnection();

    //step2: create the prepared statement
    String sql = "UPDATE traffic_system SET system_id=? WHERE system_id=?;";

    PreparedStatement pstmt = conn.prepareStatement(sql);

    pstmt.setInt(1, newSystemId);
    pstmt.setInt(2, oldSystemId);

    //step3: execute the statement
    int updateStatus = pstmt.executeUpdate();

    //step4: close the connection
    pstmt.close();
    conn.close();

    return updateStatus;
    }

    public int deleteTrafficSystem(int systemId) throws SQLException {

        //step1: connect to the database
        Connection conn = DBConnection.getConnection();

        //step2: create a prepared statement
        String sql = "DELETE FROM traffic_system WHERE system_id=?;";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, systemId);

        //step3: execute the statement
        int deleteStatus = pstmt.executeUpdate();

        //step4: close the connection
        pstmt.close();
        conn.close();

        return deleteStatus;
    }

}
