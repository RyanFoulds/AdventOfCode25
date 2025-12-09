package xyz.foulds.aoc.year25.day09;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.List;

public record Floor(List<Coordinate> coordinates) {

    public long solvePartOne() {
        long maxArea = 0L;
        for (int i = 0; i < coordinates.size() - 1; i++) {
            for (int j = i + 1; j < coordinates.size(); j++) {
                final var area = coordinates.get(i).areaWith(coordinates.get(j));
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

    public long solvePartTwo() {
        final Polygon enclosingArea = new Polygon(
                coordinates.stream().mapToLong(Coordinate::x).mapToInt(l -> (int) l).toArray(),
                coordinates.stream().mapToLong(Coordinate::y).mapToInt(l -> (int) l).toArray(),
                coordinates.size());
        long maxArea = 0L;
        for (int i = 0; i < coordinates.size() - 1; i++) {
            for (int j = i + 1; j < coordinates.size(); j++) {
                final var one = coordinates.get(i);
                final var two = coordinates.get(j);
                if (possibleRectangles(one, two).stream().noneMatch(enclosingArea::contains)) continue;
                final var area = coordinates.get(i).areaWith(coordinates.get(j));
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;

    }

    private List<Rectangle> possibleRectangles(final Coordinate one, final Coordinate two) {
        final var topLeftX = (int) Math.min(one.x(), two.x());
        final var topLeftY = (int) Math.min(one.y(), two.y());
        final var width = (int) one.widthWith(two) - 1;
        final var height = (int) one.heightWith(two) - 1;
        return List.of(new Rectangle(topLeftX, topLeftY, width, height),
                new Rectangle(topLeftX+1, topLeftY, width, height),
                new Rectangle(topLeftX, topLeftY+1, width, height),
                new Rectangle(topLeftX+1, topLeftY+1, width, height));
    }
}
