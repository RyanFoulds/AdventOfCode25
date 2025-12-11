package xyz.foulds.aoc.year25.day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    static void main(final String[] args) throws IOException {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Please provide a single file path for the puzzle input.");
        }

        final var lines = Files.readAllLines(Paths.get(args[0]))
                .stream()
                .toList();
        final var worksheet = new Worksheet(lines);

        final long start = System.nanoTime();
        final long p1 = worksheet.getSumOfPartOneAnswers();
        final long p2 = worksheet.getSumOfPartTwoAnswers();
        final long end = System.nanoTime();

        final var millis = (end - start) / 1_000_000d;
        IO.println("Part 1: %d\nPart 2: %d\nTook: %.1fms".formatted(p1, p2, millis));
    }
}