package TrafficManagementSystem.model;

public abstract class TrafficEntity {
    private int ID; 
    private String location; 

    public TrafficEntity() {
    }

    public TrafficEntity(int ID, String location) {
        this.ID = ID;
        this.location = location;
    }

    public int getID() {
        return ID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public abstract void displayInfo();
    
}
