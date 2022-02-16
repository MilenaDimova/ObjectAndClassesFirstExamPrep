package guild;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Guild {
    private String name;
    private int capacity;
    private List<Player> roster;

    public Guild(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.roster = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if (this.capacity > this.roster.size()) {
            this.roster.add(player);
        }
    }

    public boolean removePlayer(String name) {
        Player playerToRemove = this.roster.stream().filter(p -> p.getName().equals(name))
                .findFirst().orElse(null);
        if (playerToRemove != null) {
            return this.roster.remove(playerToRemove);
        } else {
            return false;
        }
    }

    public void promotePlayer(String name) {
        this.roster.stream().filter(p -> p.getName().equals(name))
                .findFirst().ifPresent(playerToRemote -> playerToRemote.setRank("Member"));
    }

    public void demotePlayer(String name) {
        this.roster.stream().filter(p -> p.getName().equals(name))
                .findFirst().ifPresent(playerToDemote -> playerToDemote.setRank("Trial"));
    }

    public Player[] kickPlayersByClass(String clazz) {
        List<Player> removedPlayers = new ArrayList<>();
        for (Player player : roster) {
            if (player.getClazz().equals(clazz)) {
                removedPlayers.add(player);
            }
        }
        Predicate<Player> removePlayersWithTheSameClazz = player -> player.getClazz().equals(clazz);
        this.roster.removeIf(removePlayersWithTheSameClazz);
        Player[] playersToRemove = new Player[removedPlayers.size()];
        return removedPlayers.toArray(playersToRemove);
    }

    public int count() {
        return this.roster.size();
    }

    public String report() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Players in the guild: %s:", this.name)).append(System.lineSeparator());
        for (Player player : roster) {
            builder.append(player.toString()).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
