package xyz.foulds.aoc.year25.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    static void main(final String[] args) throws IOException {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Please provide a single file path for the puzzle input.");
        }

        final var input = Files.readString(Paths.get(args[0]));

        final var parts = input.split("\n\n");

        final var shapes = Arrays.stream(parts).filter(str -> !str.contains("x"))
                .map(str -> Arrays.stream(str.split("\n")).toList())
                .map(Shape::new)
                .collect(Collectors.toMap(Shape::id, Function.identity()));
        final var regions = Arrays.stream(parts[parts.length - 1].split("\n"))
                .map(Region::new)
                .toList();

        final long start = System.nanoTime();
        final long p1 = regions.stream().filter(r -> r.willTheyFit(shapes)).count();
        final long end = System.nanoTime();

        final var millis = (end - start) / 1_000_000d;
        IO.println("Part 1: %d\nTook: %.1fms".formatted(p1, millis));
    }
}