package TrafficManagementSystem.model;

public interface Monitorable {
    public abstract void monitorSensor() throws InvalidTrafficDataException;
}
