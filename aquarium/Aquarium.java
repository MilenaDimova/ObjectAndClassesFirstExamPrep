package aquarium;

import java.util.ArrayList;
import java.util.List;

public class Aquarium {
    private String name;
    private int capacity;
    private int size;
    private List<Fish> fishInPool;

    public Aquarium(String name, int capacity, int size) {
        this.name = name;
        this.capacity = capacity;
        this.size = size;
        this.fishInPool = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public int getFishInPool() {
        return fishInPool.size();
    }

    public void add(Fish fish) {
        boolean haveSameFish = false;
        for (Fish f : fishInPool) {
            if (f.getName().equals(fish.getName())) {
                haveSameFish = true;
                break;
            }
        }
        if (!haveSameFish && capacity >= getFishInPool() + 1) {
            this.fishInPool.add(fish);
        }
    }

    public boolean remove(String name) {
        Fish fishToRemove = fishInPool.stream().filter(f -> f.getName().equals(name))
                .findFirst().orElse(null);
        if (fishToRemove != null) {
            return fishInPool.remove(fishToRemove);
        } else {
            return false;
        }
    }

    public Fish findFish(String name) {
        for (Fish fish : fishInPool) {
            if (fish.getName().equals(name)) {
                return fish;
            }
        }
        return null;
    }

    public String report() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Aquarium: %s ^ Size: %d", getName(), getSize())).append("\n");
        for (Fish fish : fishInPool) {
            builder.append(fish.toString()).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
