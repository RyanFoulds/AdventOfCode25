package xyz.foulds.aoc.year25.day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    static void main(final String[] args) throws IOException {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Please provide a single file path for the puzzle input.");
        }

        final List<String> input = Files.readAllLines(Paths.get(args[0]))
                .stream()
                .map(String::trim)
                .toList();
        final Grid grid = new Grid(input);

        final long start = System.nanoTime();
        final long p1 = grid.getAccessibleCount();
        final long p2 = grid.getAllAccessibleCount();
        final long end = System.nanoTime();

        final var millis = (end - start) / 1_000_000d;
        IO.println("Part 1: %d\nPart 2: %d\nTook: %.1fms".formatted(p1, p2, millis));
    }
}