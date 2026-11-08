package TrafficManagementSystem.model;

public interface TrafficObserver {
    void update(String status) throws InvalidTrafficDataException;
}
