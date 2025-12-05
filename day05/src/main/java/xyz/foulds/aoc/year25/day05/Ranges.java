package xyz.foulds.aoc.year25.day05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Ranges {
    private final List<Range> ranges;
    public Ranges(final Collection<Range> inputRanges) {
        this.ranges = new ArrayList<>(inputRanges.size());

        for (var input : inputRanges) {
            Collection<Range> overlapsWith = new ArrayList<>();
            for (var existing : ranges) {
                if (existing.overlaps(input)) {
                    overlapsWith.add(existing);
                }
            }
            if (overlapsWith.isEmpty()) {
                ranges.add(input);
            } else {
                ranges.removeAll(overlapsWith);
                ranges.add(input.combineWith(overlapsWith));
            }
        }
    }

    public long getCombinedSize() {
        return ranges.stream().mapToLong(Range::size).sum();
    }
}
