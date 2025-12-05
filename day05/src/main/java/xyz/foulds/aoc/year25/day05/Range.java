package xyz.foulds.aoc.year25.day05;

import java.util.Collection;

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

    public boolean overlaps(final Range other) {
        return  !(this.end < other.start || this.start > other.end);
    }

    public Range combineWith(final Collection<Range> others) {
        return new Range(Math.min(this.start, others.stream().mapToLong(r -> r.start).min().getAsLong()),
                Math.max(this.end, others.stream().mapToLong(r -> r.end).max().getAsLong()));
    }

    public long size() {
        return this.end - this.start + 1;
    }
}
