package TrafficManagementSystem.model;

import java.util.ArrayList;

public class TrafficSensor extends TrafficEntity implements Monitorable, Cloneable{
    private String status;
    private int systemId;
    private ArrayList<TrafficObserver> observers;
    
    public TrafficSensor() {
        observers = new ArrayList<>();
    }

    public TrafficSensor(String status) {
        this.status = status;
        observers = new ArrayList<>();
    }
    
    public TrafficSensor(int id, String location, String status, int systemId) {
        super(id, location);
        this.status = status;
        this.systemId = systemId;
        observers = new ArrayList<>();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) throws InvalidTrafficDataException{
        this.status = status;
        notifyObservers();
    }
    
    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public void addObserver(TrafficObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(TrafficObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() throws InvalidTrafficDataException {
        for (TrafficObserver observer : observers) {
            observer.update(status);
        }
    }
    
    @Override
    public void monitorSensor() throws InvalidTrafficDataException{
        System.out.println("\tSensor ID: " + getID());
        System.out.println("\tLocation: " + getLocation());

        if (status == null) {
            throw new InvalidTrafficDataException("Error Detected! Sensor status is null for sensor ID: " + super.getID());
        }

        if (status.equalsIgnoreCase("HIGH")) {
            System.out.println("\tTraffic Status: High traffic congestion detected!\n");
        } 
        else if (status.equalsIgnoreCase("MEDIUM")) {
            System.out.println("\tTraffic Status: Moderate traffic flow.\n");
        } 
        else if (status.equalsIgnoreCase("LOW")) {
            System.out.println("\tTraffic Status: Traffic is flowing smoothly.\n");
        } 
        else {
            throw new InvalidTrafficDataException("Error Detected! status is unrecognaizable, for sensor ID: " + getID() + "\n");
        }
    }   
    
    @Override
    public void displayInfo() {
        System.out.println("System ID: " + systemId);
        System.out.println("Sensor ID: " + getID());
        System.out.println("Location: " + getLocation());
        System.out.println("Status: " + status + "\n");
    }
    
    @Override
    public TrafficSensor clone() throws CloneNotSupportedException{
        TrafficSensor ClonedSensor = (TrafficSensor) super.clone();
        return ClonedSensor;
    }
}

