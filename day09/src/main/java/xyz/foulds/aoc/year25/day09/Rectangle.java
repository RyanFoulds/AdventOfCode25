package xyz.foulds.aoc.year25.day09;

public record Rectangle(long topY, long bottomY, long leftX, long rightX) {
    public Rectangle(Coordinate one, Coordinate two) {
        var tY = Math.max(one.y(), two.y());
        var bY = Math.min(one.y(), two.y());
        var lX = Math.min(one.x(), two.x());
        var rX = Math.max(one.x(), two.x());
        this(tY, bY, lX, rX);
    }
    public boolean crosses(final Line line) {
        return line.crosses(this);
    }
}
