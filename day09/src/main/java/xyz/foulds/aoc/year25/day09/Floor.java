package xyz.foulds.aoc.year25.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public record Floor(List<Coordinate> coordinates) {

    public long solvePartOne() {
        return search((_, _) -> false);
    }

    public long solvePartTwo() {
        final List<Line> boundaries = new ArrayList<>(coordinates.size());
        for (int i = 0; i < coordinates.size(); i++) {
            int j = i == coordinates.size() - 1 ? 0 : i + 1;
            boundaries.add(new Line(coordinates.get(i), coordinates.get(j)));
        }

        return search((one, two) -> {
            final Rectangle candidate = new Rectangle(one, two);
            return boundaries.stream().anyMatch(candidate::crosses);
        });
    }

    private long search(final BiPredicate<Coordinate, Coordinate> filter) {
        long maxArea = 0L;
        for (int i = 0; i < coordinates.size() - 1; i++) {
            for (int j = i + 1; j < coordinates.size(); j++) {
                final var one = coordinates.get(i);
                final var two = coordinates.get(j);
                if (filter.test(one, two)) continue;

                final var area = one.areaWith(two);
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }
}
