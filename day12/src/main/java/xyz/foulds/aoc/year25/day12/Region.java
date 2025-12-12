package xyz.foulds.aoc.year25.day12;

import java.util.HashMap;
import java.util.Map;

public class Region {

    private final long area;
    private final Map<Integer, Long> shapeIdToCount;

    public Region(final String line) {
        var split = line.split(": ");
        var grid = split[0].split("x");
        this.area = Long.parseLong(grid[0]) * Long.parseLong(grid[1]);

        final var counts = split[1].split("\\s+");
        this.shapeIdToCount = new HashMap<>(counts.length);
        for (int i = 0; i < counts.length; i++) {
            shapeIdToCount.put(i, Long.parseLong(counts[i]));
        }
    }

    public boolean willTheyFit(final Map<Integer, Shape> shapes) {
        final long totalShapeArea = shapeIdToCount.entrySet()
                .stream()
                .mapToLong(entry -> entry.getValue() * shapes.get(entry.getKey()).area())
                .sum();
        // Only places a bound on being definitely impossible.
        return totalShapeArea <= area;
    }
}
