package xyz.foulds.aoc.year25.day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    static void main(final String[] args) throws IOException {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Please provide a single file path for the puzzle input.");
        }

        final var coords = Files.readAllLines(Paths.get(args[0]))
                .stream()
                .map(String::trim)
                .map(Coordinate::new)
                .toList();

        final var floor = new Floor(coords);

        final long start = System.nanoTime();
        final long p1 = floor.solvePartOne();
        final long p2 = floor.solvePartTwo();
        final long end = System.nanoTime();

        final var millis = (end - start) / 1_000_000d;
        IO.println("Part 1: %d\nPart 2: %d\nTook: %.1fms".formatted(p1, p2, millis));
    }
}