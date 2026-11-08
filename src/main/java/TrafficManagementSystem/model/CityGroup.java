package TrafficManagementSystem.model;

public class CityGroup<T> {

    private T city;

    public CityGroup() {
    }

    public CityGroup(T city) {
        this.city = city;
    }

    public void setCity(T city) {
        this.city = city;
    }

    public T getCity() {
        return city;
    }

    public void display() {
        if (city == null) {
            System.out.println("No city assigned.");
        } else {
            System.out.println("City: " + city.toString());
        }
    }
}
