package xyz.foulds.aoc.year25.day07;

import java.util.HashSet;
import java.util.List;
import java.util.Stack;

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
        final var stack = new Stack<Coordinate>();
        final var cache = new HashSet<Coordinate>();
        stack.push(startingPoint);
        while (!stack.isEmpty()) {
            final var current = stack.pop();
            if (grid[current.i()][current.j()] == SPLITTER) {
                splitCount++;
                final var next = current.split();
                for (var n : next) {
                    if (n.inBounds(grid) && !cache.contains(n)) {
                        stack.push(n);
                        cache.add(n);
                    }
                }
            } else {
                final var next = current.moveDown();
                if (next.inBounds(grid) && !cache.contains(next)) {
                    stack.push(next);
                    cache.add(next);
                }
            }
        }
        return splitCount;
    }
}
