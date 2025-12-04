package xyz.foulds.aoc.year25.day04;

import java.util.List;
import java.util.stream.IntStream;

public class Grid {
    private static final char PAPER = '@';
    private final char[][] grid;
    public Grid(final List<String> rows) {
        grid = new char[rows.size()][rows.getFirst().length()];
        for (int i = 0; i < rows.size(); i++) {
            final String row = rows.get(i);
            for (int j = 0; j <row.length(); j++) {
                grid[i][j] = row.charAt(j);
            }
        }
    }

    public long getAccessibleCount() {
        long count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == PAPER && countAdjacent(i, j) < 4) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public long getAllAccessibleCount() {
        long count = 0;
        long lastRemoved = Long.MAX_VALUE;
        while (lastRemoved > 0) {
            lastRemoved = getAccessibleAndRemove();
            count += lastRemoved;
        }
        return count;
    }

    private long getAccessibleAndRemove() {
        long count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == PAPER && countAdjacent(i, j) < 4) {
                    count += 1;
                    grid[i][j] = 'x';
                }
            }
        }
        return count;
    }

    private long countAdjacent(final int i, final int j) {
        return IntStream.rangeClosed(-1, 1)
                .map(diff -> diff + i)
                .mapToLong(y -> IntStream.rangeClosed(-1, 1)
                        .map(diff -> diff + j)
                        .filter(x -> x != j || y != i)
                        .filter(x -> isPaper(y, x))
                        .count())
                .sum();
    }

    private boolean isPaper(final int i, final int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length) {
            return false;
        } else {
            return grid[i][j] == PAPER;
        }
    }
}
