package hotel;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String name;
    private int capacity;
    private List<Person> roster;

    public Hotel(String hotelName, int capacity) {
        this.name = hotelName;
        this.capacity = capacity;
        this.roster = new ArrayList<>();
    }

    public int getCount() {
        return this.roster.size();
    }

    public void add(Person person) {
        if (capacity > this.getCount()) {
            this.roster.add(person);
            this.capacity--;
        }
    }

    public boolean remove(String name) {
        Person personToRemove = roster.stream().filter(p -> p.getName().equals(name))
                .findFirst().orElse(null);
        if (personToRemove != null) {
            this.capacity++;
            return roster.remove(personToRemove);
        } else {
            return false;
        }
    }

    public Person getPerson(String name, String hometown) {
        return this.roster.stream()
                .filter(p -> p.getName().equals(name) && p.getHometown().equals(hometown))
                .findFirst().orElse(null);
    }

    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("The people in the hotel %s are:", this.name)).append("\n");
        for (Person person : roster) {
            builder.append(person.toString()).append("\n");
        }

        return builder.toString().trim();
    }
}
