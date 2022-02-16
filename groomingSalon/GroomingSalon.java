package groomingSalon;

import java.util.ArrayList;
import java.util.List;

public class GroomingSalon {
    private int capacity;
    private List<Pet> data;

    public GroomingSalon(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public int getCount() {
       return this.data.size();
    }

    public void add(Pet pet) {
        if (capacity > this.getCount()) {
            this.data.add(pet);
            capacity--;
        }
    }

    public boolean remove(String name) {
        Pet petToRemove = data.stream().filter(p -> p.getName().equals(name))
                .findFirst().orElse(null);
        if (petToRemove != null) {
            capacity++;
            return data.remove(petToRemove);
        }
        return false;
    }

    public Pet getPet(String name, String owner) {
        return data.stream().filter(p -> p.getName().equals(name) && p.getOwner().equals(owner))
                .findFirst().orElse(null);
    }

    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        builder.append("The grooming salon has the following clients:").append("\n");
        for (Pet pet : data) {
            builder.append(String.format("%s %s", pet.getName(), pet.getOwner())).append("\n");
        }
        return builder.toString();
    }
}
