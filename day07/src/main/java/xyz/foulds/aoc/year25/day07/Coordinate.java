package xyz.foulds.aoc.year25.day07;

public record Coordinate(int i, int j) {
    public Coordinate moveDown() {
        return new Coordinate(i + 1, j);
    }
    public Coordinate[] split() {
        return new Coordinate[] {new Coordinate(i, j -1), new Coordinate(i, j + 1)};
    }
    public boolean inBounds(char[][] grid) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[i].length;
    }
}
