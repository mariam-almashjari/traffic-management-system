package TrafficManagementSystem.model;

public class InvalidTrafficDataException extends Exception{

    private String message;
    
    public InvalidTrafficDataException() {
    }

    public InvalidTrafficDataException(String message) {
        this.message = message;
    }
    
    public void MyMessage() {
       System.out.println(this.message);
    } 
}

