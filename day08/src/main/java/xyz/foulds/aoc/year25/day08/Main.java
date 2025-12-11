package xyz.foulds.aoc.year25.day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main(final String[] args) throws IOException {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Please provide a single file path for the puzzle input.");
        }

        final var points = Files.readAllLines(Paths.get(args[0]))
                .stream()
                .map(String::trim)
                .map(Point::new)
                .toList();
        final var playground = new Playground(points, points.size() > 20 ? 1000 : 10);
        final long start = System.nanoTime();
        final long[] solution = playground.solve();
        final long end = System.nanoTime();

        final var millis = (end - start) / 1_000_000d;
        IO.println("Part 1: %d\nPart 2: %d\nTook: %.1fms".formatted(solution[0], solution[1], millis));
    }
}