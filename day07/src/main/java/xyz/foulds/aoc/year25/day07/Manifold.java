package xyz.foulds.aoc.year25.day07;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Manifold {
    private static final char SPLITTER = '^';
    private static final char START = 'S';
    private final char[][] grid;
    private Coordinate startingPoint;
    public Manifold(final List<String> input) {
        this.grid = new char[input.size()][input.getFirst().length()];
        for (int i = 0; i < input.size(); i++) {
            final var line = input.get(i);
            for (int j = 0; j < line.length(); j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == START) {
                    this.startingPoint = new Coordinate(i, j);
                }
            }
        }
    }

    public long getSplitCountPartOne() {
        var splitCount = 0L;
        final Queue<Coordinate> queue = new ArrayDeque<>();
        final var cache = new HashSet<Coordinate>();
        queue.offer(startingPoint);
        while (!queue.isEmpty()) {
            final var current = queue.poll();
            final boolean isSplit = grid[current.i()][current.j()] == SPLITTER;
            final var next = isSplit ? current.split() : new Coordinate[]{current.moveDown()};
            if (isSplit) splitCount++;
            for (var n : next) {
                if (n.inBounds(grid) && !cache.contains(n)) {
                    queue.offer(n);
                    cache.add(n);
                }
            }
        }
        return splitCount;
    }

    public long getSplitCountPartTwo() {
        final var results = new HashMap<Coordinate, Long>();
        final var visited = new HashSet<Coordinate>();
        final var queue = new PriorityQueue<>(Comparator.comparing(Coordinate::i));
        results.put(startingPoint, 1L);
        queue.offer(startingPoint);

        while (!queue.isEmpty()) {
            final var current = queue.poll();
            final var quantity = results.get(current);
            if (current.i() == grid.length - 1) {
                continue;
            }

            final var nextOnes = grid[current.i() + 1][current.j()] == SPLITTER ? current.split() : new Coordinate[]{current.moveDown()};
            for (var n : nextOnes) {
                if (n.inBounds(grid)) {
                    results.merge(n, quantity, Long::sum);
                    if (visited.add(n)) {
                        queue.offer(n);
                    }
                }
            }
        }

        return results.entrySet().stream()
                .filter(entry -> entry.getKey().i() == grid.length - 1)
                .mapToLong(Map.Entry::getValue)
                .sum();
    }
}
