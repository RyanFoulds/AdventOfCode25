package xyz.foulds.aoc.year25.day09;

public class Line {
    private final long top;
    private final long bottom;
    private final long left;
    private final long right;
    private final Direction direction;

    public Line(final Coordinate one, final Coordinate two) {
        this.top = Math.max(one.y(), two.y());
        this.bottom = Math.min(one.y(), two.y());
        this.left = Math.min(one.x(), two.x());
        this.right = Math.max(one.x(), two.x());
        this.direction = one.x() == two.x() ? Direction.V : Direction.H;
    }

    public boolean crosses(final Rectangle rectangle) {
        if (direction == Direction.H) {
            return top < rectangle.topY() && bottom > rectangle.bottomY()
                    && left < rectangle.rightX() && right > rectangle.leftX();
        } else {
            return left > rectangle.leftX() && right < rectangle.rightX()
                    && top > rectangle.bottomY() && bottom < rectangle.topY();
        }
    }

    private enum Direction {
        H,
        V
    }
}
