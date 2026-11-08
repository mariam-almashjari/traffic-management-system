package com.mycompany.trafficmanagementsystem;

import TrafficManagementSystem.model.CityGroup;
import TrafficManagementSystem.model.InvalidTrafficDataException;
import TrafficManagementSystem.model.SmartSignalDecorator;
import TrafficManagementSystem.model.TrafficEntityFactory;
import TrafficManagementSystem.model.TrafficSensor;
import TrafficManagementSystem.model.TrafficSignal;
import TrafficManagementSystem.model.TrafficSystem;

public class TrafficManagementSystem {

    public static void main(String[] args) {
        try {
            TrafficSystem system1 = new TrafficSystem(1);

            TrafficSensor s1 = system1.createSensor(1, "Al falah", "LOW", 1);
            TrafficSensor s2 = system1.createSensor(2, "Al shamkha", "HIGH", 1);

            TrafficSignal signal1 = system1.createSignal(1, "Al falah", "RED", 1);
            TrafficSignal signal2 = system1.createSignal(2, "Al shamkha", "GREEN", 1);

            System.out.println("Monitoring system (Factory used):");
            system1.monitorTraffic();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }     
    }
}
