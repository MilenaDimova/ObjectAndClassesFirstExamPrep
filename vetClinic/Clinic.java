package vetClinic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Clinic {
    private int capacity;
    private List<Pet> data;

    public Clinic(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Pet pet) {
        if (this.capacity >= this.data.size() + 1) {
            this.data.add(pet);
        }
    }

    public boolean remove(String name) {
        Pet petToRemove = this.data.stream().filter(pet -> pet.getName().equals(name))
                .findFirst().orElse(null);
        if (petToRemove != null) {
            return this.data.remove(petToRemove);
        } else {
            return false;
        }
    }

    public Pet getPet(String name, String owner) {
        return this.data.stream().filter(p -> p.getName().equals(name) && p.getOwner().equals(owner))
                .findFirst().orElse(null);
    }

    public Pet getOldestPet() {
        return this.data.stream().max(Comparator.comparing(Pet::getAge)).get();
    }

    public int getCount() {
        return this.data.size();
    }

    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        builder.append("The clinic has the following patients:").append(System.lineSeparator());
        for (Pet pet : data) {
            builder.append(pet.getName()).append(" ").append(pet.getOwner()).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
