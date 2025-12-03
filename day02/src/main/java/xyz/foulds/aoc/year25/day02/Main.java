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


        IO.println(ranges.stream().flatMapToLong(range -> range.getCandidates(2)).sum());
        IO.println(ranges.stream().mapToLong(Range::getSumOfAllCandidates).sum());

    }
}