package TrafficManagementSystem.model;

public class TrafficSignal extends TrafficEntity implements TrafficObserver{

    private String state;
    private int systemId;

    public TrafficSignal() {
    }

    public TrafficSignal(String state) {
        this.state = state;
    }

    public TrafficSignal(int id, String location, String state, int systemId) {
        super(id, location);
        this.state = state;
        this.systemId = systemId;
    }

    public String getState() {
        return state;
    }

    public int getID() {
        return super.getID();
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    @Override
    public void update(String status) throws InvalidTrafficDataException {
        if (status == null) {
            throw new InvalidTrafficDataException(
                "Error Detected! Sensor status is null, cannot update signal ID: " + getID()
            );
        }
        if (status.equalsIgnoreCase("HIGH")) {
            setState("GREEN");
        } 
        else if (status.equalsIgnoreCase("MEDIUM")) {
            setState("YELLOW");
        } 
        else if (status.equalsIgnoreCase("LOW")) {
            setState("RED");
        } 
        else {
            throw new InvalidTrafficDataException(
                "Error Detected! Unknown sensor status '" + status + "' for signal ID: " + getID()
            );
        }
    } 
    
    public void monitorSignal() throws InvalidTrafficDataException {
        System.out.println("Monitoring Signal ID: " + getID());
        System.out.println("\tLocation: " + getLocation());

        if (state == null) {
            throw new InvalidTrafficDataException("Error Detected! Signal state is null for signal ID: " + getID());
        }

        if (state.equalsIgnoreCase("RED")) {
            System.out.println("\tSignal State: RED - Vehicles must stop.\n");
        } else if (state.equalsIgnoreCase("YELLOW")) {
            System.out.println("\tSignal State: YELLOW - Prepare to stop.\n");
        } else if (state.equalsIgnoreCase("GREEN")) {
            System.out.println("\tSignal State: GREEN - Vehicles may go.\n");
        } else {
            throw new InvalidTrafficDataException("Error Detected! Invalid signal state: " + state + ", for signal ID: " + getID() + "\n");
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Signal ID: " + getID());
        System.out.println("Location: " + getLocation());
        System.out.println("State: " + state + "\n");
    }

}
