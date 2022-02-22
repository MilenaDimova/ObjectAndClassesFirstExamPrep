package parrots;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cage {
    private String name;
    private int capacity;
    private List<Parrot> data;

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void add(Parrot parrot) {
        if (this.capacity > this.data.size()) {
            this.data.add(parrot);
        }
    }

    public boolean remove(String name) {
        Parrot parrotToRemove = this.data.stream().filter(p -> p.getName().equals(name))
                .findFirst().orElse(null);
        if (parrotToRemove != null) {
            return this.data.remove(parrotToRemove);
        } else {
            return false;
        }
    }

    public Parrot sellParrot(String name) {
        Parrot parrotToSell = this.data.stream().filter(p -> p.getName().equals(name))
                .findFirst().orElse(null);
        parrotToSell.setAvailable(false);
        return parrotToSell;
    }

    public List<Parrot> sellParrotBySpecies(String species) {
        List<Parrot> listedParrot = this.data.stream().filter(p -> p.getSpecies().equals(species))
                .collect(Collectors.toList());
        for (Parrot parrot : data) {
            if (parrot.getSpecies().equals(species)) {
                parrot.setAvailable(false);
            }
        }
        return listedParrot;
    }

    public int count() {
        return this.data.size();
    }

    public String report() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Parrots available at %s:", this.name)).append(System.lineSeparator());
        List<Parrot> filteredData = data.stream().filter(p -> p.isAvailable()).collect(Collectors.toList());
        for (Parrot parrot : filteredData) {
            builder.append(parrot.toString()).append(System.lineSeparator());
        }
        return builder.toString();
    }
}
