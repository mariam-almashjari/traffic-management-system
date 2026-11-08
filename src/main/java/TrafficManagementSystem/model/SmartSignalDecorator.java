package TrafficManagementSystem.model;

public class SmartSignalDecorator extends TrafficSignalDecorator {

    public SmartSignalDecorator(TrafficSignal decoratedSignal) {
        super(decoratedSignal);
    }

    @Override
    public void update(String status) throws InvalidTrafficDataException {
        super.update(status);

        if (status.equalsIgnoreCase("HIGH")) {
            System.out.println("Smart Feature: Longer GREEN duration applied for signal ID " + getID());
        } 
        else if (status.equalsIgnoreCase("LOW")) {
            System.out.println("Smart Feature: Reduced GREEN duration applied for signal ID " + getID());
        }
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Decorator Feature: Smart Adaptive Control Enabled\n");
    }
}