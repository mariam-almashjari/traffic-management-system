package TrafficManagementSystem.model;

public abstract class TrafficSignalDecorator extends TrafficSignal {

    protected TrafficSignal decoratedSignal;

    public TrafficSignalDecorator(TrafficSignal decoratedSignal) {
        this.decoratedSignal = decoratedSignal;
    }

    public TrafficSignalDecorator(TrafficSignal decoratedSignal, String state) {
        super(state);
        this.decoratedSignal = decoratedSignal;
    }

    public TrafficSignalDecorator(TrafficSignal decoratedSignal, int id, String location, String state, int systemId) {
        super(id, location, state, systemId);
        this.decoratedSignal = decoratedSignal;
    }

    @Override
    public String getState() {
        return decoratedSignal.getState();
    }

    @Override
    public void setState(String state) {
        decoratedSignal.setState(state);
    }

    @Override
    public int getSystemId() {
        return decoratedSignal.getSystemId();
    }

    @Override
    public void setSystemId(int systemId) {
        decoratedSignal.setSystemId(systemId);
    }

    @Override
    public void update(String status) throws InvalidTrafficDataException {
        decoratedSignal.update(status);
    }

    @Override
    public void monitorSignal() throws InvalidTrafficDataException {
        decoratedSignal.monitorSignal();
    }

    @Override
    public void displayInfo() {
        decoratedSignal.displayInfo();
    }
}
