package xyz.foulds.aoc.year25.day09;

public record Coordinate(long x, long y) {

    public Coordinate(final String line) {
        final var split = line.split(",");
        this(Long.parseLong(split[0]), Long.parseLong(split[1]));
    }

    public long areaWith(final Coordinate other) {
        return widthWith(other) * heightWith(other);
    }

    public long widthWith(final Coordinate other) {
        return Math.abs(this.x - other.x) + 1;
    }
    public long heightWith(final Coordinate other) {
        return Math.abs(this.y - other.y) + 1;
    }
}
