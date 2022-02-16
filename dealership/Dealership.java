package dealership;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Dealership {
    public String name;
    public int capacity;
    public List<Car> data;

    public Dealership(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public int getCount() {
        return this.data.size();
    }

    public void add(Car car) {
        if (capacity >= getCount() + 1) {
            this.data.add(car);
        }
    }

    public boolean buy(String manufacturer, String model) {
        Car carToRemove = data.stream().filter(c -> c.getManufacturer().equals(manufacturer) &&
                c.getModel().equals(model)).findFirst().orElse(null);
        if (carToRemove != null) {
            return data.remove(carToRemove);
        } else {
            return false;
        }
    }

    public Car getLatestCar() {
        return data.stream().max(Comparator.comparing(Car::getYear)).get();
    }

    public Car getCar(String manufacturer, String model) {
        return data.stream().filter(c -> c.getManufacturer().equals(manufacturer) &&
                c.getModel().equals(model)).findFirst().orElse(null);
    }

    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("The cars are in a car dealership %s:", this.name)).append("\n");
        for (Car car : data) {
            builder.append(car.toString()).append(System.lineSeparator());
        }
        return builder.toString();
    }
}
