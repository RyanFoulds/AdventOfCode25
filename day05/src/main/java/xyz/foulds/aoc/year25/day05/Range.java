package xyz.foulds.aoc.year25.day05;

public class Range {
    private final long start;
    private final long end;
    public Range(final String input) {
        final var range = input.split("-");
        this(Long.parseLong(range[0]), Long.parseLong(range[1]));
    }
    private Range(final long start, final long end) {
        this.start = start;
        this.end = end;
    }

    public boolean contains(final long test) {
        return test >= start && test <= end;
    }
    public long size() {
        return this.end - this.start + 1;
    }
    public long getStart() {
        return start;
    }
    public long getEnd() {
        return end;
    }

    public static Range combine(final Range one, final Range other) {
        return new Range(Math.min(one.start, other.start), Math.max(one.end, other.end));
    }
}
