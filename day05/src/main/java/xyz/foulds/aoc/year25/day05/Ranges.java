package xyz.foulds.aoc.year25.day05;

import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class Ranges {
    private final Stack<Range> ranges;
    public Ranges(final List<Range> inputRanges) {
        this.ranges = new Stack<>();
        inputRanges.sort(Comparator.comparing(Range::getStart));

        for (var input : inputRanges) {
            if (overlapsLatest(input)) {
                final var latest = ranges.pop();
                ranges.push(Range.combine(latest, input));
            } else {
                ranges.push(input);
            }
        }
    }

    public long getCombinedSize() {
        return ranges.stream().mapToLong(Range::size).sum();
    }

    private boolean overlapsLatest(final Range other) {
        if (ranges.isEmpty()) {
            return false;
        }
        final var latest = ranges.peek();
        return other.getStart() <= latest.getEnd();
    }
}
