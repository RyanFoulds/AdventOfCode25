package xyz.foulds.aoc.year25.day07;

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
                .map(String::trim)
                .toList();
        final var manifold = new Manifold(lines);

        IO.println(manifold.getSplitCountPartOne());
    }
}