package TrafficManagementSystem.model;

import java.util.ArrayList;

public class TrafficSystem {
    
    private int systemID;
    private ArrayList<TrafficSensor> sensors;
    private ArrayList<TrafficSignal> signals;
    private TrafficEntityFactory factory;

    public TrafficSystem() {
        this.sensors = new ArrayList<>();
        this.signals = new ArrayList<>();
        this.factory = new TrafficEntityFactory();
    }
    
    public TrafficSystem(int systemID) {
        this.systemID = systemID;
        this.sensors = new ArrayList<TrafficSensor>();
        this.signals = new ArrayList<TrafficSignal>();
        this.factory = new TrafficEntityFactory();
    }

    public TrafficSystem(int systemID, ArrayList sensors, ArrayList signals) {
        this.systemID = systemID;
        this.sensors = new ArrayList<TrafficSensor>(sensors);
        this.signals = new ArrayList<TrafficSignal>(signals);
        this.factory = new TrafficEntityFactory();
    }

    public int getSystemID() {
        return systemID;
    }

    public void setSystemID(int systemID) {
        this.systemID = systemID;
    }
    
    public void setSignals(ArrayList<TrafficSignal> signals) {
        this.signals = signals;
    }

    public void setSensors(ArrayList<TrafficSensor> sensors) {
        this.sensors = sensors;
    }
    
    // FACTORY usage
    public TrafficSensor createSensor(int id, String location, String status, int systemId) {
        TrafficSensor sensor = factory.createSensor(id, location, status, systemId);
        addSensor(sensor);
        return sensor;
    }

    // FACTORY usage
    public TrafficSignal createSignal(int id, String location, String state, int systemId) {
        TrafficSignal signal = factory.createSignal(id, location, state, systemId);
        addSignal(signal);
        return signal;
    }
    
    public void addSensor(TrafficSensor sensor){
        if(sensor == null){
            throw new IllegalArgumentException("Error: Sensor cannot be null");
        }
        else if(!this.sensors.contains(sensor)){ //to not duplicate the sensor twice
           this.sensors.add(sensor);
        }      
        else{
            System.out.println("Sensor " + sensor.getID() + " was added successfully!");
        }
    }
    
    public void addSignal(TrafficSignal signal){
        if(signal.getState() == null){
            throw new IllegalArgumentException("Error: Signal cannot be null");
        }
        else if(!this.signals.contains(signal)){ //to not duplicate the signal twice
           this.signals.add(signal);
        }      
        else{
            System.out.println("Signal " + signal.getID() + " was added successfully!");
        }
    }
    
    public void monitorTraffic() {
        System.out.println("Monitoring sensors...");

        boolean allSensorsValid = true;

        for (TrafficSensor sensor : sensors) {
            try {
                sensor.monitorSensor();
            } catch (InvalidTrafficDataException e) {
                e.MyMessage();
                allSensorsValid = false;
            }
        }

        if (allSensorsValid) {
            System.out.println("\nMonitoring signals...");
            for (TrafficSignal signal : signals) {
                try {
                    signal.monitorSignal();
                } catch (InvalidTrafficDataException e) {
                    e.MyMessage();
                }
            }
        } else {
            System.out.println("Skipping signal monitoring due to invalid sensor data.");
        }
    }
}