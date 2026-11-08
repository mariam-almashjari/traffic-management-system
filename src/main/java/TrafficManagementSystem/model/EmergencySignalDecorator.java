package TrafficManagementSystem.model;

public class EmergencySignalDecorator extends TrafficSignalDecorator {

    public EmergencySignalDecorator(TrafficSignal decoratedSignal) {
        super(decoratedSignal);
    }

    @Override
    public void update(String status) throws InvalidTrafficDataException {
        super.update(status);

        if (status.equalsIgnoreCase("HIGH")) {
            System.out.println("Emergency Feature: Priority route check activated for signal ID " + getID());
        }
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Decorator Feature: Emergency Mode Enabled\n");
    }
}
