package xyz.foulds.aoc.year25.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    static void main(final String[] args) throws IOException {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Please provide a single file path for the puzzle input.");
        }

        final var ranges = Files.readAllLines(Paths.get(args[0]))
                .stream()
                .flatMap(str -> Arrays.stream(str.split(",")))
                .map(String::trim)
                .map(Range::new)
                .toList();

        final long start = System.nanoTime();
        final long p1 = ranges.stream().flatMapToLong(range -> range.getCandidates(2)).sum();
        final long p2 = ranges.stream().mapToLong(Range::getSumOfAllCandidates).sum();
        final long end = System.nanoTime();

        final var millis = (end - start) / 1_000_000d;
        IO.println("Part 1: %d\nPart 2: %d\nTook: %.1fms".formatted(p1, p2, millis));
    }
}