package christmas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Bag {
    private String color;
    private int capacity;
    private List<Present> data;

    public Bag(String color, int capacity) {
        this.color = color;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getColor() {
        return color;
    }

    public int getCapacity() {
        return capacity;
    }

    public int count() {
        return this.data.size();
    }

    public void add(Present present) {
        if (this.capacity > this.data.size()) {
            this.data.add(present);
        }
    }

    public boolean remove(String name) {
        Present presentToRemove = this.data.stream().filter(p -> p.getName().equals(name))
                .findFirst().orElse(null);
        if (presentToRemove != null) {
            return this.data.remove(presentToRemove);
        } else {
            return false;
        }
    }

    public Present heaviestPresent() {
        return this.data.stream().max(Comparator.comparingDouble(Present::getWeight)).get();
    }

    public Present getPresent(String name) {
        return this.data.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    }

    public String report() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s bag contains:", this.color)).append(System.lineSeparator());
        for (Present present : data) {
            builder.append(present.toString()).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
