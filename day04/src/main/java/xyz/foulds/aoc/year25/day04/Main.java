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

        IO.println(grid.getAccessibleCount());
        IO.println(grid.getAllAccessibleCount());
    }
}