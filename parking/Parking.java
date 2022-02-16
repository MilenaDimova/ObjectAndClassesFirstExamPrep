package parking;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Parking {
    private String type;
    private int capacity;
    private List<Car> data;

    public Parking(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Car car) {
        if (this.capacity > this.data.size()) {
            this.data.add(car);
        }
    }

    public boolean remove(String manufacturer, String model) {
        Car carToRemove = this.data.stream().filter(c -> c.getManufacturer().equals(manufacturer) &&
                c.getModel().equals(model)).findFirst().orElse(null);
        if (carToRemove != null) {
           return this.data.remove(carToRemove);
        }
        return false;
    }

    public Car getLatestCar() {
        return this.data.stream().max(Comparator.comparing(Car::getYear)).get();
    }

    public Car getCar(String manufacturer, String model) {
        return this.data.stream().filter(c -> c.getManufacturer().equals(manufacturer) &&
                c.getModel().equals(model)).findFirst().orElse(null);
    }

    public int getCount() {
        return this.data.size();
    }

    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("The cars are parked in %s:", this.type)).append(System.lineSeparator());
        for (Car car : data) {
            builder.append(car.toString()).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
