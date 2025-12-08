package xyz.foulds.aoc.year25.day08;

public record Connection(Point first, Point second) {
    public long getDistance() {
        return first.squaredDistanceTo(second);
    }

    public long getWallDistance() {
        return first.getX() * second.getX();
    }
}
