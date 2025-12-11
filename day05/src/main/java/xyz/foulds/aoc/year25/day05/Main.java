package xyz.foulds.aoc.year25.day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Main {
    static void main(final String[] args) throws IOException {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Please provide a single file path for the puzzle input.");
        }

        final var ranges = new ArrayList<Range>();
        final var ids = new ArrayList<Long>();

        Files.readAllLines(Paths.get(args[0]))
                .stream()
                .map(String::trim)
                .filter(Predicate.not(String::isBlank))
                .forEach(line -> {
                    if (line.contains("-")){
                        ranges.add(new Range(line));
                    } else {
                        ids.add(Long.parseLong(line));
                    }
                });

        final long start = System.nanoTime();
        final long p1 = ids.stream()
                .mapToLong(Long::longValue)
                .filter(id -> ranges.stream().anyMatch(range -> range.contains(id)))
                .count();
        final var combined = new Ranges(ranges);
        final long p2 = combined.getCombinedSize();
        final long end = System.nanoTime();

        final var millis = (end - start) / 1_000_000d;
        IO.println("Part 1: %d\nPart 2: %d\nTook: %.1fms".formatted(p1, p2, millis));
    }
}