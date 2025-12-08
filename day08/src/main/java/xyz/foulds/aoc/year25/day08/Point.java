package xyz.foulds.aoc.year25.day08;

public class Point {
    private final long x;
    private final long y;
    private final long z;

    private Point head;

    public Point(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point(final String line) {
        final var nums = line.split(",");
        this(Long.parseLong(nums[0]), Long.parseLong(nums[1]), Long.parseLong(nums[2]));
    }

    public Point findHead() {
        var next = this;
        while (next.head != null) {
            next = next.head;
        }
        return next;
    }

    public void setHead(final Point head) {
        this.head = head;
    }

    public long squaredDistanceTo(final Point other) {
        final var dx = other.x - this.x;
        final var dy = other.y - this.y;
        final var dz = other.z - this.z;
        return dx*dx + dy*dy + dz*dz;
    }

    public long getX() {
        return x;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point other)) {
            return false;
        }
        return other.x == this.x && other.y == this.y && other.z == this.z;
    }

    @Override
    public int hashCode() {
        return Math.toIntExact(31 * (31 * x + y) + z);
    }
}
