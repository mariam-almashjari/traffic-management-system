package TrafficManagementSystem.model;

public class TrafficEntityFactory {

    public TrafficSensor createSensor(int id, String location, String status, int systemId) {
        return new TrafficSensor(id, location, status, systemId);
    }

    public TrafficSignal createSignal(int id, String location, String state, int systemId) {
        return new TrafficSignal(id, location, state, systemId);
    }
}
