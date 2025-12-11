package xyz.foulds.aoc.year25.day08;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Playground {
    private final List<Connection> sortedDistanceCache;
    private final List<Point> points;
    private final int connectionsToMake;
    public Playground(final Collection<Point> points, final int connectionsToMake) {
        this.points = new ArrayList<>(points);
        this.connectionsToMake = connectionsToMake;
        this.sortedDistanceCache = new ArrayList<>((points.size() * (points.size() - 1))/2);
        for (int i = 0; i < this.points.size() - 1; i++) {
            for (int j = i+1; j < this.points.size(); j++) {
                sortedDistanceCache.add(new Connection(this.points.get(i), this.points.get(j)));
            }
        }
        sortedDistanceCache.sort(Comparator.comparing(Connection::getDistance));
    }

    // Solve both parts together today, since one is just a continuation of the other.
    public long[] solve() {
        var partOne = 0L;
        final Map<Point, Integer> circuitHeads = new HashMap<>();
        for (int i = 0; i < sortedDistanceCache.size(); i++) {
            final var connection = sortedDistanceCache.get(i);
            final Point firstHead = connection.first().findHead();
            final Point secondHead = connection.second().findHead();
            Integer firstSize = circuitHeads.get(firstHead);
            Integer secondSize = circuitHeads.get(secondHead);

            if (firstSize == null && secondSize == null) {
                connection.second().setHead(connection.first());
                circuitHeads.put(connection.first(), 2);
            } else if (firstSize == null) {
                connection.first().setHead(secondHead);
                circuitHeads.put(secondHead, secondSize + 1);
            } else if (secondSize == null) {
                connection.second().setHead(firstHead);
                circuitHeads.put(firstHead, firstSize + 1);
            } else if (firstHead != secondHead) {
                secondHead.setHead(firstHead);
                circuitHeads.remove(secondHead);
                circuitHeads.put(firstHead, firstSize + secondSize);
            }

            if (i == connectionsToMake - 1) {
                partOne = circuitHeads.values().stream()
                        .sorted(Comparator.reverseOrder())
                        .toList().subList(0, 3)
                        .stream().reduce((a, b) -> a * b)
                        .get();
            } else if (circuitHeads.size() == 1 && circuitHeads.containsValue(points.size())) {
                final var partTwo = sortedDistanceCache.get(i).getWallDistance();
                return new long[]{partOne, partTwo};
            }
        }
        return new long[]{partOne, 0L};
    }
}
