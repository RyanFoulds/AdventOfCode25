package xyz.foulds.aoc.year25.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    static void main(final String[] args) throws IOException {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Please provide a single file path for the puzzle input.");
        }

        final Dial dial = new Dial(100L, 50L);

        Files.readAllLines(Paths.get(args[0]))
                .stream()
                .map(String::trim)
                .forEachOrdered(line -> dial.turn(Direction.valueOf(line.substring(0, 1)), Long.parseLong(line.substring(1))));

        final long start = System.nanoTime();
        final long p1 = dial.getTimesAtZero();
        final long p2 = dial.getTimesPassingZero();
        final long end = System.nanoTime();

        final var micros = (end - start) / 1_000d;
        IO.println("Part 1: %d\nPart 2: %d\nTook: %.1fµs".formatted(p1, p2, micros));
    }
}